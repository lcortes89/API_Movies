# P5 - Digital Academy - Spring & Spring Boot - Without Security

## 1. ¿Qué es una Entity?

Una **Entity** es una clase Java que representa una **tabla de base de datos** dentro de JPA/Hibernate.  
Spring Boot detecta automáticamente las entidades y genera las tablas según la configuración (`ddl-auto`).

### Características principales
- Se marca con `@Entity`.  
- Se puede mapear a una tabla con `@Table`.  
- Cada instancia representa una fila de la tabla.  
- Debe tener un **id único** (`@Id`).  
- Hibernate gestiona su ciclo de vida (persistencia, actualización, eliminación).

---

## 2. Código explicado

```java
@Entity
@Table(name = "countries")
public class CountryEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CountryEntity() {
    }

    public CountryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### Anotaciones clave

**@Entity**
Indica que la clase es una entidad JPA.
Hibernate la convertirá en una tabla.

**@Table(name = "countries")**
Define el nombre de la tabla en la base de datos.
Si no se especifica, usa el nombre de la clase.

**@Id**
Marca el campo como clave primaria.

**@GeneratedValue(strategy = GenerationType.IDENTITY)**
Indica que el ID se genera automáticamente por la base de datos (auto-increment).

### 4. Requisitos de una Entity
- Constructor vacío (obligatorio para Hibernate)
- Campos privados
- Getters y setters
- Identificador único
- Opcional: constructor completo para facilitar creación

### 5. ¿Cómo se relaciona con Spring Boot?

Spring Boot, junto con JPA/Hibernate:
- Detecta automáticamente las entidades.
- Genera las tablas según la configuración (ddl-auto=create-drop en H2).
- Permite usar repositorios (CrudRepository, JpaRepository) para CRUD.
- Mapea automáticamente los tipos Java a tipos SQL.

### 7. Buenas prácticas con Entities
- No incluir lógica de negocio dentro de la Entity.
- Mantenerlas simples.
- Usar record solo para DTOs, no para Entities (Hibernate requiere mutabilidad).
- Evitar exponer Entities directamente en la capa View → usar DTOs + mappers.
- Mantener nombres claros y consistentes entre tabla y clase.