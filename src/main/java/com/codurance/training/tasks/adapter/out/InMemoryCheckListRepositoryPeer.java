package com.codurance.training.tasks.adapter.out;

import com.codurance.training.tasks.usecase.port.CheckListPo;
import com.codurance.training.tasks.usecase.port.out.CheckListRepositoryPeer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCheckListRepositoryPeer implements CheckListRepositoryPeer {
    private final Map<String, CheckListPo> store;

    public InMemoryCheckListRepositoryPeer() {
        store = new HashMap<>();
    }

    @Override
    public Optional<CheckListPo> findById(String checkListId) {
        CheckListPo checkListPo = store.getOrDefault(checkListId, null);
        if (null == checkListPo) {
            return Optional.empty();
        }
        return Optional.of(checkListPo);
    }

    @Override
    public void save(CheckListPo checkList) {
        store.put(checkList.getId(), checkList);
    }

    @Override
    public void delete(CheckListPo checkList) {
        store.remove(checkList.getId());
    }
}
