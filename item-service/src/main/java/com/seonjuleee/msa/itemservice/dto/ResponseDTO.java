package com.seonjuleee.msa.itemservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDTO {
    private String code;
    private String message;
}
