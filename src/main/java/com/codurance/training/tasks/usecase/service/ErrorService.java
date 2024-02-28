package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.usecase.error.ErrorInput;
import com.codurance.training.tasks.usecase.error.ErrorUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class ErrorService implements ErrorUseCase {

    @Override
    public CqrsOutput execute(ErrorInput input) {
        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage(format("I don't know what the command \"%s\" is.", input.command()));
    }
}
