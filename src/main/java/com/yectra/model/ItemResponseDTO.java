package com.yectra.model;

import java.util.ArrayList;
import java.util.List;

public class ItemResponseDTO   {
	
	List<Item>items = new ArrayList<>();

	public ItemResponseDTO(Item items) {
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
		}