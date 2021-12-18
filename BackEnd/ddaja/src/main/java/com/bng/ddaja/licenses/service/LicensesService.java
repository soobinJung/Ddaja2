package com.bng.ddaja.licenses.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.licenses.repository.LicensesRepository;
import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.licenses.dto.LicenseDTO;
import com.bng.ddaja.licenses.dto.LicenseSearch;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service; 

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LicensesService {
    
    private LicensesRepository licensesRepository;

    public Page<LicenseDTO> getAllLicenseByLicenseSearch(LicenseSearch licensesSearch) {
        return licensesRepository.findAll(
            licensesSearch.toSpecification()
            , licensesSearch.toPageable()
        ).map(vo -> new LicenseDTO(vo));
    }

    public List<LicenseDTO> getLicensesTotalCount(LicenseSearch licensesSearch) {
        return licensesRepository.findAll(
            licensesSearch.toSpecification()
        ).stream().map(vo -> new LicenseDTO(vo)).collect(Collectors.toList());
    }

    public LicenseDTO findById( long lid ){
        return new LicenseDTO( licensesRepository.findById( lid ));
    }

    public LicenseDTO saveLicense(LicenseDTO licenseDTO) {
        License licenseVO = licenseDTO.toEntity();
        licensesRepository.save(licenseVO);
        return new LicenseDTO(licenseVO);
    }

    public List<LicenseDTO> getAllLicenseLikeSubjectName(LicenseSearch licenseSearch) {
        return licensesRepository.findAll(
            licenseSearch.toSpecification()).stream().map(v -> new LicenseDTO(v)).collect(Collectors.toList());
    }

    public LicenseDTO deleteLicenses( LicenseDTO licenseDTO ){
        LicenseDTO dto = new LicenseDTO(licensesRepository.findById(licenseDTO.getId()));
        dto.setInUse(false);
        licensesRepository.save(dto.toEntity());
        return new LicenseDTO (licensesRepository.findById(licenseDTO.getId()));
    }
}