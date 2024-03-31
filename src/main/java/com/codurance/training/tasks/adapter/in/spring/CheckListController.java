package com.codurance.training.tasks.adapter.in.spring;

import com.codurance.training.tasks.adapter.out.presenter.ShowPresenter;
import com.codurance.training.tasks.entity.CheckList;
import com.codurance.training.tasks.usecase.port.CheckListDto;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;

import static com.codurance.training.tasks.io.spring.SpringCheckListApp.CHECK_LIST_ID;


@RestController
@RequestMapping("/checkList")
public class CheckListController {
    private final ShowUseCase showUseCase;
    private final PrintWriter out;

    @Autowired
    public CheckListController(ShowUseCase showUseCase, PrintWriter out) {
        this.showUseCase = showUseCase;
        this.out = out;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<CheckListDto> getCheckListById(@PathVariable String id) {
        ShowInput showInput = new ShowInput();
        showInput.setCheckListId(id);
        try {
            var showOutput = showUseCase.execute(showInput);

            new ShowPresenter().present(out, showOutput.getProjects());
            return new ResponseEntity<>(showOutput.getCheckListDto(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
