package com.bng.ddaja.passRate.controller;

import lombok.AllArgsConstructor;

import com.bng.ddaja.passRate.dto.PassRateDTO;
import com.bng.ddaja.passRate.service.PassRateService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("pass-rate")
@RestController
public class PassRateController { 
    
    private PassRateService passRateService; 

    // @GetMapping("")
    // public ResponseEntity<CommonResponse> getPassRate( PassRateDTO passRateDTO ){
    //     return ResponseEntity.ok().body(
    //         new CommonResponse(
    //             passRateService.getPassRate(passRateDTO)
    //         )
    //     );
    // }

    @GetMapping("")
    public @ResponseBody Object getPassRate( PassRateDTO passRateDTO ) throws Exception { 
        return passRateService.getPassRate(passRateDTO);
    }
}