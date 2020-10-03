package com.aritra.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aritra.media.domain.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{
	Location findFirstById(Long id);
	List<Location> findAll();

}
