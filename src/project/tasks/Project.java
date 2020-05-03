package project.tasks;

public class Project extends TaskUnit {
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}