package com.pip.lab4.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Lab4Service")
public interface Lab4Service extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use Lab4Service.App.getInstance() to access static instance of Lab4ServiceAsync
     */
    public static class App {
        private static Lab4ServiceAsync ourInstance = GWT.create(Lab4Service.class);

        public static synchronized Lab4ServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
