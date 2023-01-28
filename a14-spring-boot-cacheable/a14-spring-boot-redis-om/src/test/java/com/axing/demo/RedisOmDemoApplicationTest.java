package com.axing.demo;

import com.axing.demo.dao.CompanyRepository;
import com.axing.demo.dao.PeopleRepository;
import com.axing.demo.model.Company;
import com.axing.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
class RedisOmDemoApplicationTest {

    @Autowired
    PeopleRepository repo;
    @Autowired
    CompanyRepository companyRepo;

    @Test
    void tes1() {
        List<Person> list = repo.findAll();
        System.out.println("list = " + list);
    }

    @Test
    void tes2() {
        Person person = new Person();
        person.setFirstName("Apple iPhone 14");
        Person person1 = repo.save(person);
        System.out.println("person1 = " + person1);
    }

    @Test
    void test3() {
        companyRepo.deleteAll();
        Company redis = Company.of("Redis", "https://redis.com", new Point(-122.066540, 37.377690), 526, 2011);
        redis.setTags(Set.of("fast", "scalable", "reliable"));

        companyRepo.save(redis);

        Optional<Company> redis1 = companyRepo.findOneByName("Redis");
        System.out.println("redis1 = " + redis1);

        Iterable<Company> redis2 = companyRepo.findAllByName("Redis");
        System.out.println("redis2 = " + redis2);
    }
}
