package com.northstar.northstar.gateway.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayFallbackController {

    @Operation(summary = "auth service fallback", description = "Auth Service FallBack", tags = {"FallBack"})
    @GetMapping("/auth-fallback")
    ResponseEntity<String> authServiceFallback() {
        return new ResponseEntity<>(
                "the auth service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "book service fallback", description = "Book Service FallBack", tags = {"FallBack"})
    @GetMapping("/book-fallback")
    ResponseEntity<String> bookServiceFallback() {
        return new ResponseEntity<>(
                "the book service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "store service fallback", description = "Store Service FallBack", tags = {"FallBack"})
    @GetMapping("/store-fallback")
    ResponseEntity<String> storeServiceFallback() {
        return new ResponseEntity<>(
                "the store service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "order service fallback", description = "Order Service FallBack", tags = {"FallBack"})
    @GetMapping("/order-fallback")
    ResponseEntity<String> orderServiceFallback() {
        return new ResponseEntity<>(
                "the order service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "inventory service fallback", description = "Inventory Service FallBack", tags = {"FallBack"})
    @GetMapping("/inventory-fallback")
    ResponseEntity<String> inventoryServiceFallback() {
        return new ResponseEntity<>(
                "the inventory service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "notification service fallback", description = "Notification Service FallBack", tags = {"FallBack"})
    @GetMapping("/notification-fallback")
    ResponseEntity<String> notificationServiceFallback() {
        return new ResponseEntity<>(
                "the notification service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }
}
