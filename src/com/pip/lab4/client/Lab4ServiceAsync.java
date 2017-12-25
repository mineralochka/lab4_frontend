package com.pip.lab4.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Lab4ServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
