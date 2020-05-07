package project.tasks;

/**
 * Represents the project which consists
 * of several tasks
 */

public class Project extends TaskUnit {
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
