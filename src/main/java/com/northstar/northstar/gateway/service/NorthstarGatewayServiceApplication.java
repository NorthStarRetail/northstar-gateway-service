package com.northstar.northstar.gateway.service;

import com.northstar.northstar.gateway.service.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;

import java.util.Base64;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@SpringBootApplication
@EnableConfigurationProperties(value = AppConfiguration.class)
public class NorthstarGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthstarGatewayServiceApplication.class, args);
    }

    private final String encodedClientCredentials;

    public NorthstarGatewayServiceApplication(AppConfiguration appConfiguration) {
        byte[] clientCredentials = String.format("%s:%s", appConfiguration.getOauth().getClientId(), appConfiguration.getOauth().getClientSecret()).getBytes();
        encodedClientCredentials = String.format("Basic %s", Base64.getEncoder().encodeToString(clientCredentials));
    }

    @Bean
    public RouteLocator appRoutes(RouteLocatorBuilder builder, AppConfiguration appConfiguration) {
        return builder.routes()
                .route("login", p -> p.order(-1)
                        .path("/api/auth/oauth2/token")
                        .filters(f -> f.stripPrefix(2)
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .addRequestHeader("Authorization", encodedClientCredentials))
                        .uri(appConfiguration.getOpenapiServiceUrl().getAuth()))

                .route("northstar-auth-service", p -> p
                        .path("/api/auth/**", "/api/auth/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver()))
                                .circuitBreaker(config -> config
                                        .setName("authCircuitBreaker")
                                        .setFallbackUri("forward:/auth-fallback"))
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false))
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getAuth()))

                .route("northstar-book-service", p -> p
                        .path("/api/book/**", "/api/book/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                )
                                .circuitBreaker(config -> config
                                        .setName("bookCircuitBreaker")
                                        .setFallbackUri("forward:/book-fallback")
                                )
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false)
                                )
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getBook()))

                .route("northstar-store-service", p -> p
                        .path("/api/store/**", "/api/store/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                )
                                .circuitBreaker(config -> config
                                        .setName("storeCircuitBreaker")
                                        .setFallbackUri("forward:/store-fallback")
                                )
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false)
                                )
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getStore()))

                .route("northstar-order-service", p -> p
                        .path("/api/order/**", "/api/order/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                )
                                .circuitBreaker(config -> config
                                        .setName("orderCircuitBreaker")
                                        .setFallbackUri("forward:/order-fallback")
                                )
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false)
                                )
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getOrder()))

                .route("northstar-inventory-service", p -> p
                        .path("/api/inventory/**", "/api/inventory/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                )
                                .circuitBreaker(config -> config
                                        .setName("inventoryCircuitBreaker")
                                        .setFallbackUri("forward:/inventory-fallback")
                                )
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false)
                                )
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getInventory()))

                .route("northstar-notification-service", p -> p
                        .path("/api/notification/**", "/api/notification/v3/api-docs")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                )
                                .circuitBreaker(config -> config
                                        .setName("notificationCircuitBreaker")
                                        .setFallbackUri("forward:/notification-fallback")
                                )
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.GET)
                                        .setStatuses(HttpStatus.REQUEST_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE)
                                        .setBackoff(Duration.ofSeconds(2), Duration.ofSeconds(10), 2, false)
                                )
                                .addRequestHeader("X-PF-Response-Time", new Date().toString())
                                .removeRequestHeader("Cookie"))
                        .uri(appConfiguration.getOpenapiServiceUrl().getNotification()))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20, 1);
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange ->
                Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }
}
