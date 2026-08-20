# P5 - Digital Academy - Spring & Spring Boot - Without Security

## 1. `application.properties` — Archivo de configuración

Es el archivo central donde Spring Boot carga la configuración de la aplicación:

- Propiedades de servidor  
- Configuración de base de datos  
- Logging  
- Perfiles  
- JPA / Hibernate  
- Variables externas  

Spring Boot detecta automáticamente este archivo en:
```
src/main/resources/application.properties
```

---

## 2. `spring.profiles.active` — Activación de perfiles

Spring Boot permite definir **perfiles** para separar configuraciones según el entorno:

- `dev` → desarrollo  
- `test` → pruebas  
- `prod` → producción  

### Activar un perfil
```properties
spring.profiles.active=dev

Esto hace que Spring cargue:
application-dev.properties
```

- Permite cambiar configuraciones sin tocar el código
- Facilita despliegues y entornos aislados
- Cumple OCP (Open/Closed) al extender sin modificar

## 3. Perfil de base de datos con H2 (memoria)
H2 es una base de datos ligera, ideal para:

- pruebas
- demos
- desarrollo
- clases introductorias

Se ejecuta en memoria, por lo que se borra al detener la aplicación.

### Activación típica
```
spring.profiles.active=h2

Y se crea un archivo:
application-h2.properties
```

### 4. Propiedades de configuración de H2
Conexión a la base de datos
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

Notas:
- jdbc:h2:mem:testdb → base de datos en memoria
- Variables `${DATABASE_USERNAME}` y `${DATABASE_PASSWORD}` se leen del entorno
- No requiere instalación externa

### 5. Configuración de JPA / Hibernate
```
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create-drop
```

> Dialect → indica a Hibernate cómo generar SQL para H2 </br>
> defer-datasource-initialization → permite cargar data.sql después de inicializar la BD </br>
> ddl-auto=create-drop

- Crea las tablas al iniciar
- Las elimina al cerrar
- Ideal para pruebas y demos

### 6. Mostrar SQL en consola
```
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
- Útil para depuración
- Permite ver consultas generadas por Hibernate
- Facilita aprendizaje de JPA

### 7. Configuración de Logging
```
logging.level.root=warn
logging.level.org.springframework.web=debug
logging.level.org.hibernate=error
```

> root=warn → solo avisos y errores globales </br>
> springframework.web=debug → ver detalles de peticiones HTTP </br>
> hibernate=error → solo errores de Hibernate </br>

- Control granular de logs
- Evita ruido innecesario
- Útil para depuración en desarrollo