package com.codurance.training.tasks.usecase.setdone;

import tw.teddysoft.ezddd.core.usecase.Input;

public class SetDoneInput implements Input {
    private String checkListId;
    private String taskId;
    private boolean done;

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String checkListId() {
        return checkListId;
    }

    public String taskId() {
        return taskId;
    }

    public boolean done() {
        return done;
    }
}
