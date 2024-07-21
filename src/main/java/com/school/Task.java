package com.school;

public class Task {
    String id;
    String name;
    String description;
    static int classId = 1000;

    Task(String id, String name, String description) {
        this.id = String.format("%s%n", classId++);
        this.name = name;
        this.description = description;
    }

    Task(Task task) {
        this.id = String.format("%s%n", classId++);
        this.name = task.name;
        this.description = task.description;
    }
}
