package ch.heigvd;

import io.javalin.http.Context;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TodoManager {
    // In-memory storage of todos, ConcurrentHashMap is used to make the storage
    // thread-safe
    private ConcurrentHashMap<String, TodoItem> todos = new ConcurrentHashMap<>();

    public TodoManager() {
    }

    public void createTodo(Context ctx) {
        TodoItem todo = ctx.bodyAsClass(TodoItem.class);
        todo.setId(UUID.randomUUID().toString());
        todos.put(todo.getId(), todo);
        ctx.status(201).json(todo);
    }

    public void getAllTodos(Context ctx) {
        ctx.json(todos.values());
    }

    public void getTodo(Context ctx) {
        String id = ctx.pathParam("id");
        TodoItem todo = todos.get(id);
        if (todo != null) {
            ctx.json(todo);
        } else {
            ctx.status(404);
        }
    }

    public void updateTodo(Context ctx) {
        String id = ctx.pathParam("id");
        TodoItem updatedTodo = ctx.bodyAsClass(TodoItem.class);
        if (todos.containsKey(id)) {
            updatedTodo.setId(id);
            todos.put(id, updatedTodo);
            ctx.json(updatedTodo);
        } else {
            ctx.status(404);
        }
    }

    public void deleteTodo(Context ctx) {
        String id = ctx.pathParam("id");
        if (todos.remove(id) != null) {
            ctx.status(204);
        } else {
            ctx.status(404);
        }
    }
}
