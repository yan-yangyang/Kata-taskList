package com.codurance.training.tasks.adapter.out.presenter;

import java.io.PrintWriter;
import java.util.List;

public class HelpPresenter {
    public void present(PrintWriter out, List<String> helpResponse) {
        out.println(helpResponse.get(0));
        for (int i = 1 ; i < helpResponse.size() ; i++) {
            out.println("  " + helpResponse.get(i));
        }
    }
}
