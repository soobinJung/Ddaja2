package com.bng.ddaja.licenseIf.repository;

import com.bng.ddaja.common.domain.LicenseIf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LicenseIfRepository extends JpaRepository< LicenseIf, Long >, JpaSpecificationExecutor< LicenseIf >{
    public LicenseIf findById( long id );
}