package com.pip.lab4.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

@Path("")
public interface AuthorisationClient extends RestService {
    @GET
    @Path("login")
    void login(@QueryParam("id") String id, @QueryParam("password") String password, MethodCallback<Boolean> result);

    @GET
    @Path("register")
    void register(@QueryParam("id") String id, @QueryParam("password") String password, MethodCallback<Boolean> result);
}
