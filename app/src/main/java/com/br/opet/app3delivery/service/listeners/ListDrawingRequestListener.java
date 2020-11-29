package com.br.opet.app3delivery.service.listeners;

import java.util.ArrayList;

public interface ListDrawingRequestListener {

    void onResponse(ArrayList listDrawing);

    void onError(String message);

}
