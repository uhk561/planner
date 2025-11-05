package com.planner.comment.entity;

import com.planner.common.entity.BaseEntity;
import com.planner.todo.entity.TodoEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoId")
    private TodoEntity todo;

    private String content;

    private String userName;

    private String password;

    public CommentEntity (TodoEntity todo, String content, String userName, String password) {
        this.todo =todo;
        this.content =content;
        this.userName =userName;
        this.password =password;
    }
}
