package com.example.edufygenre.dto;


// ED-137-AWS
public class VideoPlaylistDTO {

    private Long id;
    private String title;


    public VideoPlaylistDTO() {
    }

    public VideoPlaylistDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "VideoPlaylistDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
