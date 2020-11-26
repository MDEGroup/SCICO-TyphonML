package org.typhon.similarity.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.typhon.similarity.model.Similarity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RepositoryRestResource(collectionResourceRel = "similarity", path = "similarity")
public interface SimilarityRepository extends PagingAndSortingRepository<Similarity, String> {
	
	@ApiOperation("Get similarity by name")
	List<Similarity> findByName(@Param("name") 
								@ApiParam(value="Name")String name);

	@ApiOperation("Get similarity by value")
	List<Similarity> findByValue(@Param("value") 
								@ApiParam(value="Value")String value);

	
	
}