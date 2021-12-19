package com.bng.ddaja.licenses.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.dto.CommonDTO;
import com.bng.ddaja.common.enums.LicenseCode;
import com.bng.ddaja.common.enums.LicenseType;
import com.bng.ddaja.subject.dto.SubjectDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class LicenseDTO extends CommonDTO {
    
    private long id;
    private LicenseCode code;
    private String name;
    private boolean inUse;
    private String agency;
    private LicenseType type;
    private int passScore;
    private int examFee;
    private List<SubjectDTO> subjects;

    public LicenseDTO(License vo) {
        this.id = vo.getId();
        this.code = vo.getLCode();
        this.name = vo.getName();
        this.inUse = vo.isInUse();
        this.agency = vo.getAgency();
        this.type = vo.getType();
        this.passScore = vo.getPassScore();
        this.examFee = vo.getExamFee();
        super.createdDate = vo.getCreatedDate();
        super.modifiedDate = vo.getModifiedDate();
        this.subjects = vo.getSubjects() == null ? null : vo.getSubjects().stream().map(v -> new SubjectDTO(v)).collect(Collectors.toList());
    }

    public License toEntity() {
        return License.builder()
        .id(this.id)
        .lCode(this.code)
        .name(this.name)
        .inUse(this.inUse)
        .agency(this.agency)
        .type(this.type)
        .passScore(this.passScore)
        .examFee(this.examFee)
        .build();
    }

    public void checkValue() {
        log.info("====== LicenseDTO ToString ====== ");
        log.info("ID         : " + this.id);
        log.info("CODE       : " + this.code);
        log.info("NAME       : " + this.name);
        log.info("IN USE     : " + this.inUse);
        log.info("AGENCY     : " + this.agency);
        log.info("TYPE       : " + this.type);
        log.info("PASS SCORE : " + this.passScore);
        log.info("EXAM FEE   : " + this.examFee);
        log.info("====================================");
    }
}
