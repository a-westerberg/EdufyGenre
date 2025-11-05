package com.example.edufygenre.models.entities;

import com.example.edufygenre.models.enums.MediaType;
import jakarta.persistence.*;

// ED-216-AWS
@Entity
@Table(
        name = "genre_mapping",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_genre_mapping",
                columnNames = {"media_type","media_id","genre_id"}
        ),
        indexes = {
                @Index(name = "idx_genre_mapping_media", columnList = "media_type, media_id"),
                @Index(name = "idx_genre_mapping_genre", columnList = "genre_id")
        }
)
public class GenreMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false, length = 50)
    private MediaType mediaType;

    @Column(name = "media_id", nullable = false)
    private Long mediaId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id",
        foreignKey = @ForeignKey(name = "fk_genre_mapping_genre"))
    private Genre genre;

    protected GenreMapping() {
    }

    public GenreMapping(MediaType mediaType, Long mediaId, Genre genre) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
    public Long getMediaId() {
        return mediaId;
    }
    public Genre getGenre() {
        return genre;
    }
}
