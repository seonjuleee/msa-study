package com.seonjuleee.msa.gatewayserver.filter;

import com.google.gson.Gson;
import com.seonjuleee.msa.gatewayserver.service.AccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenCheckFilter extends AbstractGatewayFilterFactory<TokenCheckFilter.Config> {

    @Autowired
    AccountService accountService;

    public TokenCheckFilter() {
        super(Config.class);
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            boolean success = false;
            Object tmp = request.getHeaders().get("token");
            Object tmp2 = request.getHeaders().get("accountId");
            String token = "";
            String accountId = "";
            if (tmp != null) {
                token = tmp.toString().replace("[", "").replace("]", "");
            }
            if (tmp2 != null) {
                accountId = tmp2.toString().replace("[", "").replace("]", "");
            }
            log.info("token : {}", token);

            success = accountService.existsByAccountIdAndToken(accountId, token);

            log.info("user authentication check result : {}", success);

            if (!success) {
                return errorResponse(exchange);
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> errorResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        Gson gson = new Gson();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", "401");
        paramMap.put("message", "Unauthorized token");

        String json = gson.toJson(paramMap);

        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(buffer));
    }
}
