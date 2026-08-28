package org.factoriaf5.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
