package com.seonjuleee.msa.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // token이 null인 경우에 response에 나오지 않도록 처리
public class ResponseDTO {
    private String code;
    private String message;
    private String token;
}
