package com.aritra.media.service.mapping;

import com.aritra.media.common.exception.EntityExistException;
import com.aritra.media.common.exception.EntityNotFoundException;
import com.aritra.media.common.util.PasswordUtil;

import com.aritra.media.domain.Role;
import com.aritra.media.domain.User;
import com.aritra.media.domain.UserInfo;
import com.aritra.media.domain.dto.UserDTO;

import com.aritra.media.service.RoleService;
import com.aritra.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserMapper {

    @Autowired
    private RoleService roleService;


    @Autowired
    UserService userService;

    /**
     * Maps DTO to Entity
     *
     * @param dto
     * @return entity
     */
    public User requestMapper(UserDTO dto) throws Exception {


        User user;
        if (dto.getId() != null && userService.read(dto.getId()) == null) {
            throw new EntityNotFoundException("User with this id is not found");
        } else {
            user = userService.read(dto.getId());
        }
        if(dto.getRoleId()==null)
        	dto.setRoleId(2l);
        Role role = roleService.findRoleById(dto.getRoleId());
        if (role == null) {
            throw new EntityNotFoundException("Role does not exist");
        }

        User entity = new User();
        if (dto.getId() != null)
            entity.setId(dto.getId());

        if (dto.getCreatedOn() != null)
            entity.setCreatedOn(dto.getCreatedOn());
        else
            entity.setCreatedOn(new Date());


        entity.setName(dto.getName());
        entity.setUsername(dto.getEmail());
        if (user == null) {
            entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), PasswordUtil.EncType.BCRYPT_ENCODER, null));
        } else {
            entity.setPassword(user.getPassword());
        }
        entity.setRole(role);

        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(dto.getAddress());
        userInfo.setPhone(dto.getPhone());
        if (dto.getId() == null) {
            if (userService.isEmailExist(dto.getEmail()))
                throw new EntityExistException("User with this email already exists");
            userInfo.setEmail(dto.getEmail());
        } else {
            if (!user.getUserInfo().getEmail().equals(dto.getEmail()))
                if (userService.isEmailExist(dto.getEmail()))
                    throw new EntityExistException("User with this email already exists");
            userInfo.setEmail(dto.getEmail());
        }

        userInfo.setPostCode(dto.getPostCode());
        userInfo.setCountry(dto.getCountry());

        entity.setUserInfo(userInfo);


        return entity;
    }

    public UserDTO responseMapper(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getName());
        userDTO.setCreatedOn(entity.getCreatedOn());
        userDTO.setUsername(entity.getUsername());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRoleId(entity.getRole().getId());


        userDTO.setAddress(entity.getUserInfo().getAddress());
        userDTO.setPhone(entity.getUserInfo().getPhone());
        userDTO.setEmail(entity.getUserInfo().getEmail());
        userDTO.setPostCode(entity.getUserInfo().getPostCode());
        userDTO.setCountry(entity.getUserInfo().getCountry());
        userDTO.setRoleName(entity.getRole().getName());

        return userDTO;
    }

}