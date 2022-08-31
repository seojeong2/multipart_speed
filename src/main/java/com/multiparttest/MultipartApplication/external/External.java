package com.multiparttest.MultipartApplication.external;

import com.multiparttest.MultipartApplication.dto.CallbackRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class External {

    private RestTemplate restTemplate;

    public void passCallback(MultipartFile multipartFile) throws IOException {

        String url = "http://127.0.0.1:9002/send";

        ByteArrayResource fileResource = new ByteArrayResource(multipartFile.getBytes()){
            @Override
            public String getFilename(){
                return multipartFile.getOriginalFilename();
            }
        };


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file",fileResource);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body,httpHeaders);
        restTemplate = new RestTemplate();

        try{
//            restTemplate.postForEntity(url,requestEntity, Void.class);
            ResponseEntity response = restTemplate.exchange(url,HttpMethod.POST,requestEntity,Void.class);
            MultipartFile file = (MultipartFile) response.getBody();
            log.info("api server response: " + response.getStatusCode());
            log.info("resttemplate을 통해 전달된 파일 이름: " + file.getOriginalFilename());

            //log.info("성공");

        }catch(Exception e){
            log.info("에러 : " + e.getMessage());
        }
    }

    // 헤더 설정



}
