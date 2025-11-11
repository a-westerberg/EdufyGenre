package com.example.edufygenre.repositories;

import com.example.edufygenre.models.entities.GenreMapping;
import com.example.edufygenre.models.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// ED-216-AWS
public interface GenreMappingRepository extends JpaRepository<GenreMapping, Long> {

    List<GenreMapping> findByMediaTypeAndMediaId(MediaType mediaType, Long mediaId);

//ED-52-AWS
    @Query("""
    select gm.mediaId
        from GenreMapping gm
            where gm.genre.id = :genreId
                and gm.mediaType = :mediaType
                    order by gm.mediaId
    """)
    List<Long> findMediaIdsByGenreAndType(@Param("genreId") Long genreId, @Param("mediaType") MediaType mediaType);
}
