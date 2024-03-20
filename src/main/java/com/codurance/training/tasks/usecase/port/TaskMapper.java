package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskId;

public class TaskMapper {
    public static Task toDomain(TaskPo taskPo) {
        return new Task(TaskId.of(Integer.parseInt(taskPo.getId())), taskPo.getDescription(), taskPo.isDone());
    }

    public static TaskPo toPo(Task task) {
        return new TaskPo(String.valueOf(task.getId().value()), task.getDescription(), task.isDone());
    }
    public static TaskDto toDto(Task task) {
        return new TaskDto(String.valueOf(task.getId().value()), task.getDescription(), task.isDone());
    }

}
