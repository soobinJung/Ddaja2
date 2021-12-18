package com.bng.ddaja.passRate.dto;

import com.bng.ddaja.common.dto.CommonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class PassRateDTO extends CommonDTO{
    
    private String serviceKey;
    private String grdCd;
    private String baseYY;
    private String pageNo;
    private String numOfRows;

    private String grdNm;
    private String emqualDispNm;

    private int implYy;
    private int pilPassCnt;
    private int silPassCnt;
    private int implSeq;
    private int recptCnt;

}
