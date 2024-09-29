package com.sheom.foodle.repository;

import com.sheom.foodle.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
