package com.codurance.training.tasks.entity;

public record TaskId(long value) {
    public static TaskId of(long id) {
        return new TaskId(id);
    }
}
