package com.br.opet.app3delivery.service.listeners;

import com.br.opet.app3delivery.model.Session;

public interface SessionResponseListener {

    void onError(String message);

    void onResponse(Session responseSession);
}
