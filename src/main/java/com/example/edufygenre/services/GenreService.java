package com.example.edufygenre.services;

import com.example.edufygenre.models.dto.GenreDTO;

import java.util.List;

//ED-69-AWS
public interface GenreService {

    List<GenreDTO> getAllGenres();
}
