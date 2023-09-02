package ru.khamedov.ildar.clientApi.dto;

import lombok.Getter;
import lombok.Setter;
import ru.khamedov.ildar.clientApi.model.ContactType;

@Getter
@Setter
public class ContactDTO {

    private ContactType contactType;

    private String information;
}
