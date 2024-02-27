package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;

import java.io.PrintWriter;

public class ShowService {
    private final CheckList checkList;
    private final PrintWriter out;

    public ShowService(CheckList checkList, PrintWriter out) {
        this.checkList = checkList;
        this.out = out;
    }

    public void show() {
        for (Project project : checkList.getProjects()) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId().value(), task.getDescription());
            }
            out.println();
        }
    }
}
