package com.example.edufygenre.models.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateGenreDTO {

    @NotBlank
    private String name;
    private Boolean active;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
