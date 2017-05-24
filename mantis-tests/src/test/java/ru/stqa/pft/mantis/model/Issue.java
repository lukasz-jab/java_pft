package ru.stqa.pft.mantis.model;

/**
 * Created by luk on 2017-05-24.
 */
public class Issue {
    private int id;
    private String summary;
    private String desciption;
    private Project project;

    public String getDesciption() {
        return desciption;
    }

    public Issue withDesciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String name) {
        this.summary = name;
        return this;
    }
}
