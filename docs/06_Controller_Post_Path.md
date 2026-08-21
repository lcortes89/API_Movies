# P5 - Digital Academy - Spring & Spring Boot - Without Security

## Código de ejemplo

```java
@PostMapping("")
    public ResponseEntity<CountryDTOResponse> store(@RequestBody CountryDTORequest dto) {

        if (dto.name().isBlank())
            return ResponseEntity.badRequest().build();

        CountryDTOResponse storedEntity = editService.storeEntity(dto);

        if (storedEntity == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(201).body(storedEntity);
    }
```

## 1. `@PostMapping("")`

- Mapea peticiones **HTTP POST** a la ruta base definida en la clase: `${api-endpoint}/countries`.
- El `""` es redundante pero explícito; equivale a no poner nada dentro de los paréntesis.
- Semánticamente, un **POST** a una colección (`/countries`) se usa para **crear un nuevo recurso**.

## 2. `@RequestBody CountryDTORequest dto`

```java
public record CountryDTORequest(String name) { }
```

- `@RequestBody` le indica a Spring que **deserialice el JSON** del cuerpo de la petición HTTP a un objeto Java, usando Jackson por debajo.
- Se usa un **DTO de entrada** (`CountryDTORequest`), no la entidad `CountryEntity` directamente. Esto es una **buena práctica**:
  - El cliente de la API no necesita (ni debe) enviar el `id` (autogenerado en BD).
  - Desacopla el contrato de la API del modelo de persistencia.

## 3. Delegación a la capa de servicio

```java
@Override
    public CountryDTOResponse storeEntity(CountryDTORequest dto) {

        CountryEntity countryToSave = CountryMapper.toEntity(dto);

        // Comprobar si existe - lógica de negocio
        // https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html#query-by-example.fluent
        Example<CountryEntity> example = Example.of(countryToSave);
        boolean isEmpty  = repository.findAll(example).isEmpty();

        if (!isEmpty) return null;

        CountryEntity countrySaved = repository.save(countryToSave);

        return CountryMapper.toDTO(countrySaved);
    }
```

- **`CountryMapper.toEntity(dto)`**: convierte el DTO de entrada en una entidad JPA (`CountryEntity`), usando un mapper estático.
- **`Example.of(...)`**: usa **Query by Example (QBE)** de Spring Data JPA para comprobar si ya existe un país con esos mismos valores, evitando duplicados — esto es **lógica de negocio**, correctamente ubicada en el service.
- Si ya existe (`!isEmpty`), **devuelve `null`**.
- Si no existe, persiste (`repository.save(...)`) y devuelve el DTO de respuesta mapeado desde la entidad guardada (ya con su `id` generado).

## 4. Manejo de la respuesta según el resultado del service
| Resultado del service | Código HTTP devuelto |
|---|---|---|
| `dto.name()` en blanco | `400 Bad Request` |
| El país ya existía (`null`) | `209 Conflict` |
| Guardado con éxito | `201 Created` |

## 5. `ResponseEntity<CountryDTOResponse>`

- Permite controlar explícitamente el **código de estado HTTP** y el **cuerpo** de la respuesta, en lugar de dejar que Spring infiera un `200 OK` por defecto.
- Es la forma recomendada de construir respuestas cuando el resultado depende de lógica condicional (como en este caso: `400`, `201` o `204`/`409`).