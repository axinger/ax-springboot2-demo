package com.xing;

import com.xing.entity.Dog;
import com.xing.entity.Person;
import com.xing.repository.DogRepository;
import com.xing.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

@SpringBootTest
@Slf4j
class A21SpringBootMongodbApplicationTests {


    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private DogRepository dogRepository;

    @Test
    void save() {
        Person person = new Person();
        person.setName("jim");
        person.setAge(10);
        final Person save = personRepository.save(person);
        System.out.println("save = " + save);


        Dog dog = new Dog();
        dog.setName("golf");
        dog.setAge(9);


        dogRepository.save(dog);
    }

    @Test
    void findAll() {

        final List<Person> all = personRepository.findAll();
        System.out.println("all = " + all);
    }

    @Test
    void findAllPage() {

        PageRequest pageRequest = PageRequest.of(0, 10);
        final Page<Person> personPage = personRepository.findAll(pageRequest);

        System.out.println("personPage = " + personPage);
        System.out.println("personPage.getContent() = " + personPage.getContent());
    }

    @Test
    void findAllName() {
        final Person name = personRepository.getByName("jim");
        System.out.println("name = " + name);
    }

    @Test
    void findAllListName() {
        final List<Person> list = personRepository.findListByName("jim");
        System.out.println("list = " + list);
    }

    @Test
    void findAllLike() {
        final List<Person> list = personRepository.findByNameLike("jim");
        System.out.println("list = " + list);
    }

    @Test
    void findAll_Sort() {
        List<Person> list = personRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        System.out.println("list = " + list);
    }


    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    void save_dog() {

        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            if (i%2==0){
                dog.setName("name"+i);
                dog.setAddress("address"+i);
            }
            dog.setAge(i);
            final Dog save = mongoTemplate.save(dog);
            System.out.println("save = " + save);
        }

    }


    @Test
    void query_dog() {

        /// Example 有局限
//        BeanUtil.copyProperties(queryVo, hospital);
//        Example<Hospital> example = Example.of(hospital, matcher);
//        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);


        Pageable pageable = PageRequest.of(0, 2);


        Query query = new Query();

        final Criteria criteria = Criteria.where("age").gte(9);
        criteria.and("name").is("golf");

        query.addCriteria(criteria);


//        query.addCriteria(new Criteria ().orOperator(
//                Criteria.where("name").not().isNull().and("age").not().isNull(),
//                ));


//        query.addCriteria(new Criteria().orOperator(Criteria.where("name").regex("tom"),
//                Criteria.where("idno").regex(mdo.getIdno())));


        query.with(Sort.by(Sort.Direction.DESC, "age"));
//        query.skip(0);
//        query.limit(10)

        ;

        // 分页
        query.with(pageable);

        long totoal = mongoTemplate.count(query, Dog.class);
        System.out.println("totoal = " + totoal);


        log.info("分页参数 = {},isPaged = {}, isUnpaged = {}", pageable.getPageNumber(),
                pageable.isPaged(),
                pageable.isUnpaged()
                );


//        query.fields().include("name").include("status")
        ;
        final List<Dog> dogList = mongoTemplate.find(query, Dog.class);
        System.out.println("dogList = " + dogList);
    }

    ///不能同时为空
    @Test
    void query_dog_2() {

        Pageable pageable = PageRequest.of(0, 10);

        Query query = new Query();


        /// 不能同时为空
        query.addCriteria(new Criteria ().orOperator(
                Criteria.where("name").exists(true),
                Criteria.where("address").exists(true)
        ));

        query.with(Sort.by(Sort.Direction.ASC, "age"));

        // 分页
        query.with(pageable);

        long total = mongoTemplate.count(query, Dog.class);
        System.out.println("total = " + total);
        final List<Dog> dogList = mongoTemplate.find(query, Dog.class);
        dogList.forEach(varl-> System.out.println("getName = " + varl.getName()+" getAddress = "+varl.getAddress()));
    }

    @Test
    void query_dog_limit() {

        Query query = new Query();

        query.addCriteria( Criteria.where("age").is(4));

        /// 不能同时为空
        query.addCriteria(new Criteria ().orOperator(
                Criteria.where("name").exists(true),
                Criteria.where("address").exists(true)
        ));

//        query.with(Sort.by(Sort.Direction.DESC, "_id"));
//        query.limit(2);


        long total = mongoTemplate.count(query, Dog.class);
        System.out.println("total = " + total);
        final List<Dog> dogList = mongoTemplate.find(query, Dog.class);
        dogList.forEach(varl-> System.out.println("age = "+varl.getAge()+"getName = " + varl.getName()+" getAddress = "+varl.getAddress()));
    }

    @Test
    void test1(){
List list = Arrays.asList(1,4,2);

//        list.stream().sorted(Comparator.reverseOrder()).forEach(val-> System.out.println("val = " + val));

//        list.stream().sorted(Comparator.reverse).forEach(val-> System.out.println("val = " + val));
        list.stream().forEachOrdered(val-> System.out.println("val = " + val));
//        Collections.reverse(list);list.forEach(val-> System.out.println("val = " + val));
    }
}
