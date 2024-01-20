package com.seonjuleee.msa.gatewayserver.service;

import com.seonjuleee.msa.gatewayserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    // 사용자 id와 token 값이 존재하는지 체크
    public boolean existsByAccountIdAndToken(String accountId, String token) {
        return accountRepository.existsByAccountIdAndToken(accountId, token);
    }

}
