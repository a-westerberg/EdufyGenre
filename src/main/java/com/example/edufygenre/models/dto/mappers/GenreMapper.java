package com.example.edufygenre.models.dto.mappers;

import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.dto.MediaByGenreDTO;
import com.example.edufygenre.models.entities.Genre;

import java.util.Collections;
import java.util.List;

//ED-69-AWS
public class GenreMapper {

    private GenreMapper() {
    }

    public static GenreDTO toDTO(Genre genre) {
        if(genre == null)
            return null;
        return new GenreDTO(genre.getId(), genre.getName());
    }

//ED-52-AWS
    public static MediaByGenreDTO toMediaByGenreDTO(Genre genre, List<Long> mediaIds) {
        if(genre == null)
            return null;
        return new MediaByGenreDTO(
                genre.getName(),
                mediaIds == null ? Collections.emptyList() : mediaIds
        );
    }

}
