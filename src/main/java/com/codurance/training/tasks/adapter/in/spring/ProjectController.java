package com.codurance.training.tasks.adapter.in.spring;

import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectInput;
import com.codurance.training.tasks.usecase.port.in.addproject.AddProjectUseCase;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import javax.json.Json;
import javax.json.JsonObject;

import static com.codurance.training.tasks.io.std.CheckListApp.CHECK_LIST_ID;

@RestController
public class ProjectController {
    private final AddProjectUseCase addProjectUseCase;

    @Autowired
    public ProjectController(AddProjectUseCase addProjectUseCase) {
        this.addProjectUseCase = addProjectUseCase;
    }

    @PostMapping(path = "/checkList/{checkListId}/project", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CqrsOutput> addProject(@PathVariable String checkListId,
                                                 @RequestBody String projectInfo) {
        String name;

        JSONObject projectJSON = new JSONObject(projectInfo);
        name = projectJSON.getString("name");

        AddProjectInput addProjectInput = new AddProjectInput();
        addProjectInput.setCheckListId(checkListId);
        addProjectInput.setProjectName(name);
        var output = addProjectUseCase.execute(addProjectInput);
        if (output.getExitCode() == ExitCode.SUCCESS) {
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
