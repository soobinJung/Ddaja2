package com.bng.ddaja.licenseIf.service;

import com.bng.ddaja.licenseIf.repository.LicenseIfRepository;

import org.springframework.data.domain.Page;

import com.bng.ddaja.licenseIf.dto.LicenseIfDTO;
import com.bng.ddaja.licenseIf.dto.LicenseIfSearch;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LicenseIfService {

    private LicenseIfRepository licenseIfRepository;

    public Page<LicenseIfDTO> getAllLicenseIfSearch(LicenseIfSearch licenseIfSearch){
        return licenseIfRepository.findAll( 
            licenseIfSearch.toSpecification()
            , licenseIfSearch.toPageable()
        ).map( vo -> new LicenseIfDTO(vo));
    }


    public LicenseIfDTO getLicenseIfById(long id){
        return new LicenseIfDTO(licenseIfRepository.findById(id));
    }
}
