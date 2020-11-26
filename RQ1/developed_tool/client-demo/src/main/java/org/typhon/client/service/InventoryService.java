package org.typhon.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.typhon.client.model.Inventory;
import org.typhon.client.model.dto.InventoryDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InventoryService {
	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public InventoryService(String baseUrl) {
		this.baseUrl = baseUrl;
		restTemplate = restTemplate();
		modelMapper = new ModelMapper();
	}

	RestTemplate restTemplate() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jackson2HalModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setObjectMapper(objectMapper);
		messageConverter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		return new RestTemplate(Arrays.asList(messageConverter));
	}

	public Inventory findById(int
	 id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/inventory/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		InventoryDTO inventoryDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<InventoryDTO>() {
				}).getBody();
		Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
		return inventory;
	}
	
	public void delete(Inventory objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/inventory/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Inventory> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/inventory").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<InventoryDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<InventoryDTO>>() {
				}).getBody();
		List<Inventory> objList = new ArrayList<Inventory>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Inventory.class)));
		PagedResources<Inventory> result = new PagedResources<Inventory>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Inventory create(Inventory objToCreate) {
		InventoryDTO p = modelMapper.map(objToCreate, InventoryDTO.class);
		HttpEntity<InventoryDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/inventory");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<InventoryDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, InventoryDTO.class);
		InventoryDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Inventory.class);
		return objToCreate;
	}	
	
	public Inventory update(Inventory objToUpdate) {
		InventoryDTO p = modelMapper.map(objToUpdate, InventoryDTO.class);
		HttpEntity<InventoryDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/inventory/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<InventoryDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, InventoryDTO.class);
		InventoryDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Inventory.class);
		return objToUpdate;
	}
}
