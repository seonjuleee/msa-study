package com.seonjuleee.msa.itemservice.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String id;
    private String name;
    private String description;
    private long count;
    private String regDts; // 물품의 최초등록일시
    private String updDts; // 물품의 최종수정일시
}
