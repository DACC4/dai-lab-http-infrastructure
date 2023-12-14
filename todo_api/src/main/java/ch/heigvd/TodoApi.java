package ch.heigvd;

import io.javalin.Javalin;

public class TodoApi {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);

        TodoManager todoManager = new TodoManager();

        app.post("/todos", todoManager::createTodo);
        app.get("/todos", todoManager::getAllTodos);
        app.get("/todos/{id}", todoManager::getTodo);
        app.put("/todos/{id}", todoManager::updateTodo);
        app.delete("/todos/{id}", todoManager::deleteTodo);
    }
}
