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
import org.typhon.client.model.PurchaseDetails;
import org.typhon.client.model.dto.PurchaseDetailsDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PurchaseDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseDetailsService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public PurchaseDetailsService(String baseUrl) {
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

	public PurchaseDetails findById(int
	 id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/purchasedetails/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		PurchaseDetailsDTO purchasedetailsDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<PurchaseDetailsDTO>() {
				}).getBody();
		PurchaseDetails purchasedetails = modelMapper.map(purchasedetailsDTO, PurchaseDetails.class);
		return purchasedetails;
	}
	
	public void delete(PurchaseDetails objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/purchasedetails/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<PurchaseDetails> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/purchasedetails").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<PurchaseDetailsDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<PurchaseDetailsDTO>>() {
				}).getBody();
		List<PurchaseDetails> objList = new ArrayList<PurchaseDetails>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, PurchaseDetails.class)));
		PagedResources<PurchaseDetails> result = new PagedResources<PurchaseDetails>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public PurchaseDetails create(PurchaseDetails objToCreate) {
		PurchaseDetailsDTO p = modelMapper.map(objToCreate, PurchaseDetailsDTO.class);
		HttpEntity<PurchaseDetailsDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/purchasedetails");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<PurchaseDetailsDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, PurchaseDetailsDTO.class);
		PurchaseDetailsDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, PurchaseDetails.class);
		return objToCreate;
	}	
	
	public PurchaseDetails update(PurchaseDetails objToUpdate) {
		PurchaseDetailsDTO p = modelMapper.map(objToUpdate, PurchaseDetailsDTO.class);
		HttpEntity<PurchaseDetailsDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/purchasedetails/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<PurchaseDetailsDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, PurchaseDetailsDTO.class);
		PurchaseDetailsDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, PurchaseDetails.class);
		return objToUpdate;
	}
}
