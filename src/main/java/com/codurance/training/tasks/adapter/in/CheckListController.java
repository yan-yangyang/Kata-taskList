package com.codurance.training.tasks.adapter.in;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectInput;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskInput;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorInput;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpInput;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneInput;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;

import java.io.PrintWriter;
import java.util.List;

import static com.codurance.training.tasks.frame.CheckListApp.CHECK_LIST_ID;

public class CheckListController {
    private final PrintWriter out;
    private final ShowUseCase showUseCase;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final SetDoneUseCase setDoneUseCase;
    private final ErrorUseCase errorUseCase;
    private final HelpUseCase helpUseCase;

    public CheckListController(PrintWriter out,
                               ShowUseCase showUseCase,
                               AddProjectUseCase addProjectUseCase,
                               AddTaskUseCase addTaskUseCase,
                               SetDoneUseCase setDoneUseCase,
                               ErrorUseCase errorUseCase,
                               HelpUseCase helpUseCase) {
        this.out = out;
        this.showUseCase = showUseCase;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.setDoneUseCase = setDoneUseCase;
        this.errorUseCase = errorUseCase;
        this.helpUseCase = helpUseCase;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                whenShow();
                break;
            case "add":
                whenAdd(commandRest);
                break;
            case "check":
                whenSetDone(commandRest, true);
                break;
            case "uncheck":
                whenSetDone(commandRest, false);
                break;
            case "help":
                WhenHelp();
                break;
            default:
                WhenError(command);
                break;
        }
    }

    private void whenShow() {
        ShowInput showInput = new ShowInput();
        showInput.setCheckListId(CHECK_LIST_ID);
        var showOutput = showUseCase.execute(showInput);

        for (Project project : showOutput.getProjects()) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId().value(), task.getDescription());
            }
            out.println();
        }
    }
    private void whenAdd(String[] commandRest) {
        String[] subcommandRest = commandRest[1].split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            whenAddProject(subcommandRest);

        } else if (subcommand.equals("task")) {
            whenAddTask(subcommandRest);
        }
    }
    private void whenAddProject(String[] subcommandRest) {
        AddProjectInput addProjectInput = new AddProjectInput();
        addProjectInput.setCheckListId(CHECK_LIST_ID);
        addProjectInput.setProjectName(subcommandRest[1]);
        addProjectUseCase.execute(addProjectInput);
    }
    private void whenAddTask(String[] subcommandRest) {
        String[] projectTask = subcommandRest[1].split(" ", 2);

        AddTaskInput addTaskInput = new AddTaskInput();
        addTaskInput.setCheckListId(CHECK_LIST_ID);
        addTaskInput.setProjectName(projectTask[0]);
        addTaskInput.setTaskDescription(projectTask[1]);
        addTaskUseCase.execute(addTaskInput);
    }


    private void whenSetDone(String[] commandRest, boolean done) {
        SetDoneInput setTrueInput = new SetDoneInput();
        setTrueInput.setCheckListId(CHECK_LIST_ID);
        setTrueInput.setTaskId(commandRest[1]);
        setTrueInput.setDone(done);
        setDoneUseCase.execute(setTrueInput);
    }
    private void WhenHelp() {
        HelpInput helpInput = new HelpInput();
        var helpOutput = helpUseCase.execute(helpInput);
        List<String> helpResponse = helpOutput.getResponse();
        out.println(helpResponse.get(0));
        for (int i = 1 ; i < helpResponse.size() ; i++) {
            out.println("  " + helpResponse.get(i));
        }
    }
    private void WhenError(String command) {
        ErrorInput errorInput = new ErrorInput();
        errorInput.setCommand(command);
        var errorOutput = errorUseCase.execute(errorInput);
        out.println(errorOutput.getMessage());
    }
}
