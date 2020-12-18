package org.jboss.resteasy.plugins.servlet.testapp;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/app")
@Path("/path")
public class AppAndResource extends Application {

    @GET
    public String foo() {
        return "foo";
    }
}