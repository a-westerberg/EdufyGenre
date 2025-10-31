package com.example.edufygenre.services;


import com.example.edufygenre.exceptions.ResourceNotFound;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.dto.mappers.GenreMapper;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//ED-69-AWS
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(GenreMapper::toDTO)
                .toList();
    }

// ED-73-AWS
    @Override
    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Genre", "id", id));
        return GenreMapper.toDTO(genre);
    }
}
