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
import org.typhon.client.model.Song;
import org.typhon.client.model.dto.SongDTO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SongService {
	private static final Logger logger = LoggerFactory.getLogger(SongService.class);
	private String baseUrl;
	ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public SongService(String baseUrl) {
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

	public Song findById(String
	 id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/song/" + id);
		String uriBuilder = builder.build().encode().toUriString();
		SongDTO songDTO = restTemplate
				.exchange(uriBuilder, HttpMethod.GET, null, new ParameterizedTypeReference<SongDTO>() {
				}).getBody();
		Song song = modelMapper.map(songDTO, Song.class);
		return song;
	}
	
	public void delete(Song objToDelete) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/song/" + objToDelete.getId());
			String uriBuilder = builder.build().encode().toUriString();
			restTemplate.delete(uriBuilder);
		}
		catch(HttpClientErrorException e) {
			logger.error(e.getMessage());
		}
	}

	public PagedResources<Song> findAll(int page, int size, String order) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/song").queryParam("page", page)
				.queryParam("size", size).queryParam("order", order);
		String uriBuilder = builder.build().encode().toUriString();
		PagedResources<SongDTO> queryResult = restTemplate.exchange(uriBuilder, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<SongDTO>>() {
				}).getBody();
		List<Song> objList = new ArrayList<Song>();
		queryResult.forEach(z -> objList.add(modelMapper.map(z, Song.class)));
		PagedResources<Song> result = new PagedResources<Song>(objList, queryResult.getMetadata(),
				new ArrayList<Link>());
		return result;
	}

	public Song create(Song objToCreate) {
		SongDTO p = modelMapper.map(objToCreate, SongDTO.class);
		HttpEntity<SongDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/song");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<SongDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.POST, request, SongDTO.class);
		SongDTO foo = response.getBody();
		objToCreate = modelMapper.map(foo, Song.class);
		return objToCreate;
	}	
	
	public Song update(Song objToUpdate) {
		SongDTO p = modelMapper.map(objToUpdate, SongDTO.class);
		HttpEntity<SongDTO> request = new HttpEntity<>(p);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/song/" + objToUpdate.getId());
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<SongDTO> response = restTemplate.exchange(uriBuilder, HttpMethod.PUT, request, SongDTO.class);
		SongDTO foo = response.getBody();
		objToUpdate = modelMapper.map(foo, Song.class);
		return objToUpdate;
	}
}
