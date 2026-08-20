# P5 - Digital Academy - Spring & Spring Boot - Without Security

## 1. ¿Qué es un Controller en Spring Boot?

Un **Controller** es la capa encargada de recibir peticiones HTTP desde la View (cliente) y devolver respuestas.  
En aplicaciones REST, se usa:

```java
@RestController
```

Esto indica que:
- La clase expone endpoints HTTP.
- Los métodos devuelven datos directamente (JSON, texto, objetos).
- No se usa @ResponseBody porque ya está incluido.

## 2. Controlador básico - Visto en clase
```
@RestController
public class HomeController {
    
    @GetMapping("")
    public String index() {
        return new String("Hello, Spring Boot");
    }

    @GetMapping("passing-param")
    public String passingParams(@RequestParam String msg) {
        return "Params: " + msg;
    }

    @GetMapping("params")
    public String getParams(@RequestParam(name = "name") String paramName, String country) {
        return new String(paramName + ", " + country);
    }
}
```

## 3. Conceptos clave del ejemplo

**@GetMapping("")**</br>
Define una ruta GET.
Ruta raíz: http://localhost:8080/

**@RequestParam** </br>
Extrae parámetros enviados en la URL: `GET /passing-param?msg=hola`

El método recibe: `@RequestParam String msg`

**Parámetros con nombre personalizado**</br>
`@RequestParam(name = "name") String paramName` Permite mapear ?name=valor a una variable con otro nombre.

## 4. Controlador REST con rutas y servicio inyectado
```
@RestController
@RequestMapping(path = "${api-endpoint}/countries")
public class CountryController {

    private final InterfaceGenericService<CountryEntity> service;

    public CountryController(InterfaceGenericService<CountryEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<CountryEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public CountryEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
```

## 5. Conceptos clave del controlador avanzado
**@RequestMapping(path = "...")**</br>
Define un prefijo de ruta para todos los endpoints del controlador.

Ejemplo si `api-endpoint=/api/v1`:
```
/api/v1/countries
/api/v1/countries/1
```

**Inyección de dependencias por constructor**
```
public CountryController(InterfaceGenericService<CountryEntity> service)
```
- Recomendado por Spring
- Facilita testing
- Evita @Autowired en campos

**@GetMapping("")**</br>
Devuelve todos los países.

**@PathVariable**</br>
Extrae valores desde la URL:
```
GET /countries/5

@GetMapping("{id}")
public CountryEntity getById(@PathVariable Long id)
```

## 7. Buenas prácticas con Controllers
- Mantenerlos ligeros: solo recibir peticiones y delegar al servicio.
- No incluir lógica de negocio.
- Usar DTOs + mappers para no exponer Entities directamente.
- Definir rutas claras y consistentes.
- Usar inyección por constructor.
- Manejar errores con `@ExceptionHandler` o `@ControllerAdvice`.