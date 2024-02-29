package com.codurance.training.tasks.frame;

import com.codurance.training.tasks.adapter.in.CheckListController;
import com.codurance.training.tasks.adapter.out.repository.InMemoryCheckListRepository;
import com.codurance.training.tasks.adapter.out.InMemoryCheckListRepositoryPeer;
import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.out.CheckListRepositoryPeer;
import com.codurance.training.tasks.usecase.service.*;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

public final class CheckListApp implements Runnable {
    private static final String QUIT = "quit";
    public static final String CHECK_LIST_ID = UUID.randomUUID().toString();
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
        CheckListRepositoryPeer checkListRepositoryPeer = new InMemoryCheckListRepositoryPeer();
        CheckListRepository checkListRepository = new InMemoryCheckListRepository(checkListRepositoryPeer);
        ShowUseCase showUseCase = new ShowService(checkListRepository);
        AddProjectUseCase addProjectUseCase = new AddProjectService(checkListRepository);
        AddTaskUseCase addTaskUseCase = new AddTaskService(checkListRepository);
        SetDoneUseCase setDoneUseCase = new SetDoneService(checkListRepository);
        ErrorUseCase errorUseCase = new ErrorService();
        HelpUseCase helpUseCase = new HelpService();
        if (checkListRepository.findById(CheckListId.of(CHECK_LIST_ID)).isEmpty()) {
            checkListRepository.save(new CheckList(CHECK_LIST_ID));
        }
        new CheckListApp(in, out, showUseCase, addProjectUseCase, addTaskUseCase, setDoneUseCase, errorUseCase, helpUseCase).run();
    }

    public CheckListApp(BufferedReader reader,
                        PrintWriter writer,
                        ShowUseCase showUseCase,
                        AddProjectUseCase addProjectUseCase,
                        AddTaskUseCase addTaskUseCase,
                        SetDoneUseCase setDoneUseCase,
                        ErrorUseCase errorUseCase,
                        HelpUseCase helpUseCase) {
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
            new CheckListController(
                    out,
                    showUseCase,
                    addProjectUseCase,
                    addTaskUseCase,
                    setDoneUseCase,
                    errorUseCase,
                    helpUseCase
            ).execute(command);
        }
    }
}
