package com.yectra.service;

import java.io.ObjectStreamException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yectra.category.WallmartProductCategory;
import com.yectra.generatedsignature.SignatureGenerator;
import com.yectra.model.ImageEntities;
import com.yectra.model.Item;
import com.yectra.model.ProductResponseDTO;
import com.yectra.repository.ImageEntitiesRepositoy;
import com.yectra.repository.ItemRepository;

@Service
public class ProductService {

	private static final Long Item = null;

	@Autowired
	SignatureGenerator signaturegenerator;
	@Autowired
	ItemRepository itemrep;
	@Autowired
	ImageEntitiesRepositoy imagerep;

	@Value("${consumerIdd}")
	private String consumerIdd;
	@Value("${priviateKeyVersionn}")
	private String priviateKeyVersionn;
	@Value("${walmartTVSearchUrl}")
	private String walmartTVSearchUrl;
	@Value("${walmartACSearchUrl}")
	private String walmartACSearchUrl;
	@Value("${walmartWasherSearchUrl}")
	private String walmartWasherSearchUrl;
	@Value("${walmartRefrigiratorSearchUrl}")
	private String walmartRefrigiratorSearchUrl;

	@Scheduled(cron = "* * */24 * * *")
	public void getProduct() throws InvalidKeyException, NoSuchAlgorithmException, ObjectStreamException,
			UnsupportedEncodingException, SignatureException {

		System.out.println("Db value stored  time" + LocalDateTime.now());

		Long totalResult = null;
		Long numItems = null;

		Map<String, WallmartProductCategory> producturls = new HashMap<>();
		producturls.put(walmartTVSearchUrl, WallmartProductCategory.TV);
		producturls.put(walmartACSearchUrl, WallmartProductCategory.AC);
		producturls.put(walmartWasherSearchUrl, WallmartProductCategory.WashingMachine);

		for (Map.Entry<String, WallmartProductCategory> entry : producturls.entrySet()) {
			String url = entry.getKey();
			WallmartProductCategory category = entry.getValue();
			Map<String, Long> result = getProductFromApi(url, category);

			totalResult = result.get("totalResult");
			numItems = result.get("numItems");
			url = url + "&start=";

			while (numItems < totalResult) {
				System.out.println("Completed : " + numItems);
				Map<String, Long> nextresult = getProductFromApi(url + numItems.intValue(), category);
				numItems = numItems + nextresult.get("numItems");
			}
		}
	}

	private Map<String, Long> getProductFromApi(String url, WallmartProductCategory category)
			throws InvalidKeyException, NoSuchAlgorithmException, ObjectStreamException, UnsupportedEncodingException,
			SignatureException {

		long intimestamp = System.currentTimeMillis();
		String timestamp = Long.toString(intimestamp);
		String signature = signaturegenerator.generateSignature(timestamp);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("WM_CONSUMER.ID", consumerIdd);
		headers.set("WM_SEC.KEY_VERSION", priviateKeyVersionn);
		headers.set("WM_CONSUMER.INTIMESTAMP", timestamp);
		headers.set("WM_SEC.AUTH_SIGNATURE", signature);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<ProductResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, request,
				ProductResponseDTO.class);
		ProductResponseDTO products = response.getBody();
		Map<String, Long> result = new HashMap<>();
		result.put("totalResult", products.getTotalResults());
		result.put("numItems", products.getNumItems());

		System.out.println("===================================================================================");
		System.out.println(url);
		System.out.println(category);
		System.out.println(result.toString());
		System.out.println(products.toString());
		System.out.println("===================================================================================");

		List<Item> items = products.getItems();

		items.forEach(item -> {
			item.setCategory(category);
			Optional<Item> itm = itemrep.findByItemId(Item);
			if (itm.isPresent()) {
				item.setId(itm.get().getId());
			}
			itemrep.save(item);
			List<ImageEntities> itemImages = item.getImageEntities();
			itemImages.stream().forEach(image -> {
				image.setItem(item);
			});
			imagerep.saveAll(itemImages);
		});

		return result;
	}

	public Page<Item> getAllItems(int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		System.out.println(page);
		return itemrep.findAll(page);

	}

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public Page<Item> filter(String brandName, String stock, String availableOnline, String category,
			Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
		Root<Item> items = criteriaQuery.from(Item.class);

		List<Predicate> predicate = new ArrayList<Predicate>();

		if (brandName != null) {
			Predicate predicatebrandName = criteriaBuilder.equal(items.get("brandName"), brandName);
			predicate.add(predicatebrandName);
		}
		if (stock != null) {
			Predicate predicateStock = criteriaBuilder.equal(items.get("stock"), stock);
			predicate.add(predicateStock);
		}
		if (availableOnline != null) {
			Predicate predicateAvailableOnline = criteriaBuilder.equal(items.get("availableOnline"),
					"true".equals(availableOnline));
			predicate.add(predicateAvailableOnline);
		}
		if (category != null) {
			Predicate predicateAvailableOnline = criteriaBuilder.equal(items.get("category").as(String.class),
					category);
			predicate.add(predicateAvailableOnline);
		}
		
		Predicate pre = criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));
		criteriaQuery.where(pre);

		List<Item> result = entityManager.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
		System.out.println(result.size());
		int total = result.size();
		Page<Item> result1 = new PageImpl<>(result, pageable, total);
		return result1;
	}
}
