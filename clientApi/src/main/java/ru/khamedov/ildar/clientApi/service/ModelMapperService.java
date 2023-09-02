package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khamedov.ildar.clientApi.dto.SimpleUserDto;
import ru.khamedov.ildar.clientApi.dto.UserDTO;
import ru.khamedov.ildar.clientApi.model.UserProfile;

@Service
public class ModelMapperService {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    public UserProfile convertToUser(UserDTO userDTO,Class clazz){
        addMappings(modelMapper,clazz);
        return (UserProfile) modelMapper.map(userDTO,clazz);
    }

    private void addMappings(ModelMapper modelMapper,Class<? extends UserProfile> clazz){
        modelMapper.typeMap(UserDTO.class, clazz).addMappings(mapper -> mapper.skip(UserProfile::setContactSet));
        Converter<String,String> converter= context -> passwordEncoder.encode(context.getSource());
        modelMapper.typeMap(UserDTO.class,clazz).addMappings(mapper -> mapper.using(converter).map(UserDTO::getPassword, UserProfile::setPassword));
    }

    public SimpleUserDto convertToSimpleUserDTO(UserProfile userProfile){
        return modelMapper.map(userProfile,SimpleUserDto.class);
    }
}