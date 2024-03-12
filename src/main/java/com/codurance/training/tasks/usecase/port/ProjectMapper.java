package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.util.List;

public class ProjectMapper {
    public static Project toDomain(ProjectDto projectDto) {
        List<Task> tasks = projectDto.getTasks().stream().map(TaskMapper::toDomain).toList();
        Project project = new Project(ProjectName.of(projectDto.getName()));
        for (Task task: tasks) {
            project.addTask(task);
        }
        return project;
    }

    public static ProjectDto toDto(Project project) {
        List<TaskDto> tasks = project.getTasks().stream().map(TaskMapper::toDto).toList();
        return new ProjectDto(project.getName().value(), tasks);
    }
}
