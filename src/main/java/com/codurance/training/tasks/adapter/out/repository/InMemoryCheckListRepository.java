package com.codurance.training.tasks.adapter.out.repository;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.port.CheckListPo;
import com.codurance.training.tasks.usecase.port.CheckListMapper;
import com.codurance.training.tasks.usecase.port.out.CheckListRepository;
import com.codurance.training.tasks.usecase.port.out.CheckListRepositoryPeer;

import java.util.Optional;

public class InMemoryCheckListRepository implements CheckListRepository {
    private final CheckListRepositoryPeer peer;

    public InMemoryCheckListRepository(CheckListRepositoryPeer peer) {
        this.peer = peer;
    }

    @Override
    public Optional<CheckList> findById(CheckListId checkListId) {
        Optional<CheckListPo> checkList = peer.findById(checkListId.id());
        return checkList.map(CheckListMapper::toDomain);
    }

    @Override
    public void save(CheckList checkList) {
        peer.save(CheckListMapper.toPo(checkList));
    }

    @Override
    public void delete(CheckList checkList) {
        peer.delete(CheckListMapper.toPo(checkList));
    }
}
