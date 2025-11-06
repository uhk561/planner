package com.planner.comment.repository;

import com.planner.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    long countByTodo_Id(Long todoId);
}
