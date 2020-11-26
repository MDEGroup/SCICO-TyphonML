package org.typhon.customerdetails.model.repository;

import org.typhon.customerdetails.model.CustomerDetails;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customerdetails", path = "customerdetails")
public interface CustomerDetailsRepository extends PagingAndSortingRepository<CustomerDetails, String> {

}

