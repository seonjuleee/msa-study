package com.seonjuleee.msa.itemservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column(name = "item_id", length = 30)
    private String id;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "description", length = 30)
    private String description;
    @Column(name = "item_type", length = 1)
    private String itemType;
    @Column(name = "cnt", length = 10)
    private long count;
    @Column(name = "reg_dts", length = 14)
    private String regDts; // 물품의 최초등록일시
    @Column(name = "upd_dts", length = 14)
    private String updDts; // 물품의 최종수정일시
}
