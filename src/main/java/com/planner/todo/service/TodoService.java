package com.planner.todo.service;

import com.planner.todo.dto.create.CreateTodoRequest;
import com.planner.todo.dto.create.CreateTodoResponse;
import com.planner.todo.dto.get.GetTodoResponse;
import com.planner.todo.dto.update.UpdateTodoRequest;
import com.planner.todo.dto.update.UpdateTodoResponse;
import com.planner.todo.entity.TodoEntity;
import com.planner.todo.repository.TodoRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 일정 생성(작성)
    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {
        TodoEntity todo = new TodoEntity(
                request.getTitle(),
                request.getContent(),
                request.getUserName(),
                request.getPassword()
        );
        TodoEntity saveTodo =  todoRepository.save(todo);
        return new CreateTodoResponse(
                saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getContent(),
                saveTodo.getUserName(),
                saveTodo.getCreatedAt(),
                saveTodo.getModifiedAt()
        );

    }
    // 선택 일정 조회(단 건 조회)
    @Transactional(readOnly = true)
    public GetTodoResponse getTodo(long id) {
        TodoEntity todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 하지않습니다.")
        );
        return new GetTodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getUserName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    // 전체 일정 조회(다 건 조회)
    @Transactional(readOnly = true)
    public List<GetTodoResponse> getAll() {
        List<TodoEntity> todos = todoRepository.findAll();

        List<GetTodoResponse> dtos = new ArrayList<>();
        for (TodoEntity todo : todos) {
            GetTodoResponse dto = new GetTodoResponse(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getContent(),
                    todo.getUserName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 작성자명을 기준으로 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetTodoResponse> getTodoByUser(String userName) {
        List<TodoEntity> todos = todoRepository.findByUserNameOrderByModifiedAtDesc(userName);

        List<GetTodoResponse> dtos = new ArrayList<>();
        for (TodoEntity todo : todos) {
            GetTodoResponse dto = new GetTodoResponse(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getContent(),
                    todo.getUserName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 일정 수정
    @Transactional
    public UpdateTodoResponse patch(Long id, UpdateTodoRequest request) {
        TodoEntity todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 하지않습니다.")
        );

        if (!todo.getPassword().equals(request.getPassword())) { // 비밀번호 검증
            throw new IllegalStateException("비밀번호가 올바르지 않습니다.");
        }

        todo.update(
                request.getTitle(),
                request.getUserName()
        );
        return new UpdateTodoResponse(
                todo.getTitle(),
                todo.getUserName(),
                todo.getModifiedAt()
        );

    }

    @Transactional
    public void deleteTodo(Long id) {
        boolean existence = todoRepository.existsById(id);

        // 삭제하려는 일정이 없는 경우
        if (!existence) {
            throw new IllegalStateException("해당 일정이 존재하지 하지않습니다.");
        }

        // 삭제하려는 일정이 있는 경우
        todoRepository.deleteById(id);
    }

}
