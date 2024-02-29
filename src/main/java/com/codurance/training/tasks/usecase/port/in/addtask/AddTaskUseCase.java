package com.codurance.training.tasks.usecase.port.in.addtask;

import tw.teddysoft.ezddd.core.usecase.UseCase;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface AddTaskUseCase extends UseCase<AddTaskInput, CqrsOutput> {
}
