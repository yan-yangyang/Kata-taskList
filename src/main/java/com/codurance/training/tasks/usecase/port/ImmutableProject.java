package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.util.List;

public class ImmutableProject extends Project {

    private static final String IMMUTABLE_MESSAGE = "This project is immutable.";

    private final Project real;

    public ImmutableProject(Project real) {
        super(real.getName());
        this.real = real;
    }

    public static Project create(Project real) {
        if (real instanceof ImmutableProject) {
            return real;
        }
        return new ImmutableProject(real);
    }

    @Override
    public ProjectName getName() {
        return real.getName();
    }

    @Override
    public List<Task> getTasks() {
        return real.getTasks().stream().map(ImmutableTask::create).toList();
    }

    @Override
    public void addTask(Task task) {
        throw new UnsupportedOperationException(IMMUTABLE_MESSAGE);
    }
}
