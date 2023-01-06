package com.axing.demo.dao;

import com.axing.demo.model.Person;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

public interface PeopleRepository extends RedisDocumentRepository<Person, String> {

    Iterable<Person> findByAgeBetween(int minAge, int maxAge);
}
