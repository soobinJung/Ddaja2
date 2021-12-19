package com.bng.ddaja.round.service;

import com.bng.ddaja.licenses.repository.LicensesRepository;
import com.bng.ddaja.round.dto.RoundDTO;
import com.bng.ddaja.round.repository.RoundRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.bng.ddaja.round.dto.RoundSearch;

@Service
@AllArgsConstructor
public class RoundService {
    
    private RoundRepository roundRepository;
    private LicensesRepository licensesRepository;


    public Page<RoundDTO> getAllRoundSearch(RoundSearch roundSearch){
        
        return roundRepository.findAll(
            roundSearch.toSpecification()
            , roundSearch.toPageable()
        ).map(vo -> new RoundDTO(vo));
    }


    public RoundDTO saveRound(RoundDTO roundDTO){
        
        long rID = roundRepository.save(
            roundDTO.toEntity(licensesRepository.findById(roundDTO.getLID()))
        ).getId();
        return new RoundDTO(roundRepository.findById(rID));
    }


    public RoundDTO deleteRound(long roundID){
    
        RoundDTO dto = new RoundDTO(roundRepository.findById(roundID));
        dto.setInUse(false);
        roundRepository.save(
            dto.toEntity(licensesRepository.findById(dto.getLID()))
        );
        
        return dto;
    }
}
