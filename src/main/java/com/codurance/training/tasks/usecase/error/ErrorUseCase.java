package com.codurance.training.tasks.usecase.error;

import tw.teddysoft.ezddd.core.usecase.UseCase;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface ErrorUseCase extends UseCase<ErrorInput, CqrsOutput> {
}
