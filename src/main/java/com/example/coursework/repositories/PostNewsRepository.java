package com.example.coursework.repositories;

import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.models.TypeOfNews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostNewsRepository extends CrudRepository<PostNewsEntity, UUID> {
    List<PostNewsEntity> findByArchivedIsTrue();

    List<PostNewsEntity> findByTypeOfNewsAndArchivedIsFalseAndApprovedIsTrue(TypeOfNews typeOfNews);

    List<PostNewsEntity> findByApprovedFalse();
    void deleteByAnons(String anons);

    PostNewsEntity findByAnons(String anons);

    @Modifying
    @Query("UPDATE news SET views = views+1 WHERE id = :id ")
    void updateViews(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE news SET archived = :arg WHERE id = :id ")
    void changeParamArchive(Boolean arg, @Param("id") UUID id);

    @Modifying
    @Query("UPDATE news SET approved = :arg WHERE id = :id ")
    void publishNews(Boolean arg, @Param("id") UUID id);

    @Query(value = "select * from news where approved = true and archived = false order by random() limit 1",
            nativeQuery = true)
    PostNewsEntity randomNewsFromDataBase();

    @Query(value = "select * from news where type_of_news = 'World' and approved = true and archived = false order by random() limit 1",
            nativeQuery = true)
    PostNewsEntity randomWorldNewsFromDataBase();

    @Query(value = "select * from news where type_of_news = 'Design' and approved = true and archived = false order by random() limit 1",
            nativeQuery = true)
    PostNewsEntity randomDesignNewsFromDataBase();

}
