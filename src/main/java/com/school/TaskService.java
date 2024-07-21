package com.school;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskService {

    List<Task> tasks = new ArrayList<>();

    TaskService() {}

    void addTask(Task task) {
        tasks.add(task);
    }

    void updateTask(Task task) {
        tasks.forEach((t) -> {/*Do thing*/});
    }

    void deleteTask(Task task) {
//        tasks.removeIf((t) -> {/*Do thing*/});
    }
}
