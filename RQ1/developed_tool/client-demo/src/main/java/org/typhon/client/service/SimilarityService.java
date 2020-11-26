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
import org.typhon.client.model.Similarity;
import org.typhon.client.model.dto.SimilarityDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimilarityService {
	private static final Logger logger = LoggerFactory.getLogger(SimilarityService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public SimilarityService(String baseUrl) {
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

	public Similarity findById( id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/similarity/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		SimilarityDTO similarityDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<SimilarityDTO>() {
				}).getBody();
		Similarity similarity = modelMapper.map(similarityDTO, Similarity.class);
		return similarity;
	}
	
	public void delete(Similarity objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/similarity/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Similarity> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/similarity").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<SimilarityDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<SimilarityDTO>>() {
				}).getBody();
		List<Similarity> objList = new ArrayList<Similarity>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Similarity.class)));
		PagedResources<Similarity> result = new PagedResources<Similarity>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Similarity create(Similarity objToCreate) {
		SimilarityDTO p = modelMapper.map(objToCreate, SimilarityDTO.class);
		HttpEntity<SimilarityDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/similarity");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<SimilarityDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, SimilarityDTO.class);
		SimilarityDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Similarity.class);
		return objToCreate;
	}	
	
	public Similarity update(Similarity objToUpdate) {
		SimilarityDTO p = modelMapper.map(objToUpdate, SimilarityDTO.class);
		HttpEntity<SimilarityDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/similarity/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<SimilarityDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, SimilarityDTO.class);
		SimilarityDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Similarity.class);
		return objToUpdate;
	}
}
