package com.example.edufygenre.services;

import com.example.edufygenre.exceptions.BadRequest;
import com.example.edufygenre.models.dto.CreateGenreDTO;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//ED-344-AWS
@ExtendWith(MockitoExtension.class)
class AdminGenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private AdminGenreServiceImpl adminGenreService;


    @Test
    void createGenre_throwsBadRequest_whenNameIsBlank() {
        CreateGenreDTO request = mock(CreateGenreDTO.class);
        when(request.getName()).thenReturn("    ");

        BadRequest exception = assertThrows(BadRequest.class, () -> adminGenreService.createGenre(request));

        assertTrue(exception.getMessage().contains("Genre"));
        assertTrue(exception.getMessage().contains("name"));
        assertTrue(exception.getMessage().contains("must not be blank"));

        verify(genreRepository, never()).existsByNameIgnoreCase(anyString());
        verify(genreRepository, never()).save(any(Genre.class));

    }

    @Test
    void createGenre_throwsBadRequest_whenNameAlreadyExists() {
        CreateGenreDTO request = mock(CreateGenreDTO.class);
        when(request.getName()).thenReturn("Rock");

        when(genreRepository.existsByNameIgnoreCase("Rock")).thenReturn(true);

        BadRequest exception = assertThrows(BadRequest.class, () -> adminGenreService.createGenre(request));

        assertTrue(exception.getMessage().contains("Genre"));
        assertTrue(exception.getMessage().contains("name"));
        assertTrue(exception.getMessage().contains("already exists"));

        verify(genreRepository, times(1)).existsByNameIgnoreCase("Rock");
        verify(genreRepository, never()).save(any(Genre.class));

    }

    @Test
    void createGenre_createsGenre_whenValidRequestAndActiveNull() {
        CreateGenreDTO request = mock(CreateGenreDTO.class);
        when(request.getName()).thenReturn("   Metal    ");
        when(request.getActive()).thenReturn(null);

        when(genreRepository.existsByNameIgnoreCase("Metal")).thenReturn(false);

        when(genreRepository.save(any(Genre.class))).thenAnswer(invocation -> {
            Genre genre = invocation.getArgument(0);
            genre.setId(42L);
            return genre;
        });

        GenreDTO result = adminGenreService.createGenre(request);

        assertNotNull(result);
        assertEquals(42L, result.getId());
        assertEquals("Metal", result.getName());

        verify(genreRepository, times(1)).existsByNameIgnoreCase("Metal");
        verify(genreRepository, times(1)).save(any(Genre.class));

    }

    @Test
    void createGenre_createsGenre_whenValidRequestAndActiveFalse() {
        CreateGenreDTO request = mock(CreateGenreDTO.class);
        when(request.getName()).thenReturn("Pop");
        when(request.getActive()).thenReturn(false);

        when(genreRepository.existsByNameIgnoreCase("Pop")).thenReturn(false);

        when(genreRepository.save(any(Genre.class)))
                .thenAnswer(invocation -> {
                    Genre genre = invocation.getArgument(0);
                    genre.setId(100L);
                    return genre;
                });

        GenreDTO result = adminGenreService.createGenre(request);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Pop", result.getName());

        verify(genreRepository, times(1)).existsByNameIgnoreCase("Pop");
        verify(genreRepository, times(1)).save(any(Genre.class));

    }

}