import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Manager;

import java.io.*;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

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
        Manager manager = new Manager(in, out);
        applicationThread = new Thread(manager);
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
        execute("add project secrets");
        execute("add task secrets \"Eat more donuts.\" 04/06/2020");
        readLines("Task created with ID 0");
        execute("add task secrets \"Destroy all humans.\" 05/06/2020");
        readLines("Task created with ID 1");

        execute("show");
        readLines(
                "secrets",
            "  [ ] id 0: Eat more donuts.",
            "  [ ] id 1: Destroy all humans."
        );

        execute("add project training");
        execute("add task training \"Four Elements of Simple Design\" 04/05/2020");
        readLines("Task created with ID 2");
        execute("add task training \"SOLID\" 04/05/2020");
        readLines("Task created with ID 3");
        execute("add task training \"Coupling and Cohesion\" 04/05/2020");
        readLines("Task created with ID 4");
        execute("add task training \"Primitive Obsession\" 04/05/2020");
        readLines("Task created with ID 5");
        execute("add task training \"Outside-In TDD\" 04/05/2020");
        readLines("Task created with ID 6");
        execute("add task training \"Interaction-Driven Design\" 04/05/2020");
        readLines("Task created with ID 7");

        execute("check 0");
        execute("check 2");
        execute("check 4");
        execute("check 5");

        execute("show");
        readLines(
                "secrets",
                "  [x] id 0: Eat more donuts.",
                "  [ ] id 1: Destroy all humans.",
                "training",
                "  [x] id 2: Four Elements of Simple Design",
                "  [ ] id 3: SOLID",
                "  [x] id 4: Coupling and Cohesion",
                "  [x] id 5: Primitive Obsession",
                "  [ ] id 6: Outside-In TDD",
                "  [ ] id 7: Interaction-Driven Design"
        );

        execute("uncheck 2");
        execute("show");
        readLines(
                "secrets",
                "  [x] id 0: Eat more donuts.",
                "  [ ] id 1: Destroy all humans.",
                "training",
                "  [ ] id 2: Four Elements of Simple Design",
                "  [ ] id 3: SOLID",
                "  [x] id 4: Coupling and Cohesion",
                "  [x] id 5: Primitive Obsession",
                "  [ ] id 6: Outside-In TDD",
                "  [ ] id 7: Interaction-Driven Design"
        );

        execute("add subtask 3 \"Single responsibility\" 04/05/2020");
        readLines("Task created with ID 8");
        execute("show");
        readLines(
                "secrets",
                "  [x] id 0: Eat more donuts.",
                "  [ ] id 1: Destroy all humans.",
                "training",
                "  [ ] id 2: Four Elements of Simple Design",
                "  [ ] id 3: SOLID",
                "    [ ] id 8: Single responsibility",
                "  [x] id 4: Coupling and Cohesion",
                "  [x] id 5: Primitive Obsession",
                "  [ ] id 6: Outside-In TDD",
                "  [ ] id 7: Interaction-Driven Design"
        );

        execute("deadline 1 04/05/2021");
        execute("show 1");
        readLines(
                "description: Destroy all humans.",
                "deadline: Tue May 04 00:00:00 MSK 2021",
                "creation date: Thu May 07 00:00:00 MSK 2020",
                "checked: false",
                "projects to which task is attached: [secrets]"
        );

        execute("view date 06/05/2020");
        readLines(
                "No tasks with such creation date"
        );

        execute("view deadline 04/05/2021");
        readLines(
                "Destroy all humans."
        );

        execute("view deadline today");
        readLines("No tasks with such deadline");

        execute("delete 1");
        execute("show");
        readLines(
                "secrets",
                "  [x] id 0: Eat more donuts.",
                "training",
                "  [ ] id 2: Four Elements of Simple Design",
                "  [ ] id 3: SOLID",
                "    [ ] id 8: Single responsibility",
                "  [x] id 4: Coupling and Cohesion",
                "  [x] id 5: Primitive Obsession",
                "  [ ] id 6: Outside-In TDD",
                "  [ ] id 7: Interaction-Driven Design"
        );

        execute("delete secrets");
        execute("show");
        readLines(
                "training",
                "  [ ] id 2: Four Elements of Simple Design",
                "  [ ] id 3: SOLID",
                "    [ ] id 8: Single responsibility",
                "  [x] id 4: Coupling and Cohesion",
                "  [x] id 5: Primitive Obsession",
                "  [ ] id 6: Outside-In TDD",
                "  [ ] id 7: Interaction-Driven Design"
        );

        execute("add project secrets");
        execute("attach secrets 2");
        execute("show 2");
        readLines(
                "description: Four Elements of Simple Design",
                "deadline: Mon May 04 00:00:00 MSK 2020",
                "creation date: Thu May 07 00:00:00 MSK 2020",
                "checked: false",
                "projects to which task is attached: [training, secrets]"
        );

        execute("detach secrets 2");
        execute("show 2");
        readLines(
                "description: Four Elements of Simple Design",
                "deadline: Mon May 04 00:00:00 MSK 2020",
                "creation date: Thu May 07 00:00:00 MSK 2020",
                "checked: false",
                "projects to which task is attached: [training]"
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
        assertEquals(String.valueOf(buffer), expectedOutput);
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
