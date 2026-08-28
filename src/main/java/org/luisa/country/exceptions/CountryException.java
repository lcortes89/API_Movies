package org.factoriaf5.country.exceptions;

public class CountryException extends RuntimeException {
    
    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

}
