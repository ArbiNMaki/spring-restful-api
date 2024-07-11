package com.spring.restful.api.controller;

import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.ContactResponse;
import com.spring.restful.api.model.CreateContactReq;
import com.spring.restful.api.model.UpdateContactReq;
import com.spring.restful.api.model.WebResponse;
import com.spring.restful.api.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(path = "/api/contacts",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactReq req) {
        ContactResponse contactResponse = contactService.create(user, req);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(path = "/api/contacts/{contactId}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> get(User user, @PathVariable("contactId") String contactId) {
        ContactResponse contactResponse = contactService.get(user, contactId);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PutMapping(path = "/api/contacts/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> update(User user,
                                               @RequestBody UpdateContactReq req,
                                               @PathVariable("contactId") String contactId) {
        req.setId(contactId);

        ContactResponse contactResponse = contactService.update(user, req);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }
}
