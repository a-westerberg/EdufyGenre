package com.example.edufygenre.models.dto;

import java.util.List;

//ED-52-AWS
public class MediaByGenreDTO {

    private String genreName;
    private List<Long> mediaIds;

    public MediaByGenreDTO(String genreName, List<Long> mediaIds) {
        this.genreName = genreName;
        this.mediaIds = mediaIds;
    }

    public String getGenreName() {
        return genreName;
    }
    public List<Long> getMediaIds() {
        return mediaIds;
    }
}
