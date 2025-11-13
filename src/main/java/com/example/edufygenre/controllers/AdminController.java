package com.example.edufygenre.controllers;

import com.example.edufygenre.models.dto.CreateGenreDTO;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.services.AdminGenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


//ED-240-AWS
@RestController
@RequestMapping("/api/v1/genre/admin")
@Validated
public class AdminController {

    private final AdminGenreService adminGenreService;

    @Autowired
    public AdminController(AdminGenreService adminGenreService) {
        this.adminGenreService = adminGenreService;
    }

    @PostMapping("/add-genre")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody CreateGenreDTO request) {
        GenreDTO genreDTO = adminGenreService.createGenre(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .replacePath("/api/v1/genre/{id}")
                .buildAndExpand(genreDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(genreDTO);
    }

}
