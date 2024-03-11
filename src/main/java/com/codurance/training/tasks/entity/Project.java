package com.codurance.training.tasks.entity;

import java.util.ArrayList;
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
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
