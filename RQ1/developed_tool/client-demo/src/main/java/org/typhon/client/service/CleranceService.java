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
import org.typhon.client.model.Clerance;
import org.typhon.client.model.dto.CleranceDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CleranceService {
	private static final Logger logger = LoggerFactory.getLogger(CleranceService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public CleranceService(String baseUrl) {
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

	public Clerance findById( id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/clerance/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		CleranceDTO cleranceDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<CleranceDTO>() {
				}).getBody();
		Clerance clerance = modelMapper.map(cleranceDTO, Clerance.class);
		return clerance;
	}
	
	public void delete(Clerance objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/clerance/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Clerance> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/clerance").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<CleranceDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<CleranceDTO>>() {
				}).getBody();
		List<Clerance> objList = new ArrayList<Clerance>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Clerance.class)));
		PagedResources<Clerance> result = new PagedResources<Clerance>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Clerance create(Clerance objToCreate) {
		CleranceDTO p = modelMapper.map(objToCreate, CleranceDTO.class);
		HttpEntity<CleranceDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/clerance");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CleranceDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, CleranceDTO.class);
		CleranceDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Clerance.class);
		return objToCreate;
	}	
	
	public Clerance update(Clerance objToUpdate) {
		CleranceDTO p = modelMapper.map(objToUpdate, CleranceDTO.class);
		HttpEntity<CleranceDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/clerance/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<CleranceDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, CleranceDTO.class);
		CleranceDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Clerance.class);
		return objToUpdate;
	}
}
