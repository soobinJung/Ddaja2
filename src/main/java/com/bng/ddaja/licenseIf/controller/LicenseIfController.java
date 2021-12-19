package com.bng.ddaja.licenseIf.controller;

import com.bng.ddaja.licenseIf.dto.LicenseIfSearch;
import com.bng.ddaja.licenseIf.service.LicenseIfService;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.hateoas.licensesIf.LicenseIfHateoas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RequestMapping("/license/if")
@AllArgsConstructor
@RestController
public class LicenseIfController {
    private LicenseIfService licenseIfService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllLicenseIfSearch( LicenseIfSearch licenseIfSearch ){
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseIfService.getAllLicenseIfSearch(licenseIfSearch)
                , LicenseIfHateoas.values()
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getLicenseIfById(@PathVariable(name = "id", required = true) long id){
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseIfService.getLicenseIfById(id)
            )
        );
    }
}
