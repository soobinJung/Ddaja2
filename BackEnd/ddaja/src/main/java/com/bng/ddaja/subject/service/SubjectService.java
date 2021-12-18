package com.bng.ddaja.subject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.common.domain.Subject;
import com.bng.ddaja.licenses.repository.LicensesRepository;
import com.bng.ddaja.subject.dto.SubjectDTO;
import com.bng.ddaja.subject.dto.SubjectSearch;
import com.bng.ddaja.subject.repository.SubjectRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService {
    
    private SubjectRepository subjectRepository;
    private LicensesRepository licensesRepository;

    public Page<SubjectDTO> getAllSubjectSearch(SubjectSearch subjectSearch){
        return subjectRepository.findAll( 
            subjectSearch.toSpecification()
            , subjectSearch.toPageable()
        ).map( vo -> new SubjectDTO(vo));
    }

    public List<SubjectDTO> getSubjectTotalCount(SubjectSearch subjectSearch){
        return subjectRepository.findAll( 
            subjectSearch.toSpecification()
        ).stream().map( vo -> new SubjectDTO(vo)).collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(long id){
        return new SubjectDTO(subjectRepository.findById(id));
    }

    public SubjectDTO saveSubject(SubjectDTO subjectDTO){
        subjectRepository.save(
            subjectDTO.toEntity(licensesRepository.findById(subjectDTO.getLID())));
        return subjectDTO;
    }

    public SubjectDTO deleteSubject(SubjectDTO subjectDTO){
        Subject subject = subjectRepository.findById(subjectDTO.getId());
        SubjectDTO dto = new SubjectDTO(subject);
        dto.setInUse(false);
        subjectRepository.save(
            dto.toEntity(subject.getLicense())
        );
        return dto;
    }
}
