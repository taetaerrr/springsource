package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTest() {
        // 10번 쓰겠다
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    Todo todo = Todo.builder()
                            .title("할 일" + i)
                            .build();

                    // 새로 삽입된 return
                    System.out.println(todoRepository.save(todo));
                });
    }

    @Test
    public void selectAllTest() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void selectOneTest() {
        System.out.println(todoRepository.findById(3L).get());
    }

    @Test
    public void updateTest() {
        // completed, important 수정
        Todo todo = todoRepository.findById(3L).get();
        todo.setCompleted(true);
        todo.setImportant(true);
        todoRepository.save(todo);
    }

    @Test
    public void deleteTest() {
        todoRepository.deleteById(10L);
    }

    @Test
    public void completedTest() {
        // 완료된 todos를 뽑겠다
        todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
        // 미완료된 todos를 뽑겠다
        todoRepository.findByCompleted(false).forEach(todo -> System.out.println(todo));
    }
}
