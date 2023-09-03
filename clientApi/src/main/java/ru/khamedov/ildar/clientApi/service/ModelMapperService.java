package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.dto.SimpleUserDto;
import ru.khamedov.ildar.clientApi.dto.UserDTO;
import ru.khamedov.ildar.clientApi.model.*;

@Service
/**
 * Сервис для сопоставления объектов.
 */
public class ModelMapperService {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ContactTypeService contactTypeService;

    /**
     * Из пользователя в request в пользователя в базу по типу.
     * @param userDTO
     * @param clazz
     */
    public UserProfile convertToUser(UserDTO userDTO,Class clazz){
        addMappings(modelMapper,clazz);
        return (UserProfile) modelMapper.map(userDTO,clazz);
    }

    /**
     * Учитываем тип пользователя.
     * @param modelMapper
     * @param clazz
     */
    private void addMappings(ModelMapper modelMapper,Class<? extends UserProfile> clazz){
        modelMapper.typeMap(UserDTO.class, clazz).addMappings(mapper -> mapper.skip(UserProfile::setContactSet));
        Converter<String,String> converter= context -> passwordEncoder.encode(context.getSource());
        modelMapper.typeMap(UserDTO.class,clazz).addMappings(mapper -> mapper.using(converter).map(UserDTO::getPassword, UserProfile::setPassword));
    }

    /**
     * Из пользователя с базы в пользователя для response.
     * @param userProfile
     */
    public SimpleUserDto convertToSimpleUserDTO(UserProfile userProfile){
        return modelMapper.map(userProfile,SimpleUserDto.class);
    }

    /**
     * Из контакта с базы в контакт для response.
     * @param contact
     */
    public ContactDTO convertToContactDTO(Contact contact){
        ContactDTO contactDTO= modelMapper.map(contact,ContactDTO.class);
        updateContactType(contact,contactDTO);
        return contactDTO;
    }

    /**
     * Добавляем тип контакта для контакта для response.
     * @param contact
     * @param contactDTO
     */
    private void updateContactType(Contact contact,ContactDTO contactDTO){
        contactDTO.setContactType(contactTypeService.getByContact(contact));
    }
}
