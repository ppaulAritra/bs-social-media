/**
 *
 */
package com.aritra.media.controller.AllController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aritra.media.domain.dto.*;
import com.aritra.media.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aritra.media.configuration.MySessionInfo;


/**
 * @author Aritra
 *
 */
@Controller
public class DashboardController {
   

    @Autowired
    UserService userService;


    @Autowired
    MySessionInfo session;

  

    private static final int BUTTONS_TO_SHOW = 9;

    @GetMapping(value = "/home")
    public ModelAndView viewDashBoard() {

        ModelAndView modelAndView = new ModelAndView("user/home");
        modelAndView.addObject("user", new UserDTO());

        return modelAndView;
    }
    @GetMapping(value={"/", "/login"})
    public ModelAndView viewLogin() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new UserDTO());

        return modelAndView;
    }
}
