package com.spring.restful.api.service;

import com.spring.restful.api.entity.Address;
import com.spring.restful.api.entity.Contact;
import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.AddressResponse;
import com.spring.restful.api.model.CreateAddressReq;
import com.spring.restful.api.model.UpdateAddressReq;
import com.spring.restful.api.repository.AddressRepository;
import com.spring.restful.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ValidationService validationService;

    private AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    @Transactional
    public AddressResponse create(User user, CreateAddressReq req) {
        validationService.validate(req);

        Contact contact = contactRepository.findFirstByUserAndId(user, req.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(req.getStreet());
        address.setCity(req.getCity());
        address.setProvince(req.getProvince());
        address.setCountry(req.getCountry());
        address.setPostalCode(req.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Transactional(readOnly = true)
    public AddressResponse get(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        return toAddressResponse(address);
    }

    @Transactional
    public AddressResponse update(User user, UpdateAddressReq req) {
        validationService.validate(req);

        Contact contact = contactRepository.findFirstByUserAndId(user, req.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = addressRepository.findFirstByContactAndId(contact, req.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        address.setStreet(req.getStreet());
        address.setCity(req.getCity());
        address.setProvince(req.getProvince());
        address.setCountry(req.getCountry());
        address.setPostalCode(req.getPostalCode());
        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Transactional
    public void remove(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        addressRepository.delete(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponse> list(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));
        List<Address> addresses = addressRepository.findAllByContact(contact);

        return addresses.stream().map(this::toAddressResponse).toList();
    }
}
