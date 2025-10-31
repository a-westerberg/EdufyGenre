package com.example.edufygenre.repositories;

import com.example.edufygenre.models.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

//ED-69-AWS
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
