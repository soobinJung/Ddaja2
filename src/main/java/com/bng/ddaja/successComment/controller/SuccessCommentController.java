package com.bng.ddaja.successComment.controller;

import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.hateoas.successComment.SuccessCommentHateoas;
import com.bng.ddaja.successComment.dto.SuccessCommentDTO;
import com.bng.ddaja.successComment.dto.SuccessCommentSearch;
import com.bng.ddaja.successComment.service.SuccessCommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RequestMapping("/success-comment")
@AllArgsConstructor
@RestController
public class SuccessCommentController {
    
    private SuccessCommentService successCommentService;
    
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllSuccessCommentBySuccessCommentSearch( SuccessCommentSearch successCommentSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                successCommentService.getAllSuccessCommentBySuccessCommentSearch(successCommentSearch)
                , SuccessCommentHateoas.values()
            )
        );
    }

    @PostMapping("")
    public  @ResponseBody ResponseEntity<CommonResponse> createSuccessComent( @Valid SuccessCommentDTO successCommentDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                successCommentService.saveSuccessComent(successCommentDTO)
            )
        );
    }

    @PatchMapping("")
    public  @ResponseBody ResponseEntity<CommonResponse> updateSuccessComent( @Valid SuccessCommentDTO successCommentDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                successCommentService.saveSuccessComent(successCommentDTO)
            )
        );
    }
} 
