package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.CheckListRepository;
import com.codurance.training.tasks.usecase.setdone.SetDoneInput;
import com.codurance.training.tasks.usecase.setdone.SetDoneUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class SetDoneService implements SetDoneUseCase {

    private final CheckListRepository repository;

    public SetDoneService(CheckListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(SetDoneInput input) {
        CheckList checkList = repository.findById(CheckListId.of(input.checkListId())).orElse(null);
        if (null == checkList) {
            return CqrsOutput.create().setExitCode(ExitCode.FAILURE);
        }
        if (!checkList.containTask(input.taskId())) {
            return CqrsOutput.create().setExitCode(ExitCode.FAILURE).setMessage(format("Could not find a task with an ID of %s.", input.taskId()));
        }
        checkList.setDone(input.taskId(), input.done());

        repository.save(checkList);

        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS);
    }
}
