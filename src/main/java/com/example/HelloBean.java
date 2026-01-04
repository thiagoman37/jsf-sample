package com.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloBean {

    public String getMessage() {
        return "Hello from Managed Bean!";
    }
}
