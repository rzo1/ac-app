package org.example;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class MyServiceWithMyResource {

    @Resource(name = "medicalAbbreviations")
    private MyResource myResource;

    public MyResource getMyResource() {
        return myResource;
    }
}
