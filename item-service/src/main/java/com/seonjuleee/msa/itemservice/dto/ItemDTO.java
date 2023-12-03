package com.seonjuleee.msa.itemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDTO {
    @NotBlank(message = "ID는 필수 입력 값입니다.")
    @Size(max = 10, message = "ID는 크기 10 이하까지 작성 가능합니다.")
    private String id;

    @Size(max = 20, message = "이름은 20자까지 작성 가능합니다.")
    private String name;

    @Size(max = 200, message = "설명은 최대 200자까지 작성 가능합니다.")
    private String description;

    private String itemType;

    @Positive
    private long count;

    private String regDts; // 물품의 최초등록일시
    private String updDts; // 물품의 최종수정일시
}
