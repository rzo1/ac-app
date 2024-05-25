package org.example;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class MyServiceWithMyResource {

    @Resource
    private MyResource myResource;

    public MyResource getResource() {
        return myResource;
    }
}
