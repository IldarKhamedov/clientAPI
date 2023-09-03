package ru.khamedov.ildar.clientApi.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.service.ContactService;
import ru.khamedov.ildar.clientApi.service.UserService;

@RestController
@RequestMapping("/api/authentication")
public class ClientAuthRestController {

    @Resource
    private UserService userService;

    @Resource
    private ContactService contactService;

    @PostMapping("/add/contact")
    public ResponseEntity addContact(@RequestBody ContactDTO contactDTO){
        boolean existsContact=userService.addContact(contactDTO);
        return new ResponseEntity(existsContact, HttpStatus.OK);
    }

    @GetMapping("/get/contacts")
    public ResponseEntity getContacts(){
        return new ResponseEntity(contactService.getContactList(), HttpStatus.OK);
    }

    @GetMapping("/get/contacts/{type}")
    public ResponseEntity getContactsByType(@PathVariable(required = true)String type){
        return new ResponseEntity(contactService.getContactList(type), HttpStatus.OK);
    }

}
