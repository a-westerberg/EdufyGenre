package com.example.edufygenre.entities;


import jakarta.persistence.*;

//ED-131-AWS
@Entity
@Table(
        name= "genre",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_genre_name", columnNames = "genre_name")
        }
)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_name", nullable = false, length = 50)
    private String name;

    // for soft delete (if we want to be able to deactivate some genre for customers with only specific genre
    @Column(name = "genre_active", nullable = false)
    private boolean active = true;

    public Genre() {
    }

    public Genre(Long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Genre(Genre genre){
        this.id = genre.id;
        this.name = genre.name;
        this.active = genre.active;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
