package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.port.CheckListMapper;
import com.codurance.training.tasks.usecase.port.ProjectMapper;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowOutPut;
import tw.teddysoft.ezddd.core.usecase.ExitCode;

public class ShowService implements ShowUseCase {
    private final CheckListRepository repository;

    public ShowService(CheckListRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShowOutPut execute(ShowInput input) {
        CheckList checkList = repository.findById(CheckListId.of(input.checkListId())).orElse(null);
        if (null == checkList) {
            return ShowOutPut.create(ShowOutPut.class).setExitCode(ExitCode.FAILURE);
        }

        return ShowOutPut
                .create(ShowOutPut.class)
                .setExitCode(ExitCode.SUCCESS)
                .setCheckList(CheckListMapper.toDto(checkList))
                .setProjects(checkList.getProjects().stream().map(ProjectMapper::toDto).toList());
    }
}
