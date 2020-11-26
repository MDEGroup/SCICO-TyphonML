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
import org.typhon.client.model.Track;
import org.typhon.client.model.dto.TrackDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackService {
	private static final Logger logger = LoggerFactory.getLogger(TrackService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public TrackService(String baseUrl) {
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

	public Track findById( id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/track/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		TrackDTO trackDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<TrackDTO>() {
				}).getBody();
		Track track = modelMapper.map(trackDTO, Track.class);
		return track;
	}
	
	public void delete(Track objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/track/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Track> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/track").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<TrackDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<TrackDTO>>() {
				}).getBody();
		List<Track> objList = new ArrayList<Track>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Track.class)));
		PagedResources<Track> result = new PagedResources<Track>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Track create(Track objToCreate) {
		TrackDTO p = modelMapper.map(objToCreate, TrackDTO.class);
		HttpEntity<TrackDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/track");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<TrackDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, TrackDTO.class);
		TrackDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Track.class);
		return objToCreate;
	}	
	
	public Track update(Track objToUpdate) {
		TrackDTO p = modelMapper.map(objToUpdate, TrackDTO.class);
		HttpEntity<TrackDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/track/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<TrackDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, TrackDTO.class);
		TrackDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Track.class);
		return objToUpdate;
	}
}
