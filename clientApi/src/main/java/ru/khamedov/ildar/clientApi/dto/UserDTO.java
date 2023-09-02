package ru.khamedov.ildar.clientApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String password;

    private List<ContactDTO> contactDTOList;

}
