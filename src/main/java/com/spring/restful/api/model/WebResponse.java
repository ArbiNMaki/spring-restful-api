package com.spring.restful.api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

    private T data;

    private String errors;

    private PagingResponse paging;
}
