package com.planner.todo.controller;

import com.planner.todo.dto.create.CreateTodoRequest;
import com.planner.todo.dto.create.CreateTodoResponse;
import com.planner.todo.dto.delete.DeleteTodoRequest;
import com.planner.todo.dto.get.GetTodoResponse;
import com.planner.todo.dto.update.UpdateTodoRequest;
import com.planner.todo.dto.update.UpdateTodoResponse;
import com.planner.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRestController {

    private final TodoService todoService;

    // 일정 생성(작성)
    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }

    // 선택 일정 조회 (단 건 조회)
    @GetMapping("/{id}")
    public ResponseEntity<GetTodoResponse> getTodo(@PathVariable long id) {
       return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodo(id));
    }

    // 전체 일정 조회 및 작성자 기준 전체조회(다 건 조회)
    @GetMapping
    public ResponseEntity<List<GetTodoResponse>> getAllTodo(
            @RequestParam(required = false)String userName) {
        if (userName != null &&  !userName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoByUser(userName));
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTodoResponse> updateTodo(
        @PathVariable long id,
        @RequestBody UpdateTodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.patch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable long id,
            @RequestBody DeleteTodoRequest request) {
        todoService.deleteTodo(id, request.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
