package org.example;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import org.apache.openejb.junit5.ExtensionMode;
import org.apache.openejb.junit5.RunWithApplicationComposer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWithApplicationComposer(mode = ExtensionMode.PER_JVM)
public class MyResourceInjectionTest {

    @Resource(name="MyResource")
    private MyResource myResource;

    @Inject
    private MyServiceWithMyResource service;

    @Inject
    private App app;

    @Test
    public void testInject() {
        assertNotNull(app, "app must not be null");
        assertNotNull(app.getResource(), "app#resource must not be null");
        assertNotNull(service, "service must not be null");
        assertNotNull(service.getMyResource(), "service#resource must not be null");
        assertNotNull(myResource, "resource must not be null"); // fail
    }

}
