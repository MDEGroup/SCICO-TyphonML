package org.typhon.purchasedetails.model.repository;

import org.typhon.purchasedetails.model.PurchaseDetails;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RepositoryRestResource(collectionResourceRel = "purchasedetails", path = "purchasedetails")
public interface PurchaseDetailsRepository extends PagingAndSortingRepository<PurchaseDetails, Long> {
	
}

