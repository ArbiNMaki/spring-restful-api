package com.spring.restful.api.repository;

import com.spring.restful.api.entity.Address;
import com.spring.restful.api.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findFirstByContactAndId(Contact contact, String id);
}
