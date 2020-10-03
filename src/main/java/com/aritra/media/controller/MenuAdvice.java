/**
 *
 */
package com.aritra.media.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.aritra.media.configuration.MySessionInfo;




/**
 * @author Aritra
 *
 */

@ControllerAdvice
public class MenuAdvice {

    @Autowired
    MySessionInfo session;


    @ModelAttribute
    public void addAttributes(Model model) {
    	if (session.getCurrentUser() != null) {
            model.addAttribute("roleName", session.getCurrentUser().getRole().getName());
            model.addAttribute("userId", session.getCurrentUser().getId());
            model.addAttribute("name", session.getCurrentUser().getName());

        }else {
        	model.addAttribute("name", null);
        }

    }
}

