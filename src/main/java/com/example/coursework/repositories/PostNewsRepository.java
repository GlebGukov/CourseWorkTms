package com.example.coursework.repositories;

import com.example.coursework.models.PostNews;
import org.springframework.data.repository.CrudRepository;

public interface PostNewsRepository extends CrudRepository<PostNews,Long> {

    Iterable<PostNews> findByTitleContainingOrAnonsContaining(String title, String anons);


}
