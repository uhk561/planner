package com.planner.todo.dto.create;

import lombok.Getter;

@Getter
public class CreateTodoRequest {

    private String title;
    private String content;
    private String userName;
    private String password;
}
