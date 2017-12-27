package com.pip.lab4.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("")
public interface ChecksClient extends RestService{
    @GET
    @Path("check")
    void check(@QueryParam("x") String x, @QueryParam("y") String y, @QueryParam("r") String r, MethodCallback<Boolean> result);
}
