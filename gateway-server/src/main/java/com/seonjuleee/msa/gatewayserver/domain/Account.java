package com.seonjuleee.msa.gatewayserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id @Column(name = "account_id", length = 30)
    private String accountId;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "token", length = 500)
    private String token;
}
