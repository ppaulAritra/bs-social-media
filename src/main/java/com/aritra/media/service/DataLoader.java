package com.aritra.media.service;

import com.aritra.media.common.util.DateTimeUtils;
import com.aritra.media.common.util.PasswordUtil;
import com.aritra.media.domain.Location;
import com.aritra.media.domain.Role;
import com.aritra.media.domain.User;
import com.aritra.media.domain.UserInfo;
import com.aritra.media.repository.LocationRepository;
import com.aritra.media.repository.RoleRepository;
import com.aritra.media.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;
    private LocationRepository locationRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository,LocationRepository locationRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load User Roles
        if (roleRepository.findById(1) == null)
            roleRepository.save(new Role(1, "ROLE_ADMIN"));
        else if (!roleRepository.findById(1).getName().equals("ROLE_ADMIN")) {
            Role oldAdminDate = roleRepository.findById(1);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findById(2) == null)
            roleRepository.save(new Role(2, "ROLE_CUSTOMER"));
        else if (!roleRepository.findById(2).getName().equals("ROLE_CUSTOMER")) {
            Role oldAdminDate = roleRepository.findById(2);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
        
        if(locationRepository.findAll().isEmpty())
        {
        	List<Location> locations= new ArrayList<>();
        	Location location1= new Location(1l,"Sylhet");
        	Location location2= new Location(1l,"Bandarban");
        	Location location3= new Location(1l,"Khulna");
        	locations.add(location1);
        	locations.add(location2);
        	locations.add(location3);
        	locationRepository.saveAll(locations);
        	
        }

        if (userRepository.findByUsername("aritra@gmail.com") == null) {
            User user = new User();
            user.setName("Aritra Paul");
            user.setCreatedOn(DateTimeUtils.dateWithTZ());
            user.setUsername("aritra@gmail.com");
            user.setPassword(PasswordUtil.encryptPassword("00000000", PasswordUtil.EncType.BCRYPT_ENCODER, null));
            user.setRole(roleRepository.findById(1));

            UserInfo userInfo = new UserInfo();
            userInfo.setAddress(" Mohammadpur, Dahak");
            userInfo.setPhone("01812020827");
            userInfo.setEmail("aritra@hotmail.com");
            userInfo.setPostCode("1207");
            userInfo.setCountry("Bangladesh");
            user.setUserInfo(userInfo);
            userRepository.save(user);
        }

    }
}