package com.aritra.media.repository;

import com.aritra.media.domain.User;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User getFirstById(Long userId);

    User findByUserInfoEmail(String email);


    List<User> findAllByIsDeletedFalse();
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update users u set u.is_deleted =:delete where u.id = :userId",nativeQuery = true)
    Integer deactivateUser( @Param("userId") Long userId,@Param("delete") Boolean delete);

}