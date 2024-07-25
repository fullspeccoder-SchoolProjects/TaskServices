package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskService {

    List<Task> tasks;
    public TaskService() {
        tasks = new ArrayList<Task>();
    }

    public Task getTask(String id) {
        if(id == null || id.isEmpty()) {
            throw new NullPointerException("Task id cannot be null or empty");
        }
        final Task task;
        for(Task t : tasks) {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Task with id " + id + " not found");
    }

    void addTask(String id, String name, String description) {
        if(id == null) {
            throw new NullPointerException("Task id cannot be null");
        }
        tasks.forEach((task) -> {
            if (task.getId().equals(id)) {
                throw new IllegalArgumentException("ID exists in another task");
            }
        });
        Task newTask = new Task(id, name, description);
        tasks.add(newTask);
    }

    void addTask(Task task) {
        tasks.forEach((t) -> {
            if (t.getId().equals(task.getId())) {
                throw new IllegalArgumentException("ID exists in another task");
            }
        });
        tasks.add(new Task(task, task.getId()));
    }

    void addTask(Task task, String id) {
        tasks.forEach((t) -> {
            if (t.getId().equals(id)) {
                throw new IllegalArgumentException("Cannot add task because id exists in another task");
            }
        });
        Task newTask = new Task(id, task.getName(), task.getDescription());
        tasks.add(newTask);
    }

    void addMultipleTasks(ArrayList<Task> taskList) {
        tasks.forEach((t) -> {
            taskList.forEach((t2) -> {
               if(t2.getId().equals(t.getId())) {
                   throw new IllegalArgumentException("Task already exists in another task");
               }
            });
        });
        tasks.addAll(taskList);
    }

    void deleteTask(Task task) {
        if(!tasks.removeIf(t -> t.getId().equals(task.getId()))) {
            throw new IllegalArgumentException("ID does not exist");
        }
    }

    void deleteTask(String id) {
        if (id == null || id.isEmpty()) {
            throw new NullPointerException("Task id is null or empty");
        }
        if(!tasks.removeIf(t -> t.getId().equals(id))) {
            throw new IllegalArgumentException("ID does not exist");
        }
    }

    void updateTask(Task task, String name, String description) {
        AtomicBoolean updatedContact = new AtomicBoolean(false);
        tasks.forEach((t) -> {
            if(t.getId().equals(task.getId())) {
                t.setName(name);
                t.setDescription(description);
                updatedContact.set(true);
            }
        });
        if(!updatedContact.get()) {
            throw new IllegalArgumentException("ID does not exist");
        }
    }

    void updateTask(String id, String name, String description) {
        if(id == null || id.isEmpty()) {
            throw new NullPointerException("ID is null or empty");
        }
        AtomicBoolean updatedContact = new AtomicBoolean(false);
        tasks.forEach((task) -> {
            if(task.getId().equals(id)) {
                task.setName(name);
                task.setDescription(description);
                updatedContact.set(true);
            }
        });
        if(!updatedContact.get()) {
            throw new IllegalArgumentException("ID does not exist");
        }
    }
}
