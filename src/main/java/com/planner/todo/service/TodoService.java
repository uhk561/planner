package com.planner.todo.service;

import com.planner.todo.dto.CreateTodoRequest;
import com.planner.todo.dto.CreateTodoResponse;
import com.planner.todo.entity.TodoEntity;
import com.planner.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 일정 생성(작성)
    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {
        TodoEntity todo = new TodoEntity(
                request.getTitle(),
                request.getContent(),
                request.getUserName(),
                request.getPassword()
        );
        TodoEntity saveTodo =  todoRepository.save(todo);
        return new CreateTodoResponse(
                saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getContent(),
                saveTodo.getUserName(),
                saveTodo.getCreatedAt(),
                saveTodo.getModifiedAt()
        );
    }
}
