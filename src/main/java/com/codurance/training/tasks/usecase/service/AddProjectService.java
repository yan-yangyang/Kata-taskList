package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectInput;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.core.usecase.UseCaseFailureException;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public class AddProjectService implements AddProjectUseCase {
    private final CheckListRepository repository;

    public AddProjectService(CheckListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddProjectInput input) {
        try {
            CheckList checkList = repository.findById(CheckListId.of(input.checkListId())).orElse(null);
            if (null == checkList) {
                return CqrsOutput.create().setExitCode(ExitCode.FAILURE);
            }
            checkList.addProject(Project.of(ProjectName.of(input.projectName())));

            repository.save(checkList);

            return CqrsOutput.create().setExitCode(ExitCode.SUCCESS);
        } catch (Exception e) {
            throw new UseCaseFailureException(e);
        }
    }
}
