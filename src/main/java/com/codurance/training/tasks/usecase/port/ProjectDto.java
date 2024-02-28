package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectDto {

    private final String name;
    private final List<TaskDto> tasks;

    public ProjectDto(String name, List<TaskDto> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
    public String getName() {
        return name;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }
}
