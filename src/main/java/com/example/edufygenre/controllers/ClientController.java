package com.example.edufygenre.controllers;

import com.example.edufygenre.models.dto.CreateMediaRecordRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordResponse;
import com.example.edufygenre.models.dto.GenreDTO;

import com.example.edufygenre.models.dto.MediaByGenreDTO;
import com.example.edufygenre.models.enums.MediaType;
import com.example.edufygenre.services.GenreService;
import com.example.edufygenre.services.MediaRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ED-69-AWS
@RestController
@RequestMapping("/api/v1/genre")
@Validated
public class ClientController {

    private final GenreService genreService;
    private final MediaRecordService mediaRecordService;

    @Autowired
    public ClientController(GenreService genreService, MediaRecordService mediaRecordService) {
        this.genreService = genreService;
        this.mediaRecordService = mediaRecordService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

// ED-73-AWS
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        GenreDTO genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

//ED-72-AWS
    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDTO> getGenreByName(@PathVariable String name) {
        return ResponseEntity.ok(genreService.getGenreByName(name));
    }

//ED-216-AWS
    @GetMapping("/by/media-id/{mediaType}/{mediaId}")
    public ResponseEntity<List<GenreDTO>> getGenresByMedia(
            @PathVariable MediaType mediaType,
            @PathVariable  Long mediaId) {
        return ResponseEntity.ok(genreService.getGenresByMedia(mediaType, mediaId));
    }

//ED-218-AWS
    @GetMapping("/by/media-type/{mediaType}")
    public ResponseEntity<List<GenreDTO>> getAllGenresByMediaType(@PathVariable MediaType mediaType) {
        return ResponseEntity.ok(genreService.getAllGenresByMediaType(mediaType));
    }

//ED-52-AWS
    @GetMapping("/{genreId}/media/by-type/{mediaType}")
    public ResponseEntity<MediaByGenreDTO> getMediaByGenre(@PathVariable Long genreId, @PathVariable MediaType mediaType) {
        MediaByGenreDTO mediaByGenreDTO = genreService.getMediaByGenre(genreId, mediaType);
        return ResponseEntity.ok(mediaByGenreDTO);
    }

//ED-296-AWS
    @PostMapping("/media/record")
    public ResponseEntity<CreateMediaRecordResponse> createMediaRecord(@RequestBody @Valid CreateMediaRecordRequest request){
        CreateMediaRecordResponse response = mediaRecordService.createRecordOfMedia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
