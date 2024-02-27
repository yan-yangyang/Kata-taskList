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

    public String getCheckListId() {
        return checkListId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
