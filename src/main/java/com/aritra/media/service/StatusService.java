package com.aritra.media.service;

import com.aritra.media.domain.dto.StatusDTO;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 10/3/20 at 12:40 AM
 * @project socialmedia
 */

public interface StatusService {
    StatusDTO saveStatus(StatusDTO dto);
    StatusDTO getStatus(Long id);
    List<StatusDTO> getAllPublicStatus();
    List<StatusDTO> getAllStatusByUser(Long userId);
}
