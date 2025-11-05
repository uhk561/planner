package com.planner.comment.repository;

import com.planner.comment.entity.CommentEntity;
import com.planner.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


}
