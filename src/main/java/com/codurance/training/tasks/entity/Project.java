package com.codurance.training.tasks.entity;

import com.codurance.training.tasks.usecase.port.ImmutableTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project {

    private final ProjectName name;
    private final List<Task> tasks;

    public Project(ProjectName name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public static Project of(ProjectName name) {
        return new Project(name);
    }

    public ProjectName getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks.stream().map(ImmutableTask::create).toList();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setDone(String taskId, boolean done) {
        for (Task task: tasks) {
            if (task.getId().value() == Integer.parseInt(taskId)) {
                task.setDone(done);
            }
        }
    }
}
