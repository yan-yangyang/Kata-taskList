package com.codurance.training.tasks.usecase.addtask;

import tw.teddysoft.ezddd.core.usecase.Input;

public class AddTaskInput implements Input {
    private String checkListId;
    private String projectName;
    private String taskDescription;

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String checkListId() {
        return checkListId;
    }

    public String projectName() {
        return projectName;
    }

    public String taskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
