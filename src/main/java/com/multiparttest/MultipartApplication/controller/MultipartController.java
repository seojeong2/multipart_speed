package com.multiparttest.MultipartApplication.controller;

import com.multiparttest.MultipartApplication.dto.TestRequest;
import com.multiparttest.MultipartApplication.external.External;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MultipartController {


    private final External external;

    @PostMapping("/test")
    public String doTest(@RequestPart TestRequest _testRequest, @RequestPart MultipartFile _multipartFile) throws IOException {
        external.passCallback(_multipartFile);
        return "멀티파일 send로 전달";
    }

    @PostMapping("/send")
    public String doSend(@RequestParam(value = "file") MultipartFile _multipartFile){
        if(_multipartFile.isEmpty()) return "test failed";
        return "test success";
    }


    @PostMapping("/test2")
    public String doTest2(@RequestParam(value = "file") MultipartFile _multipartFile){



        return "속도 확인 테스트 success";
    }

}
