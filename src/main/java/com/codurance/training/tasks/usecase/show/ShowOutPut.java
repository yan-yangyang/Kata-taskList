package com.codurance.training.tasks.usecase.show;

import com.codurance.training.tasks.entity.Project;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.List;

public class ShowOutPut extends CqrsOutput<ShowOutPut> {
    private List<Project> projects;
    public ShowOutPut SetProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
