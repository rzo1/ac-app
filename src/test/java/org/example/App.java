package org.example;


import jakarta.annotation.Resource;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.testing.*;
import org.apache.openejb.testing.Module;

import java.util.Properties;

@Application
public class App {

    @Module
    @Classes(cdi = true, value = {App.class, MyServiceWithMyResource.class})
    public EjbJar modules() {
        return new EjbJar();
    }

    @Configuration
    public Properties config() {
        final Properties p = new Properties();
        p.put("myResource", "new://Resource?class-name=org.example.MyResource" +
                "&constructor=filePath, splitChar");
        p.put("myResource.filePath", "file.txt");
        p.put("myResource.splitChar", ";");
        return p;
    }

    @Resource(name = "myResource")
    private MyResource resource;

    public MyResource getResource() {
        return resource;
    }

    public void setResource(MyResource resource) {
        this.resource = resource;
    }
}
