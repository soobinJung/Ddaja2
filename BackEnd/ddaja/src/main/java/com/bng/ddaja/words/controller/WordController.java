package com.bng.ddaja.words.controller;


import javax.validation.Valid;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.hateoas.word.WordHateoas;
import com.bng.ddaja.words.dto.WordDTO;
import com.bng.ddaja.words.dto.WordSearch;
import com.bng.ddaja.words.service.WordService;

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

@AllArgsConstructor
@RequestMapping("words")
@RestController
public class WordController {

    private WordService wordService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<CommonResponse> getAllWordByWordSearch( WordSearch wordSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.getAllWordByWordSearch(wordSearch)
                , WordHateoas.values()
            )
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CommonResponse> getWordById(@PathVariable(name = "id", required = true) long id){
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.findById(id)
            )
        );
    }

    @GetMapping("/total-count")
    @ResponseBody
    public ResponseEntity<CommonResponse> getAllWordByWordSearchTotalCount( WordSearch wordSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.getAllWordByWordSearchTotalCount(wordSearch)
            )
        );
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> saveWord ( @Valid WordDTO WordDTO ){                                           
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.saveWord(WordDTO)
            )
        );
    }
    

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> updateWord ( @PathVariable(name="id", required = true) long id, @Valid WordDTO wordDTO  ){                                           
        wordDTO.setId(id);
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.saveWord(wordDTO)
            )
        );
    }

    @DeleteMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> deleteWord ( @Valid WordDTO word ){                                           
        return ResponseEntity.ok().body(
            new CommonResponse(
                wordService.deleteWord(word)
            )
        );
    }
}