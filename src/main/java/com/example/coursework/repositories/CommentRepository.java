package com.example.coursework.repositories;

import com.example.coursework.dto.CommentsDto;
import com.example.coursework.models.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<CommentsEntity,Long> {

}
