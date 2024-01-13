package com.seonjuleee.msa.authenticationserver.repository;

import com.seonjuleee.msa.authenticationserver.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
