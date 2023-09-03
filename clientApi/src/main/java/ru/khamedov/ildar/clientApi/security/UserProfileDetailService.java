package ru.khamedov.ildar.clientApi.security;

import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.khamedov.ildar.clientApi.model.UserProfile;
import ru.khamedov.ildar.clientApi.repository.UserProfileRepository;
import ru.khamedov.ildar.clientApi.util.Constant;

import java.util.ArrayList;


public class UserProfileDetailService implements UserDetailsService {
    @Resource
    private UserProfileRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        UserProfile user=userProfileRepository.findByName(username);
        authorities.add(new SimpleGrantedAuthority(Constant.AUTHORITY_ROLE));
        return new User(user.getName(), user.getPassword(), !user.isBlocked(),true, true, true, authorities);
    }
}
