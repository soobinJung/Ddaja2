package com.bng.ddaja.debate.controller;

import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.debate.dto.DebateDTO;
import com.bng.ddaja.debate.dto.DebateSearch;
import com.bng.ddaja.common.hateoas.debate.DebateHateoas;
import com.bng.ddaja.debate.service.DebateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/debate")
@AllArgsConstructor
@RestController
public class DebateContoller {

    private DebateService debateService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllDebateByDebateSearch( DebateSearch debateSearch ){
        
        return ResponseEntity.ok().body(
            new CommonResponse(
                debateService.getAllDebateByDebateSearch(debateSearch)
                , DebateHateoas.values()
            )
        );
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> saveDebate( @Valid DebateDTO debateDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                debateService.saveDebate(debateDTO)
            )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> updateDebate( @PathVariable(name = "id", required = true) long id, @Valid DebateDTO debateDTO ){
        debateDTO.setId(id);
        return ResponseEntity.ok().body(
            new CommonResponse(
                debateService.saveDebate(debateDTO)
            )
        );
    }
}
