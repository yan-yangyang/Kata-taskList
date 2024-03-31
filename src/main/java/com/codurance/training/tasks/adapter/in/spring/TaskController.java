package com.codurance.training.tasks.adapter.in.spring;

import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskInput;
import com.codurance.training.tasks.usecase.port.in.addtask.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneInput;
import com.codurance.training.tasks.usecase.port.in.setdone.SetDoneUseCase;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static com.codurance.training.tasks.io.std.CheckListApp.CHECK_LIST_ID;

@RestController
public class TaskController {
    private final AddTaskUseCase addTaskUseCase;
    private final SetDoneUseCase setDoneUseCase;

    @Autowired
    public TaskController(AddTaskUseCase addTaskUseCase, SetDoneUseCase setDoneUseCase) {
        this.addTaskUseCase = addTaskUseCase;
        this.setDoneUseCase = setDoneUseCase;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/checkList/{checkListId}/project/{projectName}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsOutput> addTask(@PathVariable String checkListId,
                                              @PathVariable String projectName,
                                              @RequestBody String taskInfo) {

        JSONObject taskJSON = new JSONObject(taskInfo);
        String description = taskJSON.getString("description");

        AddTaskInput addTaskInput = new AddTaskInput();
        addTaskInput.setCheckListId(checkListId);
        addTaskInput.setProjectName(projectName);
        addTaskInput.setTaskDescription(description);
        var output = addTaskUseCase.execute(addTaskInput);
        if (output.getExitCode().equals(ExitCode.FAILURE)) {
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/checkList/{checkListId}/project/{projectName}/{id}")
    public ResponseEntity<CqrsOutput> setDone(@PathVariable String checkListId,
                                              @PathVariable String id,
                                              @PathVariable String projectName,
                                              @RequestBody String taskInfo) {
        JSONObject taskJSON = new JSONObject(taskInfo);
        boolean flag = taskJSON.optBoolean("isDone");
        SetDoneInput setTrueInput = new SetDoneInput();
        setTrueInput.setCheckListId(checkListId);
        setTrueInput.setTaskId(id);
        setTrueInput.setDone(flag);
        var output = setDoneUseCase.execute(setTrueInput);
        if (output.getExitCode().equals(ExitCode.FAILURE)) {
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
