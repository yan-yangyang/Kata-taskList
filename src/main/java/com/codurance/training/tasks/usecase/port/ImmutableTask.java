package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskId;

public final class ImmutableTask extends Task {
    private static final String IMMUTABLE_MESSAGE = "This task is immutable.";

    private final Task real;

    public ImmutableTask(Task real) {
        super(real.getId(), real.getDescription(), real.isDone());
        this.real = real;
    }

    public static Task create(Task real) {
        if (real instanceof ImmutableTask) {
            return real;
        }
        return new ImmutableTask(real);
    }

    @Override
    public TaskId getId() {
        return real.getId();
    }

    @Override
    public String getDescription() {
        return real.getDescription();
    }

    @Override
    public boolean isDone() {
        return real.isDone();
    }

    @Override
    public void setDone(boolean done) {
        throw new UnsupportedOperationException(IMMUTABLE_MESSAGE);
    }
}
