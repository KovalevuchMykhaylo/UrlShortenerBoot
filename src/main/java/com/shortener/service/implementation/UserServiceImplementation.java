package com.shortener.service.implementation;

import com.shortener.dto.UserRegisterDto;
import com.shortener.entity.User;
import com.shortener.entity.enums.Role;
import com.shortener.repository.UserRepository;
import com.shortener.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("userDetailsService")
public class UserServiceImplementation implements UserService, UserDetailsService {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(UserServiceImplementation.class);

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void saveNewUserAndEncodePassword(UserRegisterDto userRegisterDto) {
        slf4jLogger.info("Save new user");
        User user = new User.Builder()
                .name(userRegisterDto.getName())
                .password(encoder.encode(userRegisterDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByName(s);
    }

    @PostConstruct
    private void createUser() {
        User user = userRepository.findByName("user");
        if (user == null) {
            user = new User.Builder()
                    .name("user")
                    .password(encoder.encode("user"))
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
        }
    }
}
