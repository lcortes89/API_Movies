package org.factoriaf5.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/countries")
public class CountryController {
    
    @GetMapping("")
    public CountryEntity index() {
        CountryEntity spain = new CountryEntity(1L, "Spain");
        return spain;
    }
    

}
