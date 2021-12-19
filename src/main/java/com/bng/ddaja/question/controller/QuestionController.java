package com.bng.ddaja.question.controller;

import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;

import com.bng.ddaja.question.service.QuestionService;
import com.bng.ddaja.question.dto.QuestionSearch;
import com.bng.ddaja.common.hateoas.question.QuestionHateoas;
import com.bng.ddaja.question.dto.QuestionDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/question")
@AllArgsConstructor
@RestController
public class QuestionController {
    
    private QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllQuestionByQuestionSearch( QuestionSearch questionSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                questionService.getAllQuestionByQuestionSearch(questionSearch)
                , QuestionHateoas.values()
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getQuestionById( @PathVariable(name = "id", required = true) long id ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                questionService.findByID(id)
            )
        );
    }

    @PostMapping("")
    public  @ResponseBody ResponseEntity<CommonResponse> createQuestion( @Valid QuestionDTO questionDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                questionService.saveQuestion(questionDTO)
            )
        );
    }

    @PatchMapping("")
    public  @ResponseBody ResponseEntity<CommonResponse> updateQuestion( @Valid QuestionDTO questionDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                questionService.saveQuestion(questionDTO)
            )
        );
    }
}
