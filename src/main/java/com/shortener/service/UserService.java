package com.shortener.service;

import com.shortener.dto.UserRegisterDto;
import com.shortener.entity.User;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Save new user and encode password.
     *
     * @param userRegisterDto the user register dto
     */
    void saveNewUserAndEncodePassword(UserRegisterDto userRegisterDto);

    /**
     * Find by user name user.
     *
     * @param name the name
     * @return the user
     */
    User findByUserName(String name);
}
