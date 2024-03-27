package com.codurance.training.tasks.io.spring.config;

import com.codurance.training.tasks.adapter.out.InMemoryCheckListRepositoryPeer;
import com.codurance.training.tasks.adapter.out.repository.InMemoryCheckListRepository;
import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.codurance.training.tasks.io.spring.SpringCheckListApp.CHECK_LIST_ID;


@Configuration("RepositoryInjection")
public class RepositoryInjection {

    public RepositoryInjection() {
    }

    @Bean(name = "checkListRepository")
    public CheckListRepository checkListRepository() {
        CheckListRepository repository =  new InMemoryCheckListRepository(new InMemoryCheckListRepositoryPeer());
        if (repository.findById(CheckListId.of(CHECK_LIST_ID)).isEmpty()) {
            repository.save(new CheckList(CHECK_LIST_ID));
        }
        return repository;
    }
}
