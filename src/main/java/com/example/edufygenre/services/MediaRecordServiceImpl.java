package com.example.edufygenre.services;

import com.example.edufygenre.exceptions.BadRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordResponse;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.models.entities.GenreMapping;
import com.example.edufygenre.models.enums.MediaType;
import com.example.edufygenre.repositories.GenreMappingRepository;
import com.example.edufygenre.repositories.GenreRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//ED-296-AWS
@Service
public class MediaRecordServiceImpl implements MediaRecordService {

    private final GenreRepository genreRepository;
    private final GenreMappingRepository genreMappingRepository;

    @Autowired
    public MediaRecordServiceImpl(GenreRepository genreRepository, GenreMappingRepository genreMappingRepository) {
        this.genreRepository = genreRepository;
        this.genreMappingRepository = genreMappingRepository;
    }

    @Transactional
    @Override
    public CreateMediaRecordResponse createRecordOfMedia(CreateMediaRecordRequest request) {
        Long mediaId = request.getMediaId();
        MediaType mediaType = request.getMediaType();
        List<Long> genreIds = request.getGenreIds();

        Set<Long> requestedGenre = new HashSet<>(genreIds);
        List<Genre> foundGenres = genreRepository.findAllById(requestedGenre);
        Set<Long> foundIds = foundGenres
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());

        requestedGenre.removeAll(foundIds);
        if(!requestedGenre.isEmpty()){
            throw new BadRequest("Genre", "genreIds", requestedGenre);
        }

        List<GenreMapping> toSave = new ArrayList<>();
        List<Long> createdIds = new ArrayList<>();

        for(Genre genre : foundGenres){
            boolean exists = genreMappingRepository.existsByMediaTypeAndMediaIdAndGenre_Id(mediaType, mediaId, genre.getId());
            if(!exists){
                toSave.add(new GenreMapping(mediaType, mediaId, genre));
                createdIds.add(genre.getId());
            }
        }
        if(!toSave.isEmpty()){
            genreMappingRepository.saveAll(toSave);
        }

        createdIds.sort(Long::compareTo);
        return new CreateMediaRecordResponse(mediaId, mediaType, createdIds);
    }
}
