package com.bng.ddaja.debate.service;

import com.bng.ddaja.debate.dto.DebateDTO;
import com.bng.ddaja.debate.dto.DebateSearch;
import com.bng.ddaja.debate.repository.DebateRepository;
import com.bng.ddaja.question.repository.QuestionRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DebateService {
    
    private QuestionRepository questionRepository;
    private DebateRepository debateRepository;

    public Page<DebateDTO> getAllDebateByDebateSearch(DebateSearch debateSearch) {

        return debateRepository.findAll(
            debateSearch.toSpecification()
            , debateSearch.toPageable()
        ).map(vo -> new DebateDTO(vo));
    }

    public DebateDTO findByID (long id){
        return new DebateDTO (debateRepository.findById(id));
    }

    public DebateDTO saveDebate( DebateDTO debateDTO ){

        long dID = debateRepository.save( debateDTO.toEntity(
            questionRepository.findById(debateDTO.getQID())
        )).getId();

        return new DebateDTO( debateRepository.findById(dID) );
    }
}
