package com.multiparttest.MultipartApplication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class CommonService {
    public String doAppendString(String[] _strArr) {
        StringBuilder sb = new StringBuilder();
        for (String s : _strArr) {
            sb.append(s);
        }
        return sb.toString();
    }

    // md5값 체크
    public String getModelMd5(String _nasWavFilePath) {

        log.info("------------Call getModelMd5 ----------");
        log.info("nasWavFilePath : " + _nasWavFilePath);

        String s = null;
        String md5Hash = null;
        String md5HashCommand = doAppendString(new String[]{"md5sum ", _nasWavFilePath});
        log.info("getMd5HashCommand : " + md5HashCommand);

        BufferedReader stdInput = null;
        try {
            Process md5HashProcess = Runtime.getRuntime().exec(md5HashCommand);
            stdInput = new BufferedReader(new InputStreamReader(md5HashProcess.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                md5Hash = s.split(" ")[0];
            }
            stdInput.close();
        } catch (IOException e) {
//            throw new InternalServerException(SttResponse.Unknown_SERVER_ERROR, null, null);
            log.info("에러 = () " + e.getMessage());
        }

        log.info("md5Hash : " + md5Hash);
        return md5Hash;
    }

    ;

    public String saveMultipartFile(MultipartFile _multipartFile) {
        log.info("------------Call saveMultipartfile ----------");
        log.info("_multipartFile name : " + _multipartFile.getOriginalFilename());

        String fileName = _multipartFile.getOriginalFilename();
        fileName = doAppendString(new String[]{
                "test_", fileName
        });

        String filePathWithServiceCode = "/Users/seojeong/srt/svc/2/wav";

        String filePathWithServiceCodeWithFileName = doAppendString(new String[]{filePathWithServiceCode, File.separator, fileName});

        log.info("filePathWithServiceCodeWithFileName: " + filePathWithServiceCodeWithFileName);

        try {
            _multipartFile.transferTo(new File(filePathWithServiceCodeWithFileName));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return filePathWithServiceCodeWithFileName;
    }

    public void removeFile(String _wavFilePath) {
        log.info("------------Call Delete Violation File---------------");
        log.info("wavFilepath : " + _wavFilePath);

        File deleteFile = new File(_wavFilePath);
        if (deleteFile.delete()) {
            log.info("Deleted the wavFile : " + deleteFile.getName());
        } else {
            log.info("Deleted file Error !! ");
        }
    }
}
