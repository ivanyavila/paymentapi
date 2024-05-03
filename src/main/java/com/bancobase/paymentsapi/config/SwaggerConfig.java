package com.bancobase.paymentsapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title="Banco Base Payments API",
                version="0.1-Beta",
                description = "API REST that provides methods to Create, Read and Update Payments"
        )
)
public class SwaggerConfig {
}
