package com.multiparttest.MultipartApplication.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CallbackRequest {
    String resultCode;
    String resultMsg;
    String serviceCode;
    String srtId;
    String sttResultFilePath;
}
