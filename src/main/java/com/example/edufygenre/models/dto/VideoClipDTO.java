package com.example.edufygenre.models.dto;


// ED-134-AWS
public class VideoClipDTO {

    private Long id;
    private String title;

    public VideoClipDTO() {
    }

    public VideoClipDTO(Long id, String title) {
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
        return "VideoClipDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
