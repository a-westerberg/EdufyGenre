package com.example.edufygenre.repositories;

import com.example.edufygenre.models.entities.GenreMapping;
import com.example.edufygenre.models.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// ED-216-AWS
public interface GenreMappingRepository extends JpaRepository<GenreMapping, Long> {

    List<GenreMapping> findByMediaTypeAndMediaId(MediaType mediaType, Long mediaId);
}
