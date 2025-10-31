package com.example.edufygenre.models.dto.mappers;

import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.entities.Genre;

//ED-69-AWS
public class GenreMapper {

    public GenreMapper() {
    }

    public static GenreDTO toDto(Genre genre) {
        if(genre == null)
            return null;
        return new GenreDTO(genre.getId(), genre.getName());
    }

}
