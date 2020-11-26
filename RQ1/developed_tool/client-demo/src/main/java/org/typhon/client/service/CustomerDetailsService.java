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
import org.typhon.client.model.CustomerDetails;
import org.typhon.client.model.dto.CustomerDetailsDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public CustomerDetailsService(String baseUrl) {
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

	public CustomerDetails findById(String
	 id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customerdetails/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		CustomerDetailsDTO customerdetailsDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<CustomerDetailsDTO>() {
				}).getBody();
		CustomerDetails customerdetails = modelMapper.map(customerdetailsDTO, CustomerDetails.class);
		return customerdetails;
	}
	
	public void delete(CustomerDetails objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customerdetails/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<CustomerDetails> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customerdetails").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<CustomerDetailsDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<CustomerDetailsDTO>>() {
				}).getBody();
		List<CustomerDetails> objList = new ArrayList<CustomerDetails>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, CustomerDetails.class)));
		PagedResources<CustomerDetails> result = new PagedResources<CustomerDetails>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public CustomerDetails create(CustomerDetails objToCreate) {
		CustomerDetailsDTO p = modelMapper.map(objToCreate, CustomerDetailsDTO.class);
		HttpEntity<CustomerDetailsDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customerdetails");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CustomerDetailsDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, CustomerDetailsDTO.class);
		CustomerDetailsDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, CustomerDetails.class);
		return objToCreate;
	}	
	
	public CustomerDetails update(CustomerDetails objToUpdate) {
		CustomerDetailsDTO p = modelMapper.map(objToUpdate, CustomerDetailsDTO.class);
		HttpEntity<CustomerDetailsDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customerdetails/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CustomerDetailsDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, CustomerDetailsDTO.class);
		CustomerDetailsDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, CustomerDetails.class);
		return objToUpdate;
	}
}
