package com.codurance.training.tasks.io.spring.config;

import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;

@Configuration("UseCaseInjection")
public class UseCaseInjection {

    private final CheckListRepository repository;

    @Autowired
    public UseCaseInjection(CheckListRepository repository) {
        this.repository = repository;
    }

    @Bean
    public AddProjectUseCase addProjectUseCase() {
        return new AddProjectService(repository);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase() {
        return new AddTaskService(repository);
    }

    @Bean
    public ErrorUseCase errorUseCase() {
        return new ErrorService();
    }

    @Bean
    public HelpUseCase helpUseCase() {
        return new HelpService();
    }

    @Bean
    public SetDoneUseCase setDoneUseCase() {
        return new SetDoneService(repository);
    }

    @Bean
    public ShowUseCase showUseCase() {
        return new ShowService(repository);
    }

    @Bean
    public PrintWriter printWriter() {
        return new PrintWriter(System.out);
    }
}
