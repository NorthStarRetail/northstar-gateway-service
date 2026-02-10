package com.northstar.northstar.gateway.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayFallbackController {

    @Operation(summary = "auth service fallback", description = "Auth Service FallBack", tags = {"Auth FallBack"})
    @RequestMapping(path = "/auth-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> authServiceFallback() {
        return new ResponseEntity<>(
                "the auth service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "book service fallback", description = "Book Service FallBack", tags = {"Book FallBack"})
    @RequestMapping(path = "/book-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> bookServiceFallback() {
        return new ResponseEntity<>(
                "the book service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "store service fallback", description = "Store Service FallBack", tags = {"Store FallBack"})
    @RequestMapping(path = "/store-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> storeServiceFallback() {
        return new ResponseEntity<>(
                "the store service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "order service fallback", description = "Order Service FallBack", tags = {"Order FallBack"})
    @RequestMapping(path = "/order-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> orderServiceFallback() {
        return new ResponseEntity<>(
                "the order service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "inventory service fallback", description = "Inventory Service FallBack", tags = {"Inventory FallBack"})
    @RequestMapping(path = "/inventory-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> inventoryServiceFallback() {
        return new ResponseEntity<>(
                "the inventory service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    @Operation(summary = "notification service fallback", description = "Notification Service FallBack", tags = {"Notification FallBack"})
    @RequestMapping(path = "/notification-fallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    ResponseEntity<String> notificationServiceFallback() {
        return new ResponseEntity<>(
                "the notification service is unavailable, please try later",
                HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
    }
}
