package ru.khamedov.ildar.clientApi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khamedov.ildar.clientApi.dto.SimpleUserDto;
import ru.khamedov.ildar.clientApi.dto.UserDTO;
import ru.khamedov.ildar.clientApi.model.UserProfile;
import ru.khamedov.ildar.clientApi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/{type}")
@Tag(name="Общедоступное взаимодействие с пользователем")
public class ClientRestController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Добавление нового пользователя по типу")
    public ResponseEntity createUser(@PathVariable @Parameter(description = "Тип пользователя",required = true)String type,
                                     @RequestBody UserDTO userDTO){
        UserProfile userProfile=userService.createUser(userDTO,type);
        return new ResponseEntity(userProfile.getId(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Получение списка пользователей по типу")
    public ResponseEntity getUsers(@PathVariable @Parameter(description = "Тип пользователя",required = true)String type){
        List<SimpleUserDto> simpleUserDtoList=userService.getSimpleUserDTOList(type);
        return new ResponseEntity(simpleUserDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    public ResponseEntity getUser(@PathVariable @Parameter(description = "Тип пользователя",required = true)String type,
                                  @PathVariable @Parameter(description = "Id пользователя",required = true)Long id){
        SimpleUserDto simpleUserDto=userService.getSimpleUserDTO(type,id);
        return new ResponseEntity(simpleUserDto, HttpStatus.OK);
    }
}
