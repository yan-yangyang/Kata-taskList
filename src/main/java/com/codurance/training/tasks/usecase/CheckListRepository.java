package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.entity.CheckListId;
import tw.teddysoft.ezddd.core.usecase.Repository;

public interface CheckListRepository extends Repository<CheckList, CheckListId> {
}
