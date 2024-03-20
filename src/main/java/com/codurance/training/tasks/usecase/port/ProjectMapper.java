package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.util.List;

public class ProjectMapper {
    public static Project toDomain(ProjectPo projectPo) {
        List<Task> tasks = projectPo.getTasks().stream().map(TaskMapper::toDomain).toList();
        Project project = new Project(ProjectName.of(projectPo.getName()));
        for (Task task: tasks) {
            project.addTask(task);
        }
        return project;
    }

    public static ProjectPo toPo(Project project) {
        List<TaskPo> tasks = project.getTasks().stream().map(TaskMapper::toPo).toList();
        return new ProjectPo(project.getName().value(), tasks);
    }
    public static ProjectDto toDto(Project project) {
        List<TaskDto> tasks = project.getTasks().stream().map(TaskMapper::toDto).toList();
        return new ProjectDto(project.getName().value(), tasks);
    }
}
