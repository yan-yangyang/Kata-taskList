package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.usecase.CheckListRepository;
import com.codurance.training.tasks.usecase.addtask.AddTaskInput;
import com.codurance.training.tasks.usecase.addtask.AddTaskUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.core.usecase.UseCaseFailureException;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class AddTaskService implements AddTaskUseCase {
    private final CheckListRepository repository;

    public AddTaskService(CheckListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddTaskInput input) {
        try {
            CheckList checkList = repository.findById(CheckListId.of(input.checkListId())).orElse(null);
            if (null == checkList) {
                return CqrsOutput.create().setExitCode(ExitCode.FAILURE);
            }
            if (!checkList.contains(input.projectName())) {
                return CqrsOutput.create().setExitCode(ExitCode.FAILURE).setMessage(format("Could not find a project with the name \"%s\".\n", input.projectName()));
            }
            checkList.addTask(ProjectName.of(input.projectName()), input.taskDescription());

            repository.save(checkList);

            return CqrsOutput.create().setExitCode(ExitCode.SUCCESS);
        } catch (Exception e) {
            throw new UseCaseFailureException(e);
        }
    }
}
