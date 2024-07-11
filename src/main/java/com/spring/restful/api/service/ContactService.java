package com.spring.restful.api.service;

import com.spring.restful.api.entity.Contact;
import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.ContactResponse;
import com.spring.restful.api.model.CreateContactReq;
import com.spring.restful.api.model.UpdateContactReq;
import com.spring.restful.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    private ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    @Transactional
    public ContactResponse create(User user, CreateContactReq req) {
        validationService.validate(req);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(req.getFirstName());
        contact.setLastName(req.getLastName());
        contact.setEmail(req.getEmail());
        contact.setPhone(req.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    @Transactional(readOnly = true)
    public ContactResponse get(User user, String id) {
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        return toContactResponse(contact);
    }

    @Transactional
    public ContactResponse update(User user, UpdateContactReq req) {
        validationService.validate(req);

        Contact contact = contactRepository.findFirstByUserAndId(user, req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contact.setFirstName(req.getFirstName());
        contact.setLastName(req.getLastName());
        contact.setEmail(req.getEmail());
        contact.setPhone(req.getPhone());
        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    @Transactional
    public void delete(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contactRepository.delete(contact);
    }
}
