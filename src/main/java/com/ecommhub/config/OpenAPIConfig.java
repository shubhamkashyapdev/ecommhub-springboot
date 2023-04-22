package com.ecommhub.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("http://localhost:8080/api/v1")
    private String devUrl;

    @Value("https://ecommhubdev.app")
    private String prodUrl;


    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("shubhamkashyapdev@gmail.com");
        contact.setName("dev1800");
        contact.setUrl("https://shubhamkashyapdev.vercel.app");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Ecommerce API Starter")
                .version("1.0")
                .contact(contact)
                .description("This API is built on latest SpringBoot 3 Java Framework and is a great starting point to develop a modern and clean ecommerce web application.").termsOfService("https://shubhamkashyapdev.vercel.app/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
