package com.bng.ddaja.successComment.service;

import com.bng.ddaja.common.domain.SuccessComment;
import com.bng.ddaja.licenses.repository.LicensesRepository;
import com.bng.ddaja.successComment.dto.SuccessCommentDTO;
import com.bng.ddaja.successComment.dto.SuccessCommentSearch;
import com.bng.ddaja.successComment.repository.SuccessCommentRepository;
import com.bng.ddaja.users.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service; 

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuccessCommentService {
    
    private SuccessCommentRepository successCommentRepository;
    private LicensesRepository licensesRepository;
    private UserRepository userRepository;

    public Page<SuccessCommentDTO> getAllSuccessCommentBySuccessCommentSearch(SuccessCommentSearch successCommentSearch) {
        
        return successCommentRepository.findAll(
            successCommentSearch.toSpecification()
            , successCommentSearch.toPageable()
        ).map(vo -> new SuccessCommentDTO(vo));
    }


    public SuccessCommentDTO saveSuccessComent( SuccessCommentDTO successCommentDTO ){
        
        SuccessComment successComent = successCommentDTO.toEntity(
            licensesRepository.findById(successCommentDTO.getLID())
            , userRepository.findById(successCommentDTO.getUID())
        );
        successCommentRepository.save(successComent);

        return new SuccessCommentDTO(successComent);
    }
}
