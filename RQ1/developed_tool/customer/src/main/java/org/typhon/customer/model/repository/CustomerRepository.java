package org.typhon.customer.model.repository;

import org.typhon.customer.model.Customer;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	
	@ApiOperation("Get customer by oid")
	List<Customer> findByOid(@Param("oid") 
								@ApiParam(value="Oid")String oid);

	@ApiOperation("Get customer by name")
	List<Customer> findByName(@Param("name") 
								@ApiParam(value="Name")String name);

}

