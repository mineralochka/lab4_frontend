package com.pip.lab4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Lab4 implements EntryPoint {

    private AuthorisationClient authorisationClient = GWT.create(AuthorisationClient.class);
    private ChecksClient checksClient = GWT.create(ChecksClient.class);
    private final Label warning = new Label();
    private final Grid checksTable = new Grid(1, 4);
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://127.0.0.1:50000");

        redirect();

        if (Document.get().getElementById("welcomeTable") != null) {
            final TextBox user = new TextBox();
            final PasswordTextBox password = new PasswordTextBox();
            final Button login = new Button("Login");
            final Button register = new Button("Register");


            RootPanel.get("loginField").add(user);
            RootPanel.get("passwordField").add(password);
            RootPanel.get("buttonsField").add(login);
            RootPanel.get("buttonsField").add(register);


            login.addClickHandler(event -> login(user.getValue(), password.getValue()));
            register.addClickHandler(event -> register(user.getValue(), password.getValue()));
        }
        if (Document.get().getElementById("controlsTable") != null){
            final String user = Cookies.getCookie("user");
            final ListBox x = new ListBox();
            final TextBox y = new TextBox();
            final ListBox r = new ListBox();

            final Button checkButton = new Button("Check");

            final Button logoutButton = new Button("Logout");

            for (String var : Arrays.asList("-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2"))
                x.addItem(var);

            for (String var : Arrays.asList("-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2"))
                r.addItem(var);


            RootPanel.get("x").add(x);
            RootPanel.get("y").add(y);
            RootPanel.get("r").add(r);
            RootPanel.get("checkButton").add(checkButton);
            RootPanel.get("logout").add(logoutButton);
            RootPanel.get("warning").add(warning);
            RootPanel.get("previousChecks").add(checksTable);

            checkButton.addClickHandler(event -> check(user, x.getSelectedItemText(), y.getValue(), r.getSelectedItemText()));
            logoutButton.addClickHandler(event -> logout());

            checksTable.setText(0, 0, "x");
            checksTable.setText(0, 1, "y");
            checksTable.setText(0, 2, "r");
            checksTable.setText(0, 3, "result");

            fillTable(user);
            drawCanvas();
        }
    }

    public static native void drawCanvas() /*-{
        $wnd.drawCanvas(-2);
    }-*/;

    private void logout() {
        Cookies.removeCookie("user");
        Cookies.removeCookie("password");
        Window.Location.assign("/lab4/Lab4.html");
    }

    private void redirect(){
        String user = Cookies.getCookie("user");
        if (user == null){
            user = "user";
        }
        String password = Cookies.getCookie("password");
        if (password == null){
            password = "password";
        }
        authorisationClient.login(user, password, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if (aBoolean){
                    if (Window.Location.getPath().endsWith("Lab4.html"))
                        Window.Location.assign("/lab4/main.html");
                }
                else {
                    if (Window.Location.getPath().endsWith("main.html"))
                        Window.Location.assign("/lab4/Lab4.html");
                }
            }
        });
    }

    private void check(String user, String x, String y, String r){
        try {
            if (-3 <= Double.parseDouble(y) && Double.parseDouble(y) <= 5) {
                checksClient.check(user, x, y, r, new MethodCallback<Boolean>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Boolean aBoolean) {
                        checksTable.insertRow(1);
                        checksTable.setText(1, 0, x);
                        checksTable.setText(1, 1, y);
                        checksTable.setText(1, 2, r);
                        checksTable.setText(1, 3, aBoolean.toString());
                    }
                });
            } else {
                warning.setText("Y must be -3 to 5!");
            }
        }catch (NumberFormatException e){
            warning.setText("Y must be a number!");
        }
    }

    private void fillTable(String user){
        checksClient.previousChecks(user, new MethodCallback<List<Checks>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Checks> checksList) {
                for (Checks check : checksList) {
                    checksTable.insertRow(1);
                    checksTable.setText(1, 0, check.getX().toString());
                    checksTable.setText(1, 1, check.getY().toString());
                    checksTable.setText(1, 2, check.getR().toString());
                    checksTable.setText(1, 3, check.isResult().toString());
                }
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
                if (aBoolean) {
                    Cookies.setCookie("user", user);
                    Cookies.setCookie("password", password);
                    Window.Location.assign("/lab4/main.html");
                } else {
                    //TODO error
                }
            }
        });
    }

    private void register(String user, String password) {
        authorisationClient.register(user, password, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if (aBoolean) {
                    Cookies.setCookie("user", user);
                    Cookies.setCookie("password", password);
                    Window.Location.assign("/lab4/main.html");
                } else {
                    Window.alert("Error: id already taken!");
                }
            }
        });
    }
}
