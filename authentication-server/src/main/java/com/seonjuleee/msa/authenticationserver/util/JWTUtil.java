package com.seonjuleee.msa.authenticationserver.util;

import com.seonjuleee.msa.authenticationserver.dto.AccountDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JWTUtil {
    static String secretKey = "1f75513beb97d6c948a86572e45a07ce55e240ad7d7492c49df76c5237ccf892";
    static String CLAIM_NAME = "accountId";

    public static String generate(AccountDTO accountDTO) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(accountDTO.getAccountId())
                .claim(CLAIM_NAME, accountDTO.getAccountId()) // 정보 저장
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
                .setExpiration(new Date(now.getTime() + 10)) // set Expire Time 해당 옵션 안넣으면 expire안함
                .compact();
    }
}
