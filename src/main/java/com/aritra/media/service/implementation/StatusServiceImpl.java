package com.aritra.media.service.implementation;

import com.aritra.media.configuration.MySessionInfo;
import com.aritra.media.domain.Status;
import com.aritra.media.domain.dto.StatusDTO;
import com.aritra.media.repository.StatusRepository;
import com.aritra.media.service.StatusService;
import com.aritra.media.service.mapping.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aritra Paul
 * @created_on 10/3/20 at 12:42 AM
 * @project socialmedia
 */
@Service
public class StatusServiceImpl implements StatusService {

    StatusMapper statusMapper;
    StatusRepository statusRepository;


    @Autowired
    public StatusServiceImpl(StatusMapper statusMapper, StatusRepository statusRepository) {
        this.statusMapper = statusMapper;
        this.statusRepository = statusRepository;
    }

    @Override
    public StatusDTO saveStatus(StatusDTO dto) {
        Status entity=statusMapper.requestMapper(dto);
        return statusMapper.responseMapper(statusRepository.save(entity));
    }

    @Override
    public StatusDTO getStatus(Long id) {
        Status status= statusRepository.findFirstById(id);
        return statusMapper.responseMapper(status);
    }

    @Override
    public List<StatusDTO> getAllPublicStatus() {
        List<Status> statusList=statusRepository.findAllByPrivacyOrderByIdDesc("public");
        return statusList.stream().map(status -> statusMapper.responseMapper(status)).collect(Collectors.toList());
    }

    @Override
    public List<StatusDTO> getAllStatusByUser(Long userId) {
        List<Status> statusList=statusRepository.findAllByCreatedByIdOrderByIdDesc(userId);
        return statusList.stream().map(status -> statusMapper.responseMapper(status)).collect(Collectors.toList());
    }
}
