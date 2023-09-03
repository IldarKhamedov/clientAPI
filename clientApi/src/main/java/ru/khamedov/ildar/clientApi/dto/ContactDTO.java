package ru.khamedov.ildar.clientApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.khamedov.ildar.clientApi.model.ContactType;

@Getter
@Setter
@Schema(description = "Для добавления контакта пользователя с указанием типа и информации")
public class ContactDTO {

    private ContactType contactType;

    private String information;
}
