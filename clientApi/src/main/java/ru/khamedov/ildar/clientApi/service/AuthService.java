package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.khamedov.ildar.clientApi.model.UserProfile;
import ru.khamedov.ildar.clientApi.repository.UserProfileRepository;

@Service
public class AuthService {

    @Resource
    private UserProfileRepository userProfileRepository;

    public UserProfile getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userProfileRepository.findByName(name);
    }
}
