package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

import com.codurance.training.tasks.adapter.out.repository.InMemoryCheckListRepository;
import com.codurance.training.tasks.adapter.out.InMemoryCheckListRepositoryPeer;
import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.io.std.CheckListApp;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.out.CheckListRepositoryPeer;
import com.codurance.training.tasks.usecase.service.*;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codurance.training.tasks.io.std.CheckListApp.CHECK_LIST_ID;
import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ApplicationTest {
    public static final String PROMPT = "> ";
    private final PipedOutputStream inStream = new PipedOutputStream();
    private final PrintWriter inWriter = new PrintWriter(inStream, true);

    private final PipedInputStream outStream = new PipedInputStream();
    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
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
        CheckListApp checkListApp = new CheckListApp(in, out, showUseCase, addProjectUseCase, addTaskUseCase, setDoneUseCase, errorUseCase, helpUseCase);
        applicationThread = new Thread(checkListApp);
    }

    @Before public void
    start_the_application() {
        applicationThread.start();
    }

    @After public void
    kill_the_application() throws IOException, InterruptedException {
        if (!stillRunning()) {
            return;
        }

        Thread.sleep(1000);
        if (!stillRunning()) {
            return;
        }

        applicationThread.interrupt();
        throw new IllegalStateException("The application is still running.");
    }

    @Test(timeout = 1000) public void
    it_works() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");

        execute("show");
        readLines(
            "secrets",
            "    [ ] 1: Eat more donuts.",
            "    [ ] 2: Destroy all humans.",
            ""
        );

        execute("add project training");
        execute("add task training Four Elements of Simple Design");
        execute("add task training SOLID");
        execute("add task training Coupling and Cohesion");
        execute("add task training Primitive Obsession");
        execute("add task training Outside-In TDD");
        execute("add task training Interaction-Driven Design");

        execute("check 1");
        execute("check 3");
        execute("check 5");
        execute("check 6");

        execute("show");
        readLines(
                "secrets",
                "    [x] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                "",
                "training",
                "    [x] 3: Four Elements of Simple Design",
                "    [ ] 4: SOLID",
                "    [x] 5: Coupling and Cohesion",
                "    [x] 6: Primitive Obsession",
                "    [ ] 7: Outside-In TDD",
                "    [ ] 8: Interaction-Driven Design",
                ""
        );

        execute("quit");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }

    private boolean stillRunning() {
        return applicationThread != null && applicationThread.isAlive();
    }
}
