package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import com.codurance.training.tasks.usecase.port.CheckListDto;
import tw.teddysoft.ezddd.core.usecase.Repository;
import tw.teddysoft.ezddd.core.usecase.RepositoryPeer;

import java.util.Optional;

public interface CheckListRepositoryPeer extends RepositoryPeer<CheckListDto, String> {
}
