package com.codurance.training.tasks.usecase.port.in.show;

import tw.teddysoft.ezddd.core.usecase.Input;

public class ShowInput implements Input {
    private String checkListId;

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String checkListId() {
        return checkListId;
    }
}
