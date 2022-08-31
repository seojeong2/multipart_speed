package com.multiparttest.MultipartApplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
public class TestRequest {

    @Nullable
    private Integer wavType;

    @Nullable
    private String wavFilePath;

    @NotBlank(message = "serviceCode 누락")
    private String serviceCode;

    @Length(max=20, message = "callkey 길이 초과")
    @NotBlank(message = "callkey 누락")
    private String callkey; // 최대 20글자

    @NotNull(message = "txRxType 누락")
    @Min(value = 0, message = "0 보다 작은 값 입력")
    @Max(value = 2, message = "2 보다 큰 값 입력")
    private Integer txRxType; // 0 : Tx(상담사), 1: Rx(고객), 2 : Mono(챗봇, 콜봇)

    @NotBlank(message = "modelAuthKey 누락")
    private String modelAuthKey;

    @NotBlank(message = "callbackUrl 누락")
    private String callbackUrl;
}
