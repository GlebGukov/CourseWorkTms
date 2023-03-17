package com.example.coursework.repositories;

import com.example.coursework.models.PostNews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostNewsRepository extends CrudRepository<PostNews, Long> {

    Iterable<PostNews> findByFullTextContaining(String typeNews);

    @Modifying
    @Query("UPDATE PostNews SET views = views+1 WHERE id = :id ")
    void updateViews(@Param("id") long id);

}
