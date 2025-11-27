package com.example.edufygenre.services;

import com.example.edufygenre.exceptions.BadRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordResponse;
import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.models.entities.GenreMapping;
import com.example.edufygenre.models.enums.MediaType;
import com.example.edufygenre.repositories.GenreMappingRepository;
import com.example.edufygenre.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//ED-344-AWS
@ExtendWith(MockitoExtension.class)
class MediaRecordServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMappingRepository genreMappingRepository;

    @InjectMocks
    private MediaRecordServiceImpl mediaRecordService;

    private Genre createdGenre(Long id, String name){
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }

    @Test
    void createRecordOfMedia_createsMappings_whenAllGenresExist() {
        Long mediaId = 11L;
        MediaType mediaType = MediaType.SONG;
        List<Long> genreIds = List.of(3L, 1L);

        CreateMediaRecordRequest request = mock(CreateMediaRecordRequest.class);
        when(request.getMediaId()).thenReturn(mediaId);
        when(request.getMediaType()).thenReturn(mediaType);
        when(request.getGenreIds()).thenReturn(genreIds);

        Genre rock = createdGenre(1L, "rock");
        Genre pop = createdGenre(3L, "pop");

        when(genreRepository.findAllById(anySet())).thenReturn(List.of(rock,pop));

        when(genreMappingRepository.existsByMediaTypeAndMediaIdAndGenre_Id(any(MediaType.class), anyLong(), anyLong()))
                .thenReturn(false);

        CreateMediaRecordResponse response = mediaRecordService.createRecordOfMedia(request);

        assertNotNull(response);
        assertEquals(mediaId, response.getMediaId());
        assertEquals(mediaType, response.getMediaType());
        assertEquals(List.of(1L, 3L), response.getCreatedGenreIds());

        ArgumentCaptor<List<GenreMapping>> captor = ArgumentCaptor.forClass(List.class);

        verify(genreMappingRepository, times(1)).saveAll(captor.capture());

        List<GenreMapping> saved = captor.getValue();
        assertEquals(2, saved.size());

        verify(genreRepository, times(1)).findAllById(anySet());
    }

    @Test
    void createRecordOfMedia_throwsBadRequest() {
        Long mediaId = 22L;
        MediaType mediaType = MediaType.SONG;
        List<Long> genreIds = List.of(1L, 2L);

        CreateMediaRecordRequest request = mock(CreateMediaRecordRequest.class);
        when(request.getMediaId()).thenReturn(mediaId);
        when(request.getMediaType()).thenReturn(mediaType);
        when(request.getGenreIds()).thenReturn(genreIds);

        Genre metal = createdGenre(1L, "Metal");

        when(genreRepository.findAllById(anySet())).thenReturn(List.of(metal));

        BadRequest exception = assertThrows(BadRequest.class, () -> mediaRecordService.createRecordOfMedia(request));

        assertTrue(exception.getMessage().contains("Genre"));
        assertTrue(exception.getMessage().contains("genreIds"));

        verify(genreMappingRepository, never()).existsByMediaTypeAndMediaIdAndGenre_Id(any(), anyLong(), anyLong());
        verify(genreMappingRepository, never()).saveAll(anyList());
    }

    @Test
    void createRecordOfMedia_doesNotSave_whenMappingExists() {
        Long mediaId = 33L;
        MediaType mediaType = MediaType.SONG;
        List<Long> genreIds = List.of(10L);

        CreateMediaRecordRequest request = mock(CreateMediaRecordRequest.class);
        when(request.getMediaId()).thenReturn(mediaId);
        when(request.getMediaType()).thenReturn(mediaType);
        when(request.getGenreIds()).thenReturn(genreIds);

        Genre hiphop = createdGenre(10L, "HipHop");

        when(genreRepository.findAllById(Set.of(10L))).thenReturn(List.of(hiphop));

        when(genreMappingRepository.existsByMediaTypeAndMediaIdAndGenre_Id(mediaType, mediaId, 10L))
                .thenReturn(true);

        CreateMediaRecordResponse response = mediaRecordService.createRecordOfMedia(request);

        assertNotNull(response);
        assertEquals(mediaId, response.getMediaId());
        assertEquals(mediaType, response.getMediaType());
        assertNotNull(response.getCreatedGenreIds());
        assertTrue(response.getCreatedGenreIds().isEmpty());

        verify(genreMappingRepository, never()).saveAll(anyList());
        verify(genreMappingRepository, times(1)).existsByMediaTypeAndMediaIdAndGenre_Id(mediaType, mediaId, 10L);
    }

}