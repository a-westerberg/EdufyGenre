package com.example.edufygenre.models.dto;

import com.example.edufygenre.models.enums.MediaType;

import java.util.List;

//ED-296-AWS
public class CreateMediaRecordResponse {

    private final Long mediaId;
    private final MediaType mediaType;
    private final List<Long> createdGenreIds;

    public CreateMediaRecordResponse(Long mediaId, MediaType mediaType, List<Long> createdGenreIds) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.createdGenreIds = createdGenreIds == null ? List.of() : createdGenreIds;
    }

    public Long getMediaId() {
        return mediaId;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
    public List<Long> getCreatedGenreIds() {
        return createdGenreIds;
    }
}
