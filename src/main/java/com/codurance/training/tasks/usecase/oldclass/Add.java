package com.codurance.training.tasks.usecase.oldclass;

import com.codurance.training.tasks.entity.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class Add {
    private final CheckList checkList;
    private final PrintWriter out;
    private static int lastId = 0;

    public Add(CheckList checkList, PrintWriter out) {
        this.checkList = checkList;
        this.out = out;
    }

    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        checkList.addProject(Project.of(ProjectName.of(name)));
    }

    private void addTask(String projectName, String description) {
        Optional<Project> project = checkList.get(ProjectName.of(projectName));
        if (project.isEmpty()) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        List<Task> projectTasks = project.get().getTasks();
        projectTasks.add(new Task(TaskId.of(nextId()), description, false));
    }

    private long nextId() {
        return ++lastId;
    }
}
