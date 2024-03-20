package com.codurance.training.tasks.usecase.port.in.show;

import com.codurance.training.tasks.usecase.port.ProjectDto;
import com.codurance.training.tasks.usecase.port.ProjectPo;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.List;

public class ShowOutPut extends CqrsOutput<ShowOutPut> {
    private List<ProjectDto> projects;
    public ShowOutPut SetProjects(List<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }
}
