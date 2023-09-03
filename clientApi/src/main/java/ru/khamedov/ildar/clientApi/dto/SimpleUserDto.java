package ru.khamedov.ildar.clientApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Общедоступная информация о пользователе")
public class SimpleUserDto {

    private Long id;

    private String name;
}
