package com.yectra.model;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yectra.category.WallmartProductCategory;

@Entity
@Table(name = "Item")
public class Item implements Serializable {

	private static final long serialVersionUID = 7246162780996085156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long itemId;
	private Long parentItemId;
	private String name;
	private BigDecimal msrp;
	private BigDecimal salePrice;
	private String upc;
	private String categoryPath;
	private String shortDescription;
	private String longDescription;
	private String brandName;
	private String thumbnailImage;
	private String mediumImage;
	private String largeImage;
	private String productTrackingUrl;
	private boolean ninetySevenCentShipping;
	private BigDecimal standardShipRate;
	private BigDecimal twoThreeDayShippingRate;
	private boolean marketplace;
	private boolean shipToStore;
	private boolean freeShipToStore;
	private String modelNumber;
	private String sellerInfo;
	private String customerRating;
	private long numReviews;
	private String categoryNode;
	private String rhid;
	private boolean bundle;
	private boolean clearance;
	private boolean preOrder;
	private String stock;
	private boolean freight;
	private String affiliateAddToCartUrl;
	private boolean freeShippingOver35Dollars;
	private long maxItemsInOrder;
	private String offerType;
	private boolean isTwoDayShippingEligible;
	private boolean availableOnline;
	private String offerId;
	private String size;
	private String color;
	private String gender;
	@Enumerated(value = EnumType.STRING)
	private WallmartProductCategory category ;
	@OneToMany(mappedBy = "item")
	private List<ImageEntities> imageEntities;

	public List<ImageEntities> getImageEntities() {
		return imageEntities;
	}

	public void setImageEntities(List<ImageEntities> imageEntities) {
		this.imageEntities = imageEntities;
	}

	public Item() {
		super();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public String getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(String mediumImage) {
		this.mediumImage = mediumImage;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getProductTrackingUrl() {
		return productTrackingUrl;
	}

	public void setProductTrackingUrl(String productTrackingUrl) {
		this.productTrackingUrl = productTrackingUrl;
	}

	public boolean isNinetySevenCentShipping() {
		return ninetySevenCentShipping;
	}

	public void setNinetySevenCentShipping(boolean ninetySevenCentShipping) {
		this.ninetySevenCentShipping = ninetySevenCentShipping;
	}

	public BigDecimal getStandardShipRate() {
		return standardShipRate;
	}

	public void setStandardShipRate(BigDecimal standardShipRate) {
		this.standardShipRate = standardShipRate;
	}

	public BigDecimal getTwoThreeDayShippingRate() {
		return twoThreeDayShippingRate;
	}

	public void setTwoThreeDayShippingRate(BigDecimal twoThreeDayShippingRate) {
		this.twoThreeDayShippingRate = twoThreeDayShippingRate;
	}

	public boolean isMarketplace() {
		return marketplace;
	}

	public void setMarketplace(boolean marketplace) {
		this.marketplace = marketplace;
	}

	public boolean isShipToStore() {
		return shipToStore;
	}

	public void setShipToStore(boolean shipToStore) {
		this.shipToStore = shipToStore;
	}

	public boolean isFreeShipToStore() {
		return freeShipToStore;
	}

	public void setFreeShipToStore(boolean freeShipToStore) {
		this.freeShipToStore = freeShipToStore;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public String getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(String customerRating) {
		this.customerRating = customerRating;
	}

	public long getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(long numReviews) {
		this.numReviews = numReviews;
	}

	public String getCategoryNode() {
		return categoryNode;
	}

	public void setCategoryNode(String categoryNode) {
		this.categoryNode = categoryNode;
	}

	public String getRhid() {
		return rhid;
	}

	public void setRhid(String rhid) {
		this.rhid = rhid;
	}

	public boolean isBundle() {
		return bundle;
	}

	public void setBundle(boolean bundle) {
		this.bundle = bundle;
	}

	public boolean isClearance() {
		return clearance;
	}

	public void setClearance(boolean clearance) {
		this.clearance = clearance;
	}

	public boolean isPreOrder() {
		return preOrder;
	}

	public void setPreOrder(boolean preOrder) {
		this.preOrder = preOrder;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public boolean isFreight() {
		return freight;
	}

	public void setFreight(boolean freight) {
		this.freight = freight;
	}

	public String getAffiliateAddToCartUrl() {
		return affiliateAddToCartUrl;
	}

	public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
		this.affiliateAddToCartUrl = affiliateAddToCartUrl;
	}

	public boolean isFreeShippingOver35Dollars() {
		return freeShippingOver35Dollars;
	}

	public void setFreeShippingOver35Dollars(boolean freeShippingOver35Dollars) {
		this.freeShippingOver35Dollars = freeShippingOver35Dollars;
	}

	public long getMaxItemsInOrder() {
		return maxItemsInOrder;
	}

	public void setMaxItemsInOrder(long maxItemsInOrder) {
		this.maxItemsInOrder = maxItemsInOrder;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public boolean isTwoDayShippingEligible() {
		return isTwoDayShippingEligible;
	}

	public void setTwoDayShippingEligible(boolean isTwoDayShippingEligible) {
		this.isTwoDayShippingEligible = isTwoDayShippingEligible;
	}

	public boolean isAvailableOnline() {
		return availableOnline;
	}

	public void setAvailableOnline(boolean availableOnline) {
		this.availableOnline = availableOnline;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public WallmartProductCategory getCategory() {
		return category;
	}

	public void setCategory(WallmartProductCategory category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
