package com.aritra.media.service.mapping;

import com.aritra.media.common.exception.EntityNotFoundException;
import com.aritra.media.common.util.ConstantsUtil;
import com.aritra.media.configuration.MySessionInfo;
import com.aritra.media.domain.Location;
import com.aritra.media.domain.Status;
import com.aritra.media.domain.User;
import com.aritra.media.domain.dto.StatusDTO;
import com.aritra.media.repository.LocationRepository;
import com.aritra.media.repository.StatusRepository;
import com.aritra.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aritra Paul
 * @created_on 10/3/20 at 12:06 AM
 * @project socialmedia
 */
@Service
public class StatusMapper {
    UserRepository userRepository;
    LocationRepository locationRepository;
    StatusRepository statusRepository;
    @Autowired
    MySessionInfo session;

    @Autowired
    public StatusMapper(UserRepository userRepository, LocationRepository locationRepository, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.statusRepository = statusRepository;
    }
    public Status requestMapper(StatusDTO statusDTO)
    {
        Status status= new Status();
        if(statusDTO.getId()!=null)
        {
            Status entity=statusRepository.findFirstById(statusDTO.getId());
            if(entity==null)
                throw new EntityNotFoundException("no such status found");
            status.setId(entity.getId());
        }
        status.setDescription(statusDTO.getStatus());
        if(!ConstantsUtil.isValidPrivateStatus(statusDTO.getPrivacy()));
        status.setPrivacy(statusDTO.getPrivacy());
        Location location= locationRepository.findFirstById(statusDTO.getLocationId());
        if(location==null)
            throw new EntityNotFoundException("no such location found with id "+statusDTO.getLocationId());
        status.setLocation(location);
        if(statusDTO.getUserId()==null)
            statusDTO.setUserId(session.getCurrentUser().getId());
        User user= userRepository.getFirstById(statusDTO.getUserId());
        if(user==null)
            throw new EntityNotFoundException("no such user found with id "+statusDTO.getUserId());
        status.setCreatedBy(user);


        return status;
    }

    public StatusDTO responseMapper(Status status){
        StatusDTO dto = new StatusDTO();
        dto.setId(status.getId());
        dto.setStatus(status.getDescription());
        dto.setLocationId(status.getLocation().getId());
        dto.setLocation(status.getLocation().getName());
        dto.setUserId(status.getCreatedBy().getId());
        dto.setUserName(status.getCreatedBy().getName());
        dto.setPrivacy(status.getPrivacy());
        return dto;
    }

}
