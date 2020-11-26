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
import org.typhon.client.model.Customer;
import org.typhon.client.model.dto.CustomerDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public CustomerService(String baseUrl) {
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

	public Customer findById(int
	 id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customer/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		CustomerDTO customerDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<CustomerDTO>() {
				}).getBody();
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		return customer;
	}
	
	public void delete(Customer objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customer/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Customer> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customer").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<CustomerDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<CustomerDTO>>() {
				}).getBody();
		List<Customer> objList = new ArrayList<Customer>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Customer.class)));
		PagedResources<Customer> result = new PagedResources<Customer>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Customer create(Customer objToCreate) {
		CustomerDTO p = modelMapper.map(objToCreate, CustomerDTO.class);
		HttpEntity<CustomerDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customer");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CustomerDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, CustomerDTO.class);
		CustomerDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Customer.class);
		return objToCreate;
	}	
	
	public Customer update(Customer objToUpdate) {
		CustomerDTO p = modelMapper.map(objToUpdate, CustomerDTO.class);
		HttpEntity<CustomerDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/customer/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CustomerDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, CustomerDTO.class);
		CustomerDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Customer.class);
		return objToUpdate;
	}
}
