package com.codurance.training.tasks.usecase.port;

public final class TaskPo {
    private final String id;
    private final String description;
    private final boolean done;

    public TaskPo(String id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }
}
