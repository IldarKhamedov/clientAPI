package ru.khamedov.ildar.clientApi.controller;

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
public class ClientRestController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createPost(@PathVariable(required = true)String type, @RequestBody UserDTO userDTO){
        UserProfile userProfile=userService.createUser(userDTO,type);
        return new ResponseEntity(userProfile.getId(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getUsers(@PathVariable(required = true)String type){
        List<SimpleUserDto> simpleUserDtoList=userService.getSimpleUserDTOList(type);
        return new ResponseEntity(simpleUserDtoList, HttpStatus.OK);
    }
}
