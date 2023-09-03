package ru.khamedov.ildar.clientApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Client Api",
                description = "RESTful API для получения информации о клиентах", version = "1.0.0",
                contact = @Contact(
                        name = "Khamedov Ildar",
                        email = "ildarhamedov@gmail.com"
                )
        )
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "BASIC",
        scheme = "basic")
public class OpenApiConfig {

}