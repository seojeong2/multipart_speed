package com.multiparttest.MultipartApplication.controller;

import com.multiparttest.MultipartApplication.dto.TestRequest;
import com.multiparttest.MultipartApplication.external.External;
import com.multiparttest.MultipartApplication.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MultipartController {


    private final External external;
    @Autowired
    private final CommonService commonService;

    @PostMapping("/test")
    public String doTest(@RequestPart(value = "testRequest") TestRequest _testRequest, @RequestPart(required = false, value = "file") MultipartFile _multipartFile) throws IOException {
        external.passCallback(_multipartFile);
        return "멀티파일 send로 전달";
    }

    @PostMapping("/send")
    public String doSend(@RequestParam(value = "file") MultipartFile _multipartFile, @RequestParam("md5") String _md5){
        log.info("측정 완료!");
        String wavFilePath = commonService.saveMultipartFile(_multipartFile);

        String md5Hash = commonService.getModelMd5(wavFilePath);
        log.info("md5Hash : " + md5Hash);
        // md5가 다르면 저장한 파일 삭제후 에러 던짐
        if(!md5Hash.equals(_md5)) {
            commonService.removeFile(wavFilePath);
            log.info("md5 값 다름");
        }else{
            log.info("md5값 일치");
        }

        log.info("파일 md5값 비교후 측정 시간");

        if(_multipartFile.isEmpty()) return "test failed";
        return "test success";
    }

}
