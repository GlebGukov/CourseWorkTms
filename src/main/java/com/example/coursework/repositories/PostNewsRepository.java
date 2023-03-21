package com.example.coursework.repositories;

import com.example.coursework.TypeOfNews;
import com.example.coursework.models.PostNewsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostNewsRepository extends CrudRepository<PostNewsEntity, Long> {
    Iterable<PostNewsEntity> findByArchivedIsTrue();

    Iterable<PostNewsEntity> findByTypeOfNewsAndArchivedIsFalse(TypeOfNews typeOfNews);


    @Modifying
    @Query("UPDATE news SET views = views+1 WHERE id = :id ")
    void updateViews(@Param("id") long id);

    @Modifying
    @Query("UPDATE news SET archived = :arg WHERE id = :id ")
    void archived(Boolean arg, @Param("id") long id);

}
