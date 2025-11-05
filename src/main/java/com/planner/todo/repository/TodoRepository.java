package com.planner.todo.repository;

import com.planner.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByUserName(String userName);
}
