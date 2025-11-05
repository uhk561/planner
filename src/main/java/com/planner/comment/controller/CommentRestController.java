package com.planner.comment.controller;

import com.planner.comment.dto.CreateCommentRequest;
import com.planner.comment.dto.CreateCommentResponse;
import com.planner.comment.service.CommentService;
import com.planner.todo.dto.create.CreateTodoRequest;
import com.planner.todo.dto.create.CreateTodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/{todoId}/comment")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long todoId,
            @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(todoId,request));
    }
}
