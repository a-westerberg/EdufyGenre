package com.example.edufygenre.models.dto;


// ED-133-AWS
public class PodcastEpisodeDTO {

    private Long id;
    private String title;


    public PodcastEpisodeDTO() {
    }

    public PodcastEpisodeDTO(Long id, String title) {
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
        return "PodcastEpisodeDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
