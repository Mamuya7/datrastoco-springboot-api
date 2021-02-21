package com.mamuya.datrastocospringbootapi.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {
    private String status;
    private int code;
    private String message;
    private Object data;

    public Response(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
