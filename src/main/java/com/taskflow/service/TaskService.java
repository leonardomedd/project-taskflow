package com.taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.taskflow.model.Task;
import com.taskflow.model.User;
import com.taskflow.repository.TaskRepository;
import com.taskflow.websocket.TaskWebSocketHandler;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskWebSocketHandler webSocketHandler;

    public Task createTask(String title, int points, Long userId) {
        String baseUsername = "user_" + userId;
        String uniqueUsername = baseUsername;
        int suffix = 1;

        while (userService.existsByUsername(uniqueUsername)) {
            uniqueUsername = baseUsername + "_" + suffix++;
        }

        User user = userService.createUser(uniqueUsername);

        Task task = new Task();
        task.setTitle(title);
        task.setStatus("To Do");
        task.setPoints(points);
        task.setUser(user);
        task = taskRepository.save(task);
        notifyClients("Nova tarefa criada: " + task.getTitle());
        return task;
    }

    public Task updateStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.setStatus(status);
        if (status.equals("Done")) {
            userService.addPoints(task.getUser().getId(), task.getPoints());
        }
        task = taskRepository.save(task);
        notifyClients("Tarefa atualizada: " + task.getTitle() + " para " + status);
        return task;
    }

    private void notifyClients(String message) {
        try {
            for (WebSocketSession session : webSocketHandler.getSessions()) {
                session.sendMessage(new TextMessage(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } }