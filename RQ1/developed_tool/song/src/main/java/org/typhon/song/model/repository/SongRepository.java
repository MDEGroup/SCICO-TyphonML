package org.typhon.song.model.repository;

import org.typhon.song.model.Song;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "song", path = "song")
public interface SongRepository extends PagingAndSortingRepository<Song, String> {
	List<Song> findAllBy(@Param("criteria") TextCriteria criteria);
}

