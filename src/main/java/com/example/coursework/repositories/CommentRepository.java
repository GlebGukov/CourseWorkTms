package com.example.coursework.repositories;

import com.example.coursework.models.CommentsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends CrudRepository<CommentsEntity, Long> {

    List<CommentsEntity> getCommentsEntitiesByPostNewsId(UUID newsId);

    void deleteAllByCommentContaining(String arg);
}
