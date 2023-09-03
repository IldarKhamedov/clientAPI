package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.dto.SimpleUserDto;
import ru.khamedov.ildar.clientApi.dto.UserDTO;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.UserProfile;
import ru.khamedov.ildar.clientApi.repository.ClientRepository;
import ru.khamedov.ildar.clientApi.repository.UserProfileRepository;
import ru.khamedov.ildar.clientApi.util.Constant;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    private ModelMapperService modelMapperService;

    @Resource
    private UserProfileRepository userProfileRepository;

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private ContactService contactService;

    @Resource
    private AuthService authService;

    public UserProfile createUser(UserDTO userDTO, String type) {
        Class clazz= checkClass(type);
        checkName(userDTO.getName());
        UserProfile userProfile = modelMapperService.convertToUser(userDTO,clazz);
        fillContact(userDTO,userProfile);
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    private void fillContact(UserDTO userDTO, UserProfile userProfile) {
        if (userDTO.getContactDTOList().isEmpty()) {
            return;
        }
        Set<Contact> contactSet = userDTO.getContactDTOList().stream().map(c -> contactService.createContact(c.getContactType(),c.getInformation())).collect(Collectors.toSet());
        userProfile.setContactSet(contactSet);
    }

    private void checkName(String name){
        if(userProfileRepository.checkByName(name)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,name + "-" + Constant.USER_NAME_ERROR);
        }
    }

    public Class checkClass(String type){
        checkType(type);
        return Constant.USER_MAP.get(type);
    }

    public boolean checkType(String type){
        if (!Constant.USER_MAP.containsKey(type)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,type + "-" + Constant.USER_TYPE_ERROR);
        }
        return true;
    }

    public List<SimpleUserDto> getSimpleUserDTOList(String type){
        checkType(type);
        List<? extends UserProfile> userList=clientRepository.findByNotBlocked();
        return userList.stream().map(u -> modelMapperService.convertToSimpleUserDTO(u)).collect(Collectors.toList());
    }

    public SimpleUserDto getSimpleUserDTO(String type,Long id){
        checkType(type);
        UserProfile userProfile=userProfileRepository.findById(id).get();
        return modelMapperService.convertToSimpleUserDTO(userProfile);
    }

    public boolean addContact(ContactDTO contactDTO){
        UserProfile user=authService.getUser();
        Contact contact=contactService.createContact(contactDTO.getContactType(),contactDTO.getInformation());
        user.getContactSet().add(contact);
        userProfileRepository.save(user);
        return user.getContactSet().contains(contact);
    }


}
