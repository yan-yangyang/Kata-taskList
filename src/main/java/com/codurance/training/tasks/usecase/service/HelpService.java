package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.usecase.port.in.help.HelpInput;
import com.codurance.training.tasks.usecase.port.in.help.HelpOutPut;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;

import java.util.ArrayList;
import java.util.List;

public class HelpService implements HelpUseCase {
    public HelpService() {
    }

    @Override
    public HelpOutPut execute(HelpInput input) {
        List<String> helpResponse = new ArrayList<>();
        helpResponse.add("Commands:");
        helpResponse.add("show");
        helpResponse.add("add project <project name>");
        helpResponse.add("add task <project name> <task description>");
        helpResponse.add("check <task ID>");
        helpResponse.add("uncheck <task ID>");

        return HelpOutPut.create(HelpOutPut.class).setExitCode(ExitCode.SUCCESS).setResponse(helpResponse);
    }
}
