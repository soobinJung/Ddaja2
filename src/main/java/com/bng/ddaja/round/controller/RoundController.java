package com.bng.ddaja.round.controller;

import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.hateoas.round.RoundHateoas;
import com.bng.ddaja.round.dto.RoundDTO;
import com.bng.ddaja.round.dto.RoundSearch;
import com.bng.ddaja.round.service.RoundService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/round")
@AllArgsConstructor
@RestController
public class RoundController {
    
    private RoundService roundService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllRoundSearch( RoundSearch roundSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                roundService.getAllRoundSearch(roundSearch)
                , RoundHateoas.values()
            )
        );
    }

    
    @PostMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> insertRound( @Valid RoundDTO roundDTO ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                roundService.saveRound(roundDTO)
            )
        );
    }

    @PatchMapping("{id}")
    public @ResponseBody ResponseEntity<CommonResponse> updateRound( @PathVariable(name = "id", required = true) long id, @Valid RoundDTO roundDTO ){
        roundDTO.setId(id);
        return ResponseEntity.ok().body(
            new CommonResponse(
                roundService.saveRound(roundDTO)
            )
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteSubject ( @PathVariable(name = "id", required = true) long id ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                roundService.deleteRound(id)
            )
        );
    }
}