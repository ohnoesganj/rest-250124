package com.example.rest.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RsData<T> {
    private String code;
    private String msg;
    private T data;

    public RsData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getStatusCode() {
        String statusCodeStr = code.split("-")[0];
        return Integer.parseInt(statusCodeStr);
    }
}
