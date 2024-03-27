package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.Project;

import java.util.List;

public class CheckListMapper {
    public static CheckList toDomain(CheckListPo checkListPo) {
        List<Project> projects = checkListPo.getProjects().stream().map(ProjectMapper::toDomain).toList();
        CheckList checkList = new CheckList(checkListPo.getId());
        for (Project project: projects) {
            checkList.addProject(project);
        }
        return checkList;
    }

    public static CheckListPo toPo(CheckList checkList) {
        List<ProjectPo> projectPos = checkList.getProjects().stream().map(ProjectMapper::toPo).toList();
        return new CheckListPo(checkList.getId().id(), projectPos);

    }public static CheckListDto toDto(CheckList checkList) {
        List<ProjectDto> projectDtos = checkList.getProjects().stream().map(ProjectMapper::toDto).toList();
        return new CheckListDto(checkList.getId().id(), projectDtos);
    }
}
