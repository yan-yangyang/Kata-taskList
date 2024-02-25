package com.codurance.training.tasks;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;

import java.io.PrintWriter;

public class Show {
    private final CheckList checkList;
    private final PrintWriter out;

    public Show(CheckList checkList, PrintWriter out) {
        this.checkList = checkList;
        this.out = out;
    }

    public void show() {
        for (Project project : checkList.projects) {
            out.println(project.name);
            for (Task task : project.tasks) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId().value(), task.getDescription());
            }
            out.println();
        }
    }
}
