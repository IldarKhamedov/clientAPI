package ru.khamedov.ildar.clientApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.service.ContactService;
import ru.khamedov.ildar.clientApi.service.UserService;

@RestController
@RequestMapping("/api/authentication")
@Tag(name="Взаимодействие с личной информацией пользователя")
@SecurityRequirement(name = "BASIC")
public class ClientAuthRestController {

    @Resource
    private UserService userService;

    @Resource
    private ContactService contactService;

    @PostMapping("/add/contact")
    @Operation(summary = "Добавление контакта")
    public ResponseEntity addContact(@RequestBody ContactDTO contactDTO){
        boolean existsContact=userService.addContact(contactDTO);
        return new ResponseEntity(existsContact, HttpStatus.OK);
    }

    @GetMapping("/get/contacts")
    @Operation(summary = "Получение всех контактов")
    public ResponseEntity getContacts(){
        return new ResponseEntity(contactService.getContactList(), HttpStatus.OK);
    }

    @GetMapping("/get/contacts/{type}")
    @Operation(summary = "Получение контактов по типу")
    public ResponseEntity getContactsByType(@PathVariable @Parameter(description = "Тип контакта",required = true)String type){
        return new ResponseEntity(contactService.getContactList(type), HttpStatus.OK);
    }

}
