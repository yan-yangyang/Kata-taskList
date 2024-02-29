package com.codurance.training.tasks.usecase.port.in.help;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.List;

public class HelpOutPut extends CqrsOutput<HelpOutPut> {
    private List<String> response;

    public HelpOutPut setResponse(List<String> response) {
        this.response = response;
        return this;
    }

    public List<String> getResponse() {
        return response;
    }
}
