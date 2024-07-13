package com.spring.restful.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchContactReq {

    private String name;

    private String email;

    private String phone;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
