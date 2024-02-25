package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;

import java.io.PrintWriter;

public class Check {
    private final CheckList checkList;
    private final PrintWriter out;

    public Check(CheckList checkList, PrintWriter out) {
        this.checkList = checkList;
        this.out = out;
    }
    public void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Project project : checkList.projects) {
            for (Task task : project.tasks) {
                if (task.getId().value() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
