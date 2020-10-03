package com.aritra.media.controller.AllController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aritra.media.domain.dto.UserDTO;
import com.aritra.media.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/registration")
	@ResponseBody
	public UserDTO saveUser( UserDTO userDto) throws Exception {
		UserDTO user= userService.saveUser(userDto);
		return user;
		
	}
	

}
