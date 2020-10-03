package com.aritra.media.service.implementation;

import com.aritra.media.common.PageAttr;
import com.aritra.media.common.exception.EntityNotFoundException;
import com.aritra.media.common.exception.UserNotFoundException;

import com.aritra.media.common.util.PasswordUtil;
import com.aritra.media.domain.User;
import com.aritra.media.domain.dto.UserDTO;
import com.aritra.media.repository.UserRepository;
import com.aritra.media.service.UserService;
import com.aritra.media.service.mapping.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User readByUsername(String username) throws UserNotFoundException {
        System.out.println("From username in impl:" + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isPasswordMatches(User user, String password) throws Exception {
        /**
         * Check if password matches with shaencoder, if matches encode it with bicrypt and save to the database.
         */
        if (PasswordUtil.encryptPassword(password).equals(user.getPassword())) {
            user.setPassword(PasswordUtil.encryptPassword(password));
            user = userRepository.save(user);
        }

        return PasswordUtil.getPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public User read(Long userId) {
        return userRepository.getFirstById(userId);
    }

    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findByUserInfoEmail(email);
        return user != null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws Exception {
        User user = userRepository.save(userMapper.requestMapper(userDTO));
        return userMapper.responseMapper(user);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.getFirstById(userId);
        if (user == null)
            throw new EntityNotFoundException("User not found with this Id");
        return userMapper.responseMapper(user);
    }

  

	@Override
	public void setUserDeletedProperty(Long userId,Boolean delete) {
		userRepository.deactivateUser(userId, delete);
	}

    @Override
    public String updatePassword(String email, String newPassword) throws Exception {
        User user = userRepository.findByUserInfoEmail(email);
        if(user == null)
            throw new EntityNotFoundException("User not found with this mail");
        user.setPassword(PasswordUtil.encryptPassword(newPassword, PasswordUtil.EncType.BCRYPT_ENCODER, null));
        userRepository.save(user);
        
        return "Successfully updated";
    }

	@Override
	public List<UserDTO> getAllUser() {
		List<User> userList=userRepository.findAllByIsDeletedFalse();
		return userList.stream().map(user->userMapper.responseMapper(user)).collect(Collectors.toList());
	}
}