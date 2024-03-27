package com.codurance.training.tasks.usecase.port.in.show;

import com.codurance.training.tasks.usecase.port.CheckListDto;
import com.codurance.training.tasks.usecase.port.ProjectDto;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.List;

public class ShowOutPut extends CqrsOutput<ShowOutPut> {
    private List<ProjectDto> projects;
    private CheckListDto checkListDto;
    public ShowOutPut setProjects(List<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    public ShowOutPut setCheckList(CheckListDto checkList) {
        this.checkListDto = checkList;
        return this;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public CheckListDto getCheckListDto() {
        return checkListDto;
    }
}
