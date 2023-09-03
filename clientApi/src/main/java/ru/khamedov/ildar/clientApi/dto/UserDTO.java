package ru.khamedov.ildar.clientApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Для добавления нового пользователя, указывая имя, пароль, список его контактов")
public class UserDTO {

    private String name;

    private String password;

    private List<ContactDTO> contactDTOList;

}
