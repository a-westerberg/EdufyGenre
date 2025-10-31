package com.example.edufygenre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ED-73-AWS
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    private final String resource;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFound(String resource, String fieldName, Object fieldValue) {
        super("%s not found by %s: %s".formatted(resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResource() {
        return resource;
    }
    public String getFieldName() {
        return fieldName;
    }
    public Object getFieldValue() {
        return fieldValue;
    }

}