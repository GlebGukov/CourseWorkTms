package com.example.coursework.repositories;

import com.example.coursework.models.CommentsEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentsEntity, Long> {

}
