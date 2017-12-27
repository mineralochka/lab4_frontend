package com.pip.lab4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Lab4 implements EntryPoint {

    private AuthorisationClient authorisationClient = GWT.create(AuthorisationClient.class);

    private ChecksClient checksClient = GWT.create(ChecksClient.class);
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://127.0.0.1:50000");

        final TextBox user = new TextBox();
        final PasswordTextBox password = new PasswordTextBox();

        final Button login = new Button("Login");
        final Button register = new Button("Register");

        Node a = RootPanel.getBodyElement().getFirstChild();
        RootPanel.get("loginField").add(user);
        RootPanel.get("passwordField").add(password);
        RootPanel.get("loginButton").add(login);
        RootPanel.get("registerButton").add(register);


        login.addClickHandler(event -> login(user.getValue(), password.getValue()));

        register.addClickHandler(event -> register(user.getValue(), password.getValue()));
    }

    private void register(String user, String password) {
        authorisationClient.register(user, password, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if (aBoolean){
                    //TODO reload page or navigate to controls
                }
                else{
                    //TODO show "id already taken" or something
                }

            }
        });
    }

    private void check(String x, String y, String r){
        checksClient.check(x, y, r, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                //TODO add row to the table of results
            }
        });
    }

    private void login(String user, String password) {
        authorisationClient.login(user, password, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if (aBoolean){
                    //TODO navigation
                }
                else {
                    //TODO error
                }
            }
        });
    }
}
