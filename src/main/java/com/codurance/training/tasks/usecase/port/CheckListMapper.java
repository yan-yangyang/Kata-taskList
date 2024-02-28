package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.Project;

import java.util.List;
import java.util.Optional;

public class CheckListMapper {
    public static CheckList toDomain(CheckListDto checkListDto) {
        List<Project> projects = checkListDto.getProjects().stream().map(ProjectMapper::toDomain).toList();
        CheckList checkList = new CheckList(checkListDto.getId());
        for (Project project: projects) {
            checkList.addProject(project);
        }
        return checkList;
    }

    public static CheckListDto toDto(CheckList checkList) {
        List<ProjectDto> projectDtos = checkList.getProjects().stream().map(ProjectMapper::toDto).toList();
        return new CheckListDto(checkList.getId().id(), projectDtos);
    }
}
