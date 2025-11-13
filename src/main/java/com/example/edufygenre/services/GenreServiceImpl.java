package com.example.edufygenre.services;


import com.example.edufygenre.exceptions.ResourceNotFound;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.dto.MediaByGenreDTO;
import com.example.edufygenre.models.dto.mappers.GenreMapper;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.models.enums.MediaType;
import com.example.edufygenre.repositories.GenreMappingRepository;
import com.example.edufygenre.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//ED-69-AWS
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMappingRepository genreMappingRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMappingRepository genreMappingRepository) {
        this.genreRepository = genreRepository;
        this.genreMappingRepository = genreMappingRepository;
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

//ED-72-AWS
    @Override
    public GenreDTO getGenreByName(String name) {
        Genre genre = genreRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFound("Genre", "name", name));
        return GenreMapper.toDTO(genre);
    }

//ED-216-AWS
    @Override
    public List<GenreDTO> getGenresByMedia(MediaType mediaType, Long mediaId) {
        return genreMappingRepository.findByMediaTypeAndMediaId(mediaType, mediaId)
                .stream()
                .map(m -> GenreMapper.toDTO(m.getGenre()))
                .toList();
    }

    @Override
    public List<GenreDTO> getAllGenresByMediaType(MediaType mediaType) {
        return genreRepository.findAllByMediaType(mediaType)
                .stream()
                .map(GenreMapper::toDTO)
                .toList();
    }

//ED-52-AWS
    @Override
    public MediaByGenreDTO getMediaByGenre(Long genreId, MediaType mediaType) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFound("Genre", "id", genreId));
        List<Long> mediaIds = genreMappingRepository.findMediaIdsByGenreAndType(genreId, mediaType);
        return GenreMapper.toMediaByGenreDTO(genre, mediaIds);
    }
}
