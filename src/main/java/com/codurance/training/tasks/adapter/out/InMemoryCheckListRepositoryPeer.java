package com.codurance.training.tasks.adapter.out;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.port.CheckListDto;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.port.out.CheckListRepositoryPeer;

import java.util.*;

public class InMemoryCheckListRepositoryPeer implements CheckListRepositoryPeer {
    private final Map<String, CheckListDto> store;

    public InMemoryCheckListRepositoryPeer() {
        store = new HashMap<>();
    }

    @Override
    public Optional<CheckListDto> findById(String checkListId) {
        CheckListDto checkListDto = store.getOrDefault(checkListId, null);
        if (null == checkListDto) {
            return Optional.empty();
        }
        return Optional.of(checkListDto);
    }

    @Override
    public void save(CheckListDto checkList) {
        store.put(checkList.getId(), checkList);
    }

    @Override
    public void delete(CheckListDto checkList) {
        store.remove(checkList.getId());
    }
}