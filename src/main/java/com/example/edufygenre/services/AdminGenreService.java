package com.example.edufygenre.services;

import com.example.edufygenre.models.dto.CreateGenreDTO;
import com.example.edufygenre.models.dto.GenreDTO;

//ED-240-AWS
public interface AdminGenreService {
    GenreDTO createGenre(CreateGenreDTO request);
}
