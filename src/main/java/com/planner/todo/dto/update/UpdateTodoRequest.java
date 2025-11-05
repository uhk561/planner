package com.planner.todo.dto.update;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoRequest {

    private  String title;
    private  String userName;
    private String password;
}
