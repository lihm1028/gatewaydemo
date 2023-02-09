package com.lihm.gatewaydemo.filter;

import com.lihm.gatewaydemo.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author li.hongming
 * @date 2023/2/9
 */
@Component
@Slf4j
public class AccessLogWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        long start = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpRequest request = exchange.getRequest();
            String ip = IpUtils.getRemoteIp(request);
            String method = request.getMethodValue();
            String path = request.getURI().getPath();
            String query = request.getURI().getQuery();
            int status = exchange.getResponse().getStatusCode().value();

            long useTime = System.currentTimeMillis() - start;
            log.info("记录访问日志：{},{},{},{},{},{}ms", ip, method, status, path, query, useTime);

        }));
    }
}
