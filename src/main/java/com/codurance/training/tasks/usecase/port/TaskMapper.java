package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskId;

public class TaskMapper {
    public static Task toDomain(TaskDto taskDto) {
        return new Task(TaskId.of(Integer.parseInt(taskDto.getId())), taskDto.getDescription(), taskDto.isDone());
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(String.valueOf(task.getId().value()), task.getDescription(), task.isDone());
    }
}
