package com.spring.restful.api.controller;

import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.AddressResponse;
import com.spring.restful.api.model.CreateAddressReq;
import com.spring.restful.api.model.UpdateAddressReq;
import com.spring.restful.api.model.WebResponse;
import com.spring.restful.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/api/contacts/{contactId}/addresses",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressReq req,
                                               @PathVariable("contactId") String contactId) {
        req.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, req);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @GetMapping(path = "/api/contacts/{contactId}/addresses/{addressId}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable("contactId") String contactId,
                                            @PathVariable("addressId") String addressId) {
        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @PutMapping(path = "/api/contacts/{contactId}/addresses/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddressResponse> update(User user,
                                               @RequestBody UpdateAddressReq req,
                                               @PathVariable("contactId") String contactId,
                                               @PathVariable("addressId") String addressId) {
        req.setContactId(contactId);
        req.setAddressId(addressId);

        AddressResponse addressResponse = addressService.update(user, req);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @DeleteMapping(path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> remove(User user,
                                               @PathVariable("contactId") String contactId,
                                               @PathVariable("addressId") String addressId) {
        addressService.remove(user, contactId, addressId);
        return WebResponse.<String>builder().data("Ok").build();
    }
}
