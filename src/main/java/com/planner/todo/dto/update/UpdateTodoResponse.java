package com.planner.todo.dto.update;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponse {

    private final String title;
    private final String userName;
    private final LocalDateTime modifiedAt;

    public UpdateTodoResponse(String title, String userName, LocalDateTime modifiedAt) {
        this.title = title;
        this.userName = userName;
        this.modifiedAt = modifiedAt;
    }
}
