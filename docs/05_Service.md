# P5 - Digital Academy - Spring & Spring Boot - Without Security

# @Service — Anotación de Spring

## ¿Qué es?
`@Service` es una **anotación de estereotipo** de Spring que marca una clase como parte de la **capa de servicio** (lógica de negocio) dentro de una arquitectura en capas (Controller → Service → Repository).

Es una especialización de `@Component`, lo que significa que Spring la detecta automáticamente durante el **component scanning** y crea un **bean** administrado por el contenedor de Spring (Spring IoC Container).

## Ejemplo del código

```java
@Service
public class CountryServiceImpl implements InterfaceGenericService<CountryEntity> {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }
    // ...
}
```

## Puntos clave

| Aspecto | Explicación |
|---|---|
| **Propósito semántico** | Indica que la clase contiene lógica de negocio. |
| **Registro automático** | Spring escanea el classpath y registra la clase como un **bean singleton** por defecto. |
| **Inyección de dependencias** | Al ser un bean, puede inyectarse en otras clases (ej. controllers) usando `@Autowired` o, como buena práctica, **inyección por constructor** (como se ve en el ejemplo). |
| **Funcionalmente igual a @Component** | En términos técnicos, `@Service` no añade comportamiento extra sobre `@Component`. Su valor es principalmente **documentar la intención** y mejorar la legibilidad de la arquitectura. |

## ¿Por qué usar `@Service` y no `@Component` a secas?

- Mejora la **claridad arquitectónica**: cualquier desarrollador que lea el código entiende inmediatamente el rol de la clase.
- Permite aplicar **AOP (Aspect-Oriented Programming)** de forma selectiva sobre la capa de servicio (ej. transacciones, logging, manejo de excepciones específico).
- Es una convención ampliamente adoptada en la comunidad Spring.

## Relación con la inyección de dependencias en el ejemplo

```java
private final CountryRepository repository;

public CountryServiceImpl(CountryRepository repository) {
    this.repository = repository;
}
```

- Al marcar `CountryServiceImpl` con `@Service`, Spring puede **inyectar automáticamente** el `CountryRepository` (que a su vez es un bean, típicamente vía `@Repository` o al extender `JpaRepository`).
- Esto se conoce como **inyección por constructor**, la forma recomendada en Spring porque:
  - Permite declarar el campo como `final` (inmutabilidad).
  - Facilita las pruebas unitarias (se puede instanciar sin el contenedor de Spring).
  - Hace explícitas las dependencias obligatorias de la clase.

## ✅ Buenas prácticas para la capa de Servicio (@Service)

### 1. Programar contra interfaces, no contra implementaciones
Definir una interfaz y que la clase `@Service` la implemente, permite:
- Desacoplar el contrato del comportamiento concreto.
- Facilitar el cambio de implementación sin afectar a quien la consume.
- Mejorar la capacidad de hacer **mocking** en tests unitarios.

```java
@Service
public class CountryServiceImpl implements InterfaceGenericService<CountryEntity> {
    // ...
}
```

### 2. Inyección de dependencias por constructor, no por campo
Evitar `@Autowired` sobre atributos directamente:

```java
// Evitar
@Autowired
private CountryRepository repository;

// Preferido
private final CountryRepository repository;

public CountryServiceImpl(CountryRepository repository) {
    this.repository = repository;
}
```

Ventajas: inmutabilidad (`final`), dependencias explícitas, y clases más fáciles de testear sin necesidad de un contenedor Spring.

### 3. No exponer entidades JPA directamente al exterior
Idealmente, el servicio no debería devolver directamente `CountryEntity` a capas externas (ej. controllers/API), sino un **DTO**. Esto evita:
- Acoplar la API pública a la estructura de la base de datos.
- Problemas de serialización (relaciones lazy, ciclos, etc.).

### 4. Uso de `@Transactional` cuando aplica
Si el método de servicio realiza múltiples operaciones que deben ejecutarse de forma atómica (ej. varias escrituras), se debe anotar con `@Transactional` para garantizar consistencia:

```java
@Transactional
public void updateCountryAndRelatedData(...) { ... }
```

> Para operaciones de solo lectura, se recomienda `@Transactional(readOnly = true)` para optimizar el rendimiento.

### 7. Evitar lógica duplicada mediante interfaces genéricas
El uso de `InterfaceGenericService<T>` es una buena práctica para reutilizar contratos comunes (`getById`, `getEntities`, etc.) entre distintos servicios, evitando repetir la misma firma de métodos en cada entidad.