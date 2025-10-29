package com.example.edufygenre.dto;


// ED-132-AWS
public class SongDTO {

    private Long id;
    private String title;


    public SongDTO() {
    }

    public SongDTO(Long id, String title) {
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
        return "SongDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
