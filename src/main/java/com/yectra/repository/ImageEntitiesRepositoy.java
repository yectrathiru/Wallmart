package com.yectra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yectra.model.ImageEntities;
@Repository
public interface ImageEntitiesRepositoy extends JpaRepository<ImageEntities, Long> {

	

}
