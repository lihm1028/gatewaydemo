package com.lihm.gatewaydemo.filter;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author li.hongming
 * @date 2023/2/9
 */
@Component
public class TraceHeaderGlobalFilter implements GlobalFilter {

    private static final String TRACE_ID_HEADER = "x-traceId-header";

    @Autowired
    private Tracer tracer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = tracer.currentSpan().context().traceIdString();
        ServerHttpRequest newRequest = exchange.getRequest().mutate().header(TRACE_ID_HEADER, traceId).build();
        exchange.getResponse().beforeCommit(() ->
                Mono.fromRunnable(() -> {
                            exchange.getResponse().getHeaders().add(TRACE_ID_HEADER, traceId);
                        }
                ));
        return chain.filter(exchange.mutate().request(newRequest).build());
    }
}
