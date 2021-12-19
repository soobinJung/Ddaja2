package com.bng.ddaja.wordQuestions.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.common.domain.WordQuestion;
import com.bng.ddaja.wordQuestions.dto.WordQuestionDTO;
import com.bng.ddaja.wordQuestions.dto.WordQuestionSearch;
import com.bng.ddaja.wordQuestions.repository.WordQuestionRepository;
import com.bng.ddaja.words.repository.WordRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WordQuestionService {

    private WordQuestionRepository wordQuestionRepository;
    private WordRepository wordRepository;

    public Page<WordQuestionDTO> getAllWordQuestionByWordQuestionSearch(WordQuestionSearch wordQuestionSearch){
        return wordQuestionRepository.findAll(
            wordQuestionSearch.toSpecification()
            , wordQuestionSearch.toPageable()
        ).map( vo -> new WordQuestionDTO(vo));
    }

    public List<WordQuestionDTO> getAllWordQuestionByWordQuestionSearchTotalCount(WordQuestionSearch wordQuestionSearch) {
        return wordQuestionRepository.findAll(
            wordQuestionSearch.toSpecification()
        ).stream().map(vo -> new WordQuestionDTO(vo)).collect(Collectors.toList());
    } 

    public WordQuestionDTO getWordQuestionById(long id) {
        return new WordQuestionDTO(wordQuestionRepository.findById(id));
    } 

    public WordQuestionDTO saveWordQuestion( WordQuestionDTO wordQuestionDTO){
        wordQuestionDTO.checkoutValue();
        WordQuestion wordQuestion = wordQuestionDTO.toEntity(wordRepository.findById(wordQuestionDTO.getWID()));
        wordQuestionRepository.save(wordQuestion);
        return new WordQuestionDTO(wordQuestion);
    }

    public WordQuestionDTO deleteWordQuestion( WordQuestionDTO wordQuestionDTO ){
        WordQuestion wordQuestion = wordQuestionRepository.findById(wordQuestionDTO.getId());
        wordQuestionDTO = new WordQuestionDTO(wordQuestion);
        wordQuestionDTO.setInUse(false);
        wordQuestionRepository.save(wordQuestionDTO.toEntity(wordRepository.findById(wordQuestion.getWord().getId())));
        return new WordQuestionDTO(wordQuestionRepository.findById(wordQuestionDTO.getId()));
    }
}
