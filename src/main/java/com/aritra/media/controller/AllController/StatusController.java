package com.aritra.media.controller.AllController;

import com.aritra.media.configuration.MySessionInfo;
import com.aritra.media.domain.dto.StatusDTO;
import com.aritra.media.repository.LocationRepository;
import com.aritra.media.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aritra Paul
 * @created_on 10/3/20 at 10:22 AM
 * @project socialmedia
 */
@Controller
@RequestMapping("/status")
public class StatusController {
	@Autowired
	StatusService statusService;
	@Autowired
	LocationRepository locationRepository;
    @Autowired
    MySessionInfo session;


	@GetMapping("/all")
	public ModelAndView viewAllStatus()
	{
		ModelAndView mv = new ModelAndView("view_status");
		mv.addObject("statusList",statusService.getAllPublicStatus());
		return mv;
	}
	@GetMapping("/{statusId}")
	public ModelAndView getStatus(@PathVariable Long statusId)
	{
		ModelAndView mv = new ModelAndView("create_status");
		mv.addObject("locationList",locationRepository.findAll());
		mv.addObject("status",statusService.getStatus(statusId));
		return mv;
	}
	@GetMapping("/create")
	public ModelAndView createStatus()
	{
		ModelAndView mv = new ModelAndView("create_status");
		mv.addObject("status",new StatusDTO());
		mv.addObject("locationList",locationRepository.findAll());
		return mv;
	}
	@PostMapping()
	public ModelAndView saveStatus(StatusDTO status)
	{
        
		
		statusService.saveStatus(status);
		String redirectUrl = "/status/all";
		return new ModelAndView("redirect:" + redirectUrl);
	}
	@GetMapping("/mystatus")
	public ModelAndView viewAllMyStatus()
	{
		ModelAndView mv = new ModelAndView("my_status");
		mv.addObject("statusList",statusService.getAllStatusByUser(session.getCurrentUser().getId()));
		return mv;
	}

}
