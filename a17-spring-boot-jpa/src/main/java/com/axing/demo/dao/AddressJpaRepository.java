package com.axing.demo.dao;

import com.axing.demo.model.Address;
import com.axing.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, Integer> {
}
