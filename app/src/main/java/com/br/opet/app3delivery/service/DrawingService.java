package com.br.opet.app3delivery.service;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.app3delivery.application.ApplicationContext;
import com.br.opet.app3delivery.model.Drawing;
import com.br.opet.app3delivery.service.defaultRequest.RequestSingleton;
import com.br.opet.app3delivery.service.listeners.DrawingResponseListener;
import com.br.opet.app3delivery.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DrawingService {

    public void createNewDrawing(final Drawing newDrawing, final DrawingResponseListener drawingResponseListener) {

        ApplicationContext applicationContext = (ApplicationContext) newDrawing.getmContext().getApplicationContext();
        final String token = applicationContext.getLoggedUser().getToken();

        try {

            RequestQueue requestQueue = RequestSingleton.getInstance(newDrawing.getmContext()).getRequestQueue();

            Gson gson = new Gson();
            JSONObject newDrawingJson = new JSONObject(gson.toJson(newDrawing));

            JsonObjectRequest newDrawingRequest = new JsonObjectRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.CREATE_DRAWING), newDrawingJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    drawingResponseListener.onResponse();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    drawingResponseListener.onError(error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //Request headers
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");

                    //TODO TOKEN CURRENTLY NOT BEING ACCEPTED BY API
                    headers.put("Authorization", token);

                    return headers;
                }
            };

            requestQueue.add(newDrawingRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
