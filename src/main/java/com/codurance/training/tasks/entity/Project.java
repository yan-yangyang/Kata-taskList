package com.codurance.training.tasks.entity;

import java.util.ArrayList;
import java.util.List;

public class Project {

    public final ProjectName name;
    public final List<Task> tasks;

    public Project(ProjectName name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public static Project of(ProjectName name) {
        return new Project(name);
    }
}
