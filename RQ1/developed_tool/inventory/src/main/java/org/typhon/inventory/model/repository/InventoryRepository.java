package org.typhon.inventory.model.repository;

import org.typhon.inventory.model.Inventory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {
	
	@ApiOperation("Get inventory by oid")
	List<Inventory> findByOid(@Param("oid") 
								@ApiParam(value="Oid")String oid);

	@ApiOperation("Get inventory by band")
	List<Inventory> findByBand(@Param("band") 
								@ApiParam(value="Band")String band);

	@ApiOperation("Get inventory by name")
	List<Inventory> findByName(@Param("name") 
								@ApiParam(value="Name")String name);

}

