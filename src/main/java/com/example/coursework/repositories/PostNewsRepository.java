package com.example.coursework.repositories;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.TypeOfNews;
import com.example.coursework.models.PostNewsEntity;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostNewsRepository extends CrudRepository<PostNewsEntity, UUID> {
    List<PostNewsEntity> findByArchivedIsTrue();

    List<PostNewsEntity> findByTypeOfNewsAndArchivedIsFalse(TypeOfNews typeOfNews);


    @Modifying
    @Query("UPDATE news SET views = views+1 WHERE id = :id ")
    void updateViews(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE news SET archived = :arg WHERE id = :id ")
    void changeParamArchive(Boolean arg, @Param("id") UUID id);

    @Query(value = "select * from news order by random() limit 1",nativeQuery = true)
    PostNewsEntity randomNewsFromDataBase();

}
