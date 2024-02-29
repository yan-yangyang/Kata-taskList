package com.codurance.training.tasks.entity;

public record CheckListId(String id) {

    public static CheckListId of(String id) {
        return new CheckListId(id);
    }
}
