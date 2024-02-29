package com.codurance.training.tasks.adapter.out.presenter;

import com.codurance.training.tasks.usecase.port.ProjectDto;
import com.codurance.training.tasks.usecase.port.TaskDto;

import java.io.PrintWriter;
import java.util.List;

public class ShowPresenter {
    public void present(PrintWriter out, List<ProjectDto> dtos) {
        for (ProjectDto project : dtos) {
            out.println(project.getName());
            for (TaskDto task : project.getTasks()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
