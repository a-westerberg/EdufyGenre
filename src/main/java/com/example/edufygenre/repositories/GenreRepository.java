package com.example.edufygenre.repositories;

import com.example.edufygenre.models.entities.Genre;
import com.example.edufygenre.models.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//ED-69-AWS
public interface GenreRepository extends JpaRepository<Genre, Long> {

//ED-72-AWS
    Optional<Genre> findByNameIgnoreCase(String name);


    //ED-218-AWS
    @Query("select distinct g from GenreMapping m join m.genre g where m.mediaType =:type")
    List<Genre> findAllByMediaType(@Param("type") MediaType mediaType);

//ED-240-AWS
    boolean existsByNameIgnoreCase(String name);

}
