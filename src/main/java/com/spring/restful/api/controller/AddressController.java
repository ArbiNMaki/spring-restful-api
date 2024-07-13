package com.spring.restful.api.controller;

import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.AddressResponse;
import com.spring.restful.api.model.CreateAddressReq;
import com.spring.restful.api.model.WebResponse;
import com.spring.restful.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/api/contacts/{contactId}/addresses")
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressReq req,
                                               @PathVariable("contactId") String contactId) {
        req.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, req);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }
}
