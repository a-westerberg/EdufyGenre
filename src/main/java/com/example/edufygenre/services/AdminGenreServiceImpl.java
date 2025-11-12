package com.example.edufygenre.services;

import com.example.edufygenre.exceptions.BadRequest;
import com.example.edufygenre.models.dto.CreateGenreDTO;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.dto.mappers.GenreMapper;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//ED-240-AWS
@Service
public class AdminGenreServiceImpl implements AdminGenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public AdminGenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public GenreDTO createGenre(CreateGenreDTO request) {
        String name = request.getName().trim();
        if(name.isEmpty()){
            throw new BadRequest("Genre", "name", "must not be blank");
        }
        if(genreRepository.existsByNameIgnoreCase(name)){
            throw new BadRequest("Genre", "name", "already exists");
        }

        boolean active = request.getActive() == null || request.getActive();
        Genre saved = genreRepository.save(new Genre(name, active));
        return GenreMapper.toDTO(saved);
    }
}
