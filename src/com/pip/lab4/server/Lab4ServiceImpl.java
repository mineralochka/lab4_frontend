package com.pip.lab4.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pip.lab4.client.Lab4Service;

public class Lab4ServiceImpl extends RemoteServiceServlet implements Lab4Service {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}