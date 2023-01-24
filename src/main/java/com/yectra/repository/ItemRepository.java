package com.yectra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yectra.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByItemId(Long itemId);
}
