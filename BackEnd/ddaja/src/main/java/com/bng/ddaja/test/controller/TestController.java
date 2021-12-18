package com.bng.ddaja.test.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bng.ddaja.common.config.PublicKeyConfig;
import com.bng.ddaja.common.config.exception.exception.MemberNotFoundException;
import com.bng.ddaja.common.dto.ExceptionResponse;
import com.bng.ddaja.common.dto.ExceptionDetail;
import com.bng.ddaja.common.dto.CommonJWT;
import com.bng.ddaja.common.dto.CommonResource;
import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.enums.Roles;
import com.bng.ddaja.common.hateoas.licenses.LicenseHateoas;
import com.bng.ddaja.users.dto.UserDTO;
import com.bng.ddaja.test.dto.LicenseDTO;
import com.bng.ddaja.test.dto.TestParameter;
import com.bng.ddaja.test.service.TestService;
import com.bng.ddaja.tokens.service.TokenService;
import com.bng.ddaja.test.dto.TestDTO;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("test")
@RestController
@Slf4j
public class TestController {

    private PublicKeyConfig publicKeyConfig;

    private TokenService tokensService;

    private TestService testService;

    @GetMapping("")
    public String test() {
        return "testeeee";
    }

    // @GetMapping("/one/{id}")
    // public List<Link> one(@PathVariable(name = "id") Long id) {
    //     return LicenseHateos.SELF.makeLinkList(id);
    // }

    // @GetMapping("/two/{id}")
    // public List<Link> two(@PathVariable(name = "id") Long id) {
    //     return Arrays.stream(LicenseHateos.values()).map(e -> new Link(e.name(), e.url+id.toString(), e.method)).collect(Collectors.toList());
    // }

    // @GetMapping("/three")
    // public CommonResponse<UserDTO> three(CommonParameter parameter) {
    //     return userService.findUserListByParameter(parameter);        
    // }

    // @GetMapping("/four")
    // public CommonParameter four(CommonParameter parameter) {
    //     return parameter;
    // }

    // @GetMapping("/five")
    // public ResponseEntity<CommonResponse<UserDTO>> five() {
    //     UserDTO userDTO = new UserDTO(Integer.toUnsignedLong(11), "test", "devleopeB");
    //     CommonResponse<UserDTO> response = new CommonResponse<UserDTO>(1, new CommonResource<UserDTO>(userDTO, null));
    //     return new ResponseEntity<>(response, null, HttpStatus.OK);
    // }

    // @GetMapping("/six")
    // public ResponseEntity<CommonError> six() {
    //     List<CommonErrorDetail> errorDetailList = new LinkedList<>();
    //     errorDetailList.add(new CommonErrorDetail("parameter", "pqge=1", "notCorrectParam"));
    //     CommonError error = new CommonError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errorDetailList);
    //     return new ResponseEntity<>(error, null, HttpStatus.BAD_REQUEST);
    // }

    // @GetMapping("/seven")
    // public String seven() throws MemberNotFoundException {
    //     throw new MemberNotFoundException();
    // }

    // @PostMapping("/ten")
    // public ResponseEntity<String> ten(@Valid @RequestBody TestParameter parameter ) {
    //     return ResponseEntity.ok().body("body success");
    // }

    // @GetMapping("/vue")
    // public ResponseEntity<CommonResponse<LicenseDTO>> vue() {
    //     LicenseDTO dtoOne = new LicenseDTO(11, "정보처리기사");
    //     LicenseDTO dtoTwo = new LicenseDTO(21, "정보처리산업기사");
    //     LicenseDTO dtoThr = new LicenseDTO(31, "컴퓨터활용능력 1급");
    //     LicenseDTO dtoFour = new LicenseDTO(41, "컴퓨터활용능력 2급");
    //     LicenseDTO dtoFive = new LicenseDTO(51, "빅데이터 기사");
    //     List<CommonResource<LicenseDTO>> dtoList = new LinkedList<>();
    //     dtoList.add(new CommonResource<LicenseDTO>(dtoOne, null));
    //     dtoList.add(new CommonResource<LicenseDTO>(dtoTwo, null));
    //     dtoList.add(new CommonResource<LicenseDTO>(dtoThr, null));
    //     dtoList.add(new CommonResource<LicenseDTO>(dtoFour, null));
    //     dtoList.add(new CommonResource<LicenseDTO>(dtoFive, null));
        
    //     return ResponseEntity.ok().body(new CommonResponse<>(dtoList.size(), dtoList));
    // }

    @PostMapping("/post")
    public TestDTO testPost(@RequestBody TestDTO testDTO) {
        log.info("testPOst");
        return testDTO;
    }

    @GetMapping("/publicKey")
    public String testPublicKeyProperty() {
        return publicKeyConfig.getPublicKey();
    }

    // @GetMapping("/jwt")
    // public CommonJWT testJwtCreate(UserDTO userDTO) {
    //     return tokensService.getCommonJWTByUserDTO(userDTO);
    // }

    @GetMapping("/jwt/vertify")
    public boolean testJwtVertify(String jwt) {
        // tokensService.isValidatedJWT(jwt);
        return true;
    }

    @GetMapping("/jwt/common-jwt")
    public CommonJWT testCommonJWT(String jwt) {
        // tokensService.getCommonJWT(jwt);
        return null;
    }

    @Async
    public void asyncMethod(int i) {
        try {
            Thread.sleep(500);
            log.info("[AsyncMethod]"+"-"+i);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncMethod(int i) {
        try {
            Thread.sleep(500);
            log.info("[SyncMethod]"+"-"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("async")
    public String testAsync() {
        log.info("TEST ASYNC");
        for(int i=0; i<50; i++) {
            asyncMethod(i);
        }
        return "";
    }

    @GetMapping("async/outter")
    public String testAsyncOutter() {
        log.info("TEST ASYNC");
        try {
            for(int i=0; i<50; i++) {
                testService.asyncMethod(i);
            }
        } catch (TaskRejectedException e) {
            log.error(e.getMessage());
        }
        return "";
    }

    @GetMapping("sync")
    public String testSync() {
        log.info("TEST SYNC");
        for(int i=0; i<50; i++) {
            syncMethod(i);
        }
        return "";
    }

    @GetMapping("jwt")
    public CommonJWT testJWT(CommonJWT commonJWT) {
        log.info(commonJWT.toString());
        return commonJWT;
    }
}
