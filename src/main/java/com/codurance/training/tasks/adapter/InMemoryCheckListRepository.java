package com.codurance.training.tasks.adapter;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.CheckListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCheckListRepository implements CheckListRepository {
    private final List<CheckList> store;

    public InMemoryCheckListRepository() {
        store = new ArrayList<>();
    }

    @Override
    public Optional<CheckList> findById(CheckListId checkListId) {
        return store.stream().filter(checkList -> checkList.getId().equals(checkListId)).findFirst();
    }

    @Override
    public void save(CheckList checkList) {
        store.add(checkList);
    }

    @Override
    public void delete(CheckList checkList) {
        store.remove(checkList);
    }
}
