package com.planner.todo.entity;

import com.planner.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "todos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    private String userName;

    private String password;

    public TodoEntity (String title, String content, String userName, String password) {
        this.title =title;
        this.content =content;
        this.userName =userName;
        this.password =password;
    }

    public void update(String title,String userName) {
        if(title != null) { // 만약 제목과 작성자명 중 하나만 변경하려고 둘 중 하나의 값만 보냈을 때 null이면 원래 데이터값
            this.title =title;
        }

        if(userName != null) {
            this.userName =userName;
        }
    }

}
