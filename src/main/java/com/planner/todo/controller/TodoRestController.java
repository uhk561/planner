package com.planner.todo.controller;

import com.planner.todo.dto.create.CreateTodoRequest;
import com.planner.todo.dto.create.CreateTodoResponse;
import com.planner.todo.dto.get.GetTodoResponse;
import com.planner.todo.dto.update.UpdateTodoRequest;
import com.planner.todo.dto.update.UpdateTodoResponse;
import com.planner.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 선택 일정 조회 (단 건 조회)
    @GetMapping("/{id}")
    public GetTodoResponse getTodo(@PathVariable long id) {
       return todoService.getTodo(id);
    }

    // 전체 일정 조회(다 건 조회)
    @GetMapping
    public List<GetTodoResponse> getAllTodo(
            @RequestParam(required = false)String userName) {
        if (userName != null &&  !userName.isEmpty()) {
            return todoService.getTodoByUser(userName);
        }
        return todoService.getAll();
    }

    // 일정 수정
    @PutMapping("/update/{id}")
    public UpdateTodoResponse updateTodo(
        @PathVariable long id,
        @RequestBody UpdateTodoRequest request
    ) {
        return todoService.update(id, request);
    }
}
