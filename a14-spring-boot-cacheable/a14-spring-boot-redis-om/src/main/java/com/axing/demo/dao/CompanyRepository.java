package com.axing.demo.dao;

import com.axing.demo.model.Company;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface CompanyRepository extends RedisDocumentRepository<Company, String> {
    // find one by property
    Optional<Company> findOneByName(String name);

    Iterable<Company> findAllByName(String name);
}
