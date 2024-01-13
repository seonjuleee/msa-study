package com.seonjuleee.msa.authenticationserver.service;

import com.seonjuleee.msa.authenticationserver.domain.Account;
import com.seonjuleee.msa.authenticationserver.dto.AccountDTO;
import com.seonjuleee.msa.authenticationserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    // 계정 조회
    public Account selectAccount(AccountDTO accountDTO) {
        Optional<Account> optional = accountRepository.findById(accountDTO.getAccountId());
        if (optional.isPresent()) {
            Account account = optional.get();
            if (account.getPassword().equals(accountDTO.getPassword())) {
                return account;
            }
        }
        return null;
    }

    // 계정 정보 저장
    public void saveAccount(AccountDTO accountDTO, String token) {
        accountRepository.save(Account.builder()
                        .accountId(accountDTO.getAccountId())
                        .password(accountDTO.getPassword())
                        .token(token)
                        .build());
    }
}
