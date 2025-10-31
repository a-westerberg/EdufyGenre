package com.example.edufygenre.repositories;

import com.example.edufygenre.models.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//ED-69-AWS
public interface GenreRepository extends JpaRepository<Genre, Long> {

//ED-72-AWS
    Optional<Genre> findByNameIgnoreCase(String name);
}
