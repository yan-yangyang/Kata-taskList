package com.codurance.training.tasks.usecase.port;

import java.util.List;

public class ProjectPo {

    private final String name;
    private final List<TaskPo> tasks;

    public ProjectPo(String name, List<TaskPo> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
    public String getName() {
        return name;
    }

    public List<TaskPo> getTasks() {
        return tasks;
    }
}
