package com.nanoapp.nano.services;

import com.nanoapp.nano.dto.TodoRequest;
import com.nanoapp.nano.dto.TodoResponse;
import com.nanoapp.nano.exception.ResourceNotFoundException;
import com.nanoapp.nano.model.Todo;
import com.nanoapp.nano.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::mapToTodoResponse)
                .toList();
    }

    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        return mapToTodoResponse(todo);
    }

    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo todo = new Todo(todoRequest.title(), todoRequest.description());
        Todo savedTodo = todoRepository.save(todo);
        return mapToTodoResponse(savedTodo);
    }

    public TodoResponse updateTodo(Long id, TodoRequest todoRequest) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        
        Todo updatedTodo = todoRepository.save(todo);
        return mapToTodoResponse(updatedTodo);
    }

    public TodoResponse toggleTodoCompletion(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        
        todo.setCompleted(!todo.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return mapToTodoResponse(updatedTodo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    private TodoResponse mapToTodoResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
