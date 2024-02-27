package com.codurance.training.tasks.usecase.addproject;

import tw.teddysoft.ezddd.core.usecase.Input;

public class AddProjectInput implements Input {
    private String checkListId;
    private String projectName;

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
}
