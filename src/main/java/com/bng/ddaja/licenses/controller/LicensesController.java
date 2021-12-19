package com.bng.ddaja.licenses.controller;

import java.util.List;

import javax.validation.Valid;

import com.bng.ddaja.licenses.service.LicensesService;
import com.bng.ddaja.licenses.spec.LicenseSearchOptions;

import org.springframework.http.ResponseEntity;

import com.bng.ddaja.common.dto.CommonResource;
import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.enums.LicenseCode;
import com.bng.ddaja.common.enums.LicenseType;
import com.bng.ddaja.licenses.dto.LicenseDTO;
import com.bng.ddaja.licenses.dto.LicenseSearch;
import com.bng.ddaja.common.hateoas.licenses.LicenseHateoas;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("licenses")
@RestController
public class LicensesController {
    
    private LicensesService licenseService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllLicenseByLicenseSearch( LicenseSearch licenseSearch ) {
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseService.getAllLicenseByLicenseSearch(licenseSearch)
                , LicenseHateoas.values()
            )
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResource> getLicenseById(@PathVariable(name = "id", required = true) long id) {
        return ResponseEntity.ok().body(
            new CommonResource(
                licenseService.findById(id)
                , LicenseHateoas.values()
            )
        );
    }

    @GetMapping("/total-count")
    public ResponseEntity<CommonResponse> getLicensesTotalCount(LicenseSearch licenseSearch) {
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseService.getLicensesTotalCount(licenseSearch)
            )
        );
    }

    @GetMapping("type")
    public ResponseEntity<CommonResponse> getLicensesType() {
        return ResponseEntity.ok().body(new CommonResponse(LicenseType.values()));
    }


    @GetMapping("code")
    public ResponseEntity<CommonResponse> getLicensesCode() {
        return ResponseEntity.ok().body(new CommonResponse(LicenseCode.values())); 
    }


    @GetMapping("subjects")
    public ResponseEntity<List<LicenseDTO>> getSubjectCollections(LicenseSearch licenseSearch) {
        return ResponseEntity.ok().body(licenseService.getAllLicenseLikeSubjectName(licenseSearch));   
    }


    @GetMapping("search")
    public ResponseEntity<CommonResponse> getLicenseSearchOptions() {
        return ResponseEntity.ok().body(new CommonResponse(LicenseSearchOptions.values()));
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> createLicenses ( @Valid LicenseDTO licenseDTO ){                                           
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseService.saveLicense(licenseDTO)
            )
        );
    }

    @PatchMapping("")
    public @ResponseBody ResponseEntity<CommonResponse> updateLicenses ( @Valid LicenseDTO licenseDTO ){                                           
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseService.saveLicense(licenseDTO)
            )
        );
    }

    @DeleteMapping("")
    public ResponseEntity<CommonResponse> deleteLicenses(  @Valid LicenseDTO licenseDTO ) {
        return ResponseEntity.ok().body(
            new CommonResponse(
                licenseService.deleteLicenses(licenseDTO)
            )
        );
    }
}
