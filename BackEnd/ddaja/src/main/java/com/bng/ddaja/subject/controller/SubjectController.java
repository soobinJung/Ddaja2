package com.bng.ddaja.subject.controller;

import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.hateoas.subject.SubjectHateoas;
import com.bng.ddaja.subject.dto.SubjectDTO;
import com.bng.ddaja.subject.dto.SubjectSearch;
import com.bng.ddaja.subject.service.SubjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/subject")
@AllArgsConstructor
@RestController
public class SubjectController {
    
    private SubjectService subjectService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllSubjectSearch( SubjectSearch subjectSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.getAllSubjectSearch(subjectSearch)
                , SubjectHateoas.values()
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getSubjectById(@PathVariable(name = "id", required = true) long id){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.getSubjectById(id)
            )
        );
    }

    @GetMapping("/total-count")
    public ResponseEntity<CommonResponse> getSubjectTotalCount( SubjectSearch subjectSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.getSubjectTotalCount(subjectSearch)
            )
        );
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> insertSubject( @Valid SubjectDTO subjectDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.saveSubject(subjectDTO)
            )
        );
    }

    @PatchMapping("{id}")
    public @ResponseBody ResponseEntity<CommonResponse> updateSubject( @PathVariable(name = "id", required = true) long id, @Valid SubjectDTO subjectDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.saveSubject(subjectDTO)
            )
        );
    }

    @DeleteMapping("")
    public ResponseEntity<CommonResponse> deleteSubject (@Valid SubjectDTO subjectDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                subjectService.deleteSubject(subjectDTO)
            )
        );
    }
}
