package ru.khamedov.ildar.clientApi.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Описание ошибки для response.
 */
public class JsonError {

    private int code;

    private String message;
}
