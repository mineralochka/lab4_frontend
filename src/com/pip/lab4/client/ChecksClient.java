package com.pip.lab4.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("")
public interface ChecksClient extends RestService{
    @GET
    @Path("check")
    void check(@QueryParam("user") String user, @QueryParam("x") String x, @QueryParam("y") String y, @QueryParam("r") String r, MethodCallback<Boolean> result);

    @GET
    @Path("previousChecks")
    void previousChecks(@QueryParam("user") String user, MethodCallback<List<Checks>> result);
}
