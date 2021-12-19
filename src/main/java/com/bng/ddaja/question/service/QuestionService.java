package com.bng.ddaja.question.service;

import com.bng.ddaja.licenses.repository.LicensesRepository;
import com.bng.ddaja.question.dto.QuestionDTO;
import com.bng.ddaja.question.dto.QuestionSearch;
import com.bng.ddaja.question.repository.QuestionRepository;
import com.bng.ddaja.round.repository.RoundRepository;
import com.bng.ddaja.subject.repository.SubjectRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {
    
    private QuestionRepository questionRepository;
    private RoundRepository roundRepository;
    private LicensesRepository licensesRepository;
    private SubjectRepository subjectRepository;

    
    public Page<QuestionDTO> getAllQuestionByQuestionSearch(QuestionSearch questionSearch) {
        return questionRepository.findAll(
            questionSearch.toSpecification()
            , questionSearch.toPageable()
        ).map(vo -> new QuestionDTO(vo));
    }

    public QuestionDTO findByID (long id){
        return new QuestionDTO (questionRepository.findById(id));
    }

    public QuestionDTO saveQuestion( QuestionDTO questionDTO ){
        
        long qID = questionRepository.save( questionDTO.toEntity(
            roundRepository.findById(questionDTO.getRID())
            , licensesRepository.findById(questionDTO.getLID())
            , subjectRepository.findById(questionDTO.getSID())
        )).getId();

        return new QuestionDTO(questionRepository.findById(qID));
    }
}
