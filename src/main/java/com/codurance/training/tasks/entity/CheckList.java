package com.codurance.training.tasks.entity;

import tw.teddysoft.ezddd.core.entity.AggregateRoot;
import tw.teddysoft.ezddd.core.entity.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class CheckList extends AggregateRoot<CheckListId, DomainEvent> {

    private final CheckListId id;
    private final List<Project> projects;
    private static int lastId = 0;

    public CheckList(String id) {
        this.id = CheckListId.of(id);
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public Optional<Project> get(ProjectName name) {
        return projects.stream().filter(x -> x.getName().equals(name)).findAny();
    }

    @Override
    public CheckListId getId() {
        return id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public boolean contains(String projectName) {
        return projects.stream().anyMatch(project -> project.getName().value().equals(projectName));
    }

    public void addTask(ProjectName projectName, String taskDescription) {
        Optional<Project> project = get(projectName);
        if (project.isEmpty()) {
            throw new RuntimeException(format("project '%s' not found", projectName));
        }
        project.get().getTasks().add(new Task(TaskId.of(nextId()), taskDescription, false));
    }
    private long nextId() {
        return ++lastId;
    }

    public boolean containTask(String taskId) {
        for (Project project: projects) {
            for (Task task: project.getTasks()) {
                if (task.getId().value() == Integer.parseInt(taskId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setDone(String taskId, boolean done) {
        for (Project project: projects) {
            for (Task task: project.getTasks()) {
                if (task.getId().value() == Integer.parseInt(taskId)) {
                    task.setDone(done);
                }
            }
        }
    }
}
