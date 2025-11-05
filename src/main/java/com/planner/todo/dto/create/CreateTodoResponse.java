package com.planner.todo.dto.create;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateTodoResponse {

    private final long id;
    private final String title;
    private final String content;
    private final String userName;
    //private final String password; 비밀번호 반환 X
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 일정 생성(작성)
    public CreateTodoResponse(long id, String title, String content, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        //this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
