package com.lihm.gatewaydemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@SpringBootApplication
public class GatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @RequestMapping("/circuitbreakerfallback")
    public String circuitbreakerfallback() {
        return "This is a fallback";
    }


    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }


//    @Bean
//    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
//        return http.httpBasic()
//                .and().csrf().disable()
//                .authorizeExchange()
//                .pathMatchers("/anything/**").authenticated()
//                .pathMatchers("/headers").authenticated()
//                .pathMatchers("/**")
//                .permitAll()
////                .anyExchange().permitAll()
//                .and()
//                .build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new SCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public MapReactiveUserDetailsService reactiveUserDetailsService() {
//        final UserDetails userDetails1 = User
//                .withUsername("lihm")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("123456").roles("USER").build();
//
//        final UserDetails userDetails2 = User
//                .withUsername("admin")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("123456").roles("ADMIN").build();
//
//        final MapReactiveUserDetailsService mapReactiveUserDetailsService = new MapReactiveUserDetailsService(Arrays.asList(userDetails1, userDetails2));
//        return mapReactiveUserDetailsService;
//    }

//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                /**
//                 *  curl http://localhost:8080/get
//                 */
//                .route("1", r -> r.path("/get")
//                        .uri("http://httpbin.org"))
//
//                /**
//                 *  curl -H 'Host:www.myhost.org' 'http://localhost:8080/headers'
//                 */
//                .route("host_router", r -> r.host("*.myhost.org")
//                        .uri("http://httpbin.org"))
//
//                /**
//                 * 重定向
//                 *  curl -H 'Host:www.rewrite.org' 'http://localhost:8080/foo/get' -i
//                 */
//                .route("rewrite_route", r -> r.host("*.rewrite.org")
//                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//                        .uri("http://httpbin.org"))
//
//                /**
//                 * curl -H 'Host:www.circuitbreaker.org' 'http://localhost:8080/delay/2' -i
//                 */
//                .route("circuitbreaker_route", r -> r.host("*.circuitbreaker.org")
//                        .filters(f -> f.circuitBreaker(c -> c.setName("slowcmd")))
//                        .uri("http://httpbin.org"))
//
//                /**
//                 * curl -H 'Host:www.circuitbreakerfallback.org' 'http://localhost:8080/delay/2' -i
//                 */
//                .route("circuitbreaker_fallback_route", r -> r.host("*.circuitbreakerfallback.org")
//                        .filters(f -> f.circuitBreaker(c -> c.setName("slowcmd")
//                                .setFallbackUri("forward:/circuitbreakerfallback")))
//                        .uri("http://httpbin.org"))
//
//                /**
//                 * curl -u lihm:123456 -H 'Host:www.limited.org' 'http://localhost:8080/anything/abc' -i
//                 */
//                .route("limit_route", r -> r.host("*.limited.org").and().path("/anything/**")
//                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
//                        .uri("http://httpbin.org"))
//
//                /**
//                 * websocket_route
//                 * wscat --listen 9000
//                 * wscat -c ws://localhost:8080/echo
//                 */
//                .route("websocket_route", r -> r.path("/echo")
//                        .uri("ws://localhost:9000"))
//
//
//                .build();
//    }
//

}
