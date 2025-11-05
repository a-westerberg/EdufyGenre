package com.example.edufygenre.services;

import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.enums.MediaType;

import java.util.List;

//ED-69-AWS
public interface GenreService {

    List<GenreDTO> getAllGenres();

// ED-73-AWS
    GenreDTO getGenreById(Long id);

//ED-72-AWS
    GenreDTO getGenreByName(String name);

// ED-216-AWS
    List<GenreDTO> getGenresByMedia(MediaType mediaType, Long mediaId);
}
