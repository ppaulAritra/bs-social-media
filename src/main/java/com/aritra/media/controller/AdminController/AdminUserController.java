/**
 *
 */
package com.aritra.media.controller.AdminController;

import java.util.List;

import com.aritra.media.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aritra.media.common.util.PagerModel;
import com.aritra.media.configuration.MySessionInfo;
import com.aritra.media.domain.Role;


import com.aritra.media.domain.dto.UserDTO;


/**
 * @author Aritra
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/view")
    public ModelAndView viewUsers() {
        ModelAndView mv = new ModelAndView("user_list");
       List<UserDTO> allUser=userService.getAllUser();
       mv.addObject("userList",allUser);
        return mv;
    }

}