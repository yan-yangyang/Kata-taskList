package com.codurance.training.tasks;

import com.codurance.training.tasks.adapter.InMemoryCheckListRepository;
import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.CheckListRepository;
import com.codurance.training.tasks.usecase.addproject.AddProjectInput;
import com.codurance.training.tasks.usecase.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.addtask.AddTaskInput;
import com.codurance.training.tasks.usecase.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.help.HelpUseCase;
import com.codurance.training.tasks.usecase.oldclass.*;
import com.codurance.training.tasks.usecase.oldclass.Error;
import com.codurance.training.tasks.usecase.service.*;
import com.codurance.training.tasks.usecase.setdone.SetDoneInput;
import com.codurance.training.tasks.usecase.setdone.SetDoneUseCase;
import com.codurance.training.tasks.usecase.show.ShowInput;
import com.codurance.training.tasks.usecase.show.ShowUseCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    public static final String CHECK_LIST_ID = UUID.randomUUID().toString();
    private final CheckList checkList = new CheckList(CHECK_LIST_ID);
    private final BufferedReader in;
    private final PrintWriter out;
    private final ShowUseCase showUseCase;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final SetDoneUseCase setDoneUseCase;
    private final ErrorUseCase errorUseCase;
    private final HelpUseCase helpUseCase;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        CheckListRepository checkListRepository = new InMemoryCheckListRepository();
        ShowUseCase showUseCase = new ShowService(checkListRepository);
        AddProjectUseCase addProjectUseCase = new AddProjectService(checkListRepository);
        AddTaskUseCase addTaskUseCase = new AddTaskService(checkListRepository);
        SetDoneUseCase setDoneUseCase = new SetDoneService(checkListRepository);
        ErrorUseCase errorUseCase = new ErrorService();
        HelpUseCase helpUseCase = new HelpService();
        if (checkListRepository.findById(CheckListId.of(CHECK_LIST_ID)).isEmpty()) {
            checkListRepository.save(new CheckList(CHECK_LIST_ID));
        }
        new TaskList(in, out, showUseCase, addProjectUseCase, addTaskUseCase, setDoneUseCase, errorUseCase, helpUseCase).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer, ShowUseCase showUseCase, AddProjectUseCase addProjectUseCase, AddTaskUseCase addTaskUseCase, SetDoneUseCase setDoneUseCase, ErrorUseCase errorUseCase, HelpUseCase helpUseCase) {
        this.in = reader;
        this.out = writer;
        this.showUseCase = showUseCase;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.setDoneUseCase = setDoneUseCase;
        this.errorUseCase = errorUseCase;
        this.helpUseCase = helpUseCase;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
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

//                new Show(checkList, out).show();
                break;
            case "add":
//                new Add(checkList, out).add(commandRest[1]);

                String[] subcommandRest = commandRest[1].split(" ", 2);
                String subcommand = subcommandRest[0];
                if (subcommand.equals("project")) {
//                    addProject(subcommandRest[1]);
                    AddProjectInput addProjectInput = new AddProjectInput();
                    addProjectInput.setCheckListId(CHECK_LIST_ID);
                    addProjectInput.setProjectName(subcommandRest[1]);
                    addProjectUseCase.execute(addProjectInput);

                } else if (subcommand.equals("task")) {
                    String[] projectTask = subcommandRest[1].split(" ", 2);

                    AddTaskInput addTaskInput = new AddTaskInput();
                    addTaskInput.setCheckListId(CHECK_LIST_ID);
                    addTaskInput.setProjectName(projectTask[0]);
                    addTaskInput.setTaskDescription(projectTask[1]);
                    addTaskUseCase.execute(addTaskInput);
//                    addTask(projectTask[0], projectTask[1]);
                }
                break;
            case "check":
//                new Check(checkList, out).setDone(commandRest[1], true);
                SetDoneInput setTrueInput = new SetDoneInput();
                setTrueInput.setCheckListId(CHECK_LIST_ID);
                setTrueInput.setTaskId(commandRest[1]);
                setTrueInput.setDone(true);
                setDoneUseCase.execute(setTrueInput);
                break;
            case "uncheck":
//                new Check(checkList, out).setDone(commandRest[1], false);

                SetDoneInput setFalseInput = new SetDoneInput();
                setFalseInput.setCheckListId(CHECK_LIST_ID);
                setFalseInput.setTaskId(commandRest[1]);
                setFalseInput.setDone(false);
                setDoneUseCase.execute(setFalseInput);
                break;
            case "help":
                new Help(out).help();
                break;
            default:
                new Error(out).error(command);
                break;
        }
    }
}
