package org.example;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import org.apache.openejb.junit5.ExtensionMode;
import org.apache.openejb.junit5.RunWithApplicationComposer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWithApplicationComposer(mode = ExtensionMode.PER_JVM)
public class MyResourceInjectionTest {

    @Resource
    private MyResource myResource;

    @Inject
    private MyServiceWithMyResource service;

    @Inject
    private App app;

    @Test
    public void testInject() {
        // In App..
        assertNotNull(app, "app must not be null");
        assertNotNull(app.getResource(), "app#myResource must not be null");
        assertEquals("file.txt", app.getResource().getFilePath(), "app#myResource#filePath should equal 'file.txt'");
        // In Service..
        assertNotNull(service, "service must not be null");
        assertNotNull(service.getResource(), "service#myResource must not be null");
        assertEquals("file.txt", service.getResource().getFilePath(), "service#myResource#filePath should equal 'file.txt'");
        // In Test.. --> fails with 10.0-M1
        assertNotNull(myResource, "'myResource' in Test must not be null");
        assertEquals("file.txt", myResource.getFilePath(), "myResource#filePath in Test should equal 'file.txt'");
    }

}
