package com.planner.comment.service;

import com.planner.comment.dto.CreateCommentRequest;
import com.planner.comment.dto.CreateCommentResponse;
import com.planner.comment.entity.CommentEntity;
import com.planner.comment.repository.CommentRepository;
import com.planner.todo.dto.create.CreateTodoResponse;
import com.planner.todo.entity.TodoEntity;
import com.planner.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public CreateCommentResponse save(Long todoId, CreateCommentRequest request) {
        TodoEntity todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다."));
        CommentEntity comment = new CommentEntity(
                todo,
                request.getContent(),
                request.getUserName(),
                request.getPassword()
        );
        CommentEntity saveComment =  commentRepository.save(comment);
        return new CreateCommentResponse(
                saveComment.getId(),
                saveComment.getContent(),
                saveComment.getUserName(),
                saveComment.getCreatedAt(),
                saveComment.getModifiedAt()
        );
    }
}
