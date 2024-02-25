package com.codurance.training.tasks.entity;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckList {
    public final List<Project> projects;

    public CheckList() {
        this.projects = new ArrayList<>();
    }

    public void add(Project project) {
        projects.add(project);
    }

    public Optional<Project> get(ProjectName name) {
        return projects.stream().filter(x -> x.name.equals(name)).findAny();
    }
}
