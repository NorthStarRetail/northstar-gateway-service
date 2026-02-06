package com.northstar.northstar.gateway.service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("gateway")
public class AppConfiguration {
    private OauthClient oauth;
    private String msg;
    private String buildVersion;
    private OpenApiServiceUrl openapiServiceUrl;

    @Data
    public static class OauthClient {
        private String clientId;
        private String clientSecret;
    }

    @Data
    public static class OpenApiServiceUrl {
        private String auth;
        private String book;
        private String store;
        private String order;
        private String inventory;
        private String notification;
    }
}
