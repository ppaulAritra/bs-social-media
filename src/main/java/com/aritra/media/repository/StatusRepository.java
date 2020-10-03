package com.aritra.media.repository;

import com.aritra.media.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 10/2/20 at 11:19 PM
 * @project socialmedia
 */

public interface StatusRepository extends JpaRepository<Status,Long> {
    List<Status> findAllByPrivacyOrderByIdDesc(String privacy);
    List<Status> findAllByCreatedByIdOrderByIdDesc(Long creatorId);
    Status findFirstById(Long id);
}
