package com.pip.lab4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Lab4 implements EntryPoint {

    private final TextBox user = new TextBox();
    private final PasswordTextBox password = new PasswordTextBox();
    private RestClient client = GWT.create(RestClient.class);
    private final VerticalPanel buttonsPanel = new VerticalPanel();
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://127.0.0.1:50000");

        final HorizontalPanel userPanel = new HorizontalPanel();
        final HorizontalPanel passwordPanel = new HorizontalPanel();


        final Button login = new Button("Login");
        final Button register = new Button("Register");

        userPanel.add(new Label("id:"));
        userPanel.add(user);

        passwordPanel.add(new Label("password:"));
        passwordPanel.add(password);

        buttonsPanel.add(login);
        buttonsPanel.add(register);

        RootPanel.get().add(userPanel);
        RootPanel.get().add(passwordPanel);
        RootPanel.get().add(buttonsPanel);


        login.addClickHandler(event -> login());

        register.addClickHandler(event -> register());
    }

    private void register() {
        client.register(user.getValue(), password.getValue(), new MethodCallback<Boolean>() {
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

    private void login() {
        client.login(user.getValue(), password.getValue(), new MethodCallback<Boolean>() {
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
