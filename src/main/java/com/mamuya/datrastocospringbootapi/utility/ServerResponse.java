package com.mamuya.datrastocospringbootapi.utility;

public interface ServerResponse {
    Response ObjectRequestMismatch = new Response("Failed", 1104, "Request does not match Server Object", null);
    Response SuccessfulRequestExecution = new Response("Success", 1100, "Operation Successfull");
    Response ObjectForIdentityNotFound = new Response("Failed", 1102, "Object For Identity not Found");
}
