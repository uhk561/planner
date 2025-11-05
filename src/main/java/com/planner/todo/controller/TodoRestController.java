package com.planner.todo.controller;

import com.planner.todo.dto.CreateTodoRequest;
import com.planner.todo.dto.CreateTodoResponse;
import com.planner.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRestController {

    private final TodoService todoService;

    // 일정 생성(작성)
    @PostMapping("/create")
    public CreateTodoResponse createTodo(@RequestBody CreateTodoRequest request) {
        return todoService.save(request);
    }
}
