package com.planner.comment.dto;

import com.planner.todo.entity.TodoEntity;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private String content;

    private String userName;

    private String password;

}
