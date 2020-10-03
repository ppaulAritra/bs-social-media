package com.aritra.media.service;

import com.aritra.media.common.exception.UserNotFoundException;
import com.aritra.media.domain.User;
import com.aritra.media.domain.dto.UserDTO;

import java.util.List;

import org.springframework.data.domain.Page;


public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;

    User read(Long userId);

    UserDTO saveUser(UserDTO userDTO) throws Exception;

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUser();

    boolean isEmailExist(String email);
    
    void setUserDeletedProperty(Long userId,Boolean delete);
    String updatePassword(String email,String newPassword) throws Exception;

}