package com.yectra.model;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseDTO {

	private String query;
	private String sort;
	private String responseGroup;
	private Long totalResults;
	private Long start;
	private Long numItems;
	private List<Item> items = new ArrayList<Item>();
	private List<ImageEntities> imageentities = new ArrayList<ImageEntities>();

	public List<ImageEntities> getImageentities() {
		return imageentities;
	}

	public void setImageentities(List<ImageEntities> imageentities) {
		this.imageentities = imageentities;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getResponseGroup() {
		return responseGroup;
	}

	public void setResponseGroup(String responseGroup) {
		this.responseGroup = responseGroup;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getNumItems() {
		return numItems;
	}

	public void setNumItems(Long numItems) {
		this.numItems = numItems;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
