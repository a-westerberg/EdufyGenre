package com.example.edufygenre.services;

import com.example.edufygenre.exceptions.ResourceNotFound;
import com.example.edufygenre.models.dto.GenreDTO;
import com.example.edufygenre.models.dto.MediaByGenreDTO;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.models.entities.GenreMapping;
import com.example.edufygenre.models.enums.MediaType;
import com.example.edufygenre.repositories.GenreMappingRepository;
import com.example.edufygenre.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//ED-344-AWS
@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMappingRepository genreMappingRepository;

    @InjectMocks
    private GenreServiceImpl genreService;


    private Genre createGenre(Long id, String name) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }

    // GetAllGenres
    @Test
void getAllGenres_returnsMappedList() {
        Genre rock = createGenre(1L, "Rock");
        Genre pop = createGenre(2L, "Pop");
        when(genreRepository.findAll()).thenReturn(List.of(rock,pop));

        List<GenreDTO> result = genreService.getAllGenres();

        assertEquals(2, result.size());
        assertEquals("Rock", result.get(0).getName());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Pop", result.get(1).getName());
        assertEquals(2L, result.get(1).getId());

        verify(genreRepository, times(1)).findAll();
    }

    @Test
    void getAllGenres_returnsEmptyList() {
        when(genreRepository.findAll()).thenReturn(List.of());

        List<GenreDTO> result = genreService.getAllGenres();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(genreRepository, times(1)).findAll();

    }

    // getGenreById
    @Test
    void getGenreById_returnsDTO() {
        Long id = 10L;
        Genre genre = createGenre(id, "Metal");
        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));

        GenreDTO result = genreService.getGenreById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Metal", result.getName());

        verify(genreRepository, times(1)).findById(id);
    }

    @Test
    void getGenreById_throwsResourceNotfound() {
        Long id = 999L;
        when(genreRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFound ex = assertThrows(ResourceNotFound.class, () -> genreService.getGenreById(id));

        assertTrue(ex.getMessage().contains("Genre"));
        assertTrue(ex.getMessage().contains("id"));

        verify(genreRepository, times(1)).findById(id);
    }

    // getGenreByName
    @Test
    void getGenreByName_returnsDTO() {
        String name = "Jazz";
        Genre genre = createGenre(5L, "Jazz");
        when(genreRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(genre));

        GenreDTO result = genreService.getGenreByName(name);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Jazz", result.getName());

        verify(genreRepository, times(1)).findByNameIgnoreCase(name);
    }

    @Test
    void getGenreByName_throwsResourceNotfound() {
        String name = "unknown";
        when(genreRepository.findByNameIgnoreCase(name)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> genreService.getGenreByName(name));

        verify(genreRepository, times(1)).findByNameIgnoreCase(name);
    }


    // getGenreByMedia
    @Test
    void getGenresByMedia_returnsMappedGenres() {
        MediaType mediaType = MediaType.SONG;
        Long mediaId = 100L;

        Genre rock  = createGenre(1L, "Rock");
        Genre pop  = createGenre(2L, "Pop");

        GenreMapping rockMapping = mock(GenreMapping.class);
        GenreMapping popMapping = mock(GenreMapping.class);
        when(rockMapping.getGenre()).thenReturn(rock);
        when(popMapping.getGenre()).thenReturn(pop);


        when(genreMappingRepository.findByMediaTypeAndMediaId(mediaType,mediaId))
                .thenReturn(List.of(rockMapping,popMapping));

        List<GenreDTO> result = genreService.getGenresByMedia(mediaType,mediaId);

        assertEquals(2, result.size());
        assertEquals("Rock", result.get(0).getName());
        assertEquals("Pop", result.get(1).getName());

        verify(genreMappingRepository, times(1)).findByMediaTypeAndMediaId(mediaType,mediaId);
    }


    // getAllGenresByMediaType
    @Test
    void getAllGenresByMediaType_returnsMappedGenres() {
        MediaType mediaType = MediaType.SONG;

        Genre rock  = createGenre(1L, "Rock");
        Genre pop  = createGenre(2L, "Pop");

        when(genreRepository.findAllByMediaType(mediaType)).thenReturn(List.of(rock,pop));

        List<GenreDTO> result = genreService.getAllGenresByMediaType(mediaType);

        assertEquals(2, result.size());
        assertEquals("Rock", result.get(0).getName());
        assertEquals("Pop", result.get(1).getName());

        verify(genreRepository, times(1)).findAllByMediaType(mediaType);
    }


    // getMediaByGenre
    @Test
    void getMediaByGenre_returnsDTOWithMediaIds() {
        Long genreId = 1L;
        MediaType mediaType = MediaType.SONG;

        Genre genre = createGenre(genreId, "Rock");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));

        List<Long> mediaIds = List.of(10L, 20L, 30L);
        when(genreMappingRepository.findMediaIdsByGenreAndType(genreId, mediaType))
                .thenReturn(mediaIds);

        MediaByGenreDTO result = genreService.getMediaByGenre(genreId, mediaType);

        assertNotNull(result);
        assertEquals("Rock", result.getGenreName());
        assertEquals(mediaIds, result.getMediaIds());
        assertEquals(3, result.getMediaIds().size());

        verify(genreRepository, times(1)).findById(genreId);
        verify(genreMappingRepository, times(1)).findMediaIdsByGenreAndType(genreId, mediaType);
    }

}