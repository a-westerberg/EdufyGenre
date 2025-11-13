package com.example.edufygenre.models.dto;

import com.example.edufygenre.models.enums.MediaType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

//ED-296-AWS
public class CreateMediaRecordRequest {

    @NotNull
    private Long mediaId;

    @NotNull
    private MediaType mediaType;

    @NotEmpty
    private List<@NotNull Long> genreIds;

    public Long getMediaId() {
        return mediaId;
    }
    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
    public List<Long> getGenreIds() {
        return genreIds;
    }
    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }
}
