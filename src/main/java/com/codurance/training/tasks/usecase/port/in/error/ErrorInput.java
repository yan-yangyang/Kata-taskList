package com.codurance.training.tasks.usecase.port.in.error;

import tw.teddysoft.ezddd.core.usecase.Input;

public class ErrorInput implements Input {
    private String command;

    public String command() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
