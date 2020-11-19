package com.br.opet.app3delivery.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.app3delivery.model.Drawing;
import com.br.opet.app3delivery.service.defaultRequest.RequestSingleton;
import com.br.opet.app3delivery.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONObject;

public class DrawingService {

    //TODO IMPLEMENT CALLBACK INTERFACE
    public void createNewDrawing(Drawing newDrawing) {

        try {

            RequestQueue requestQueue = RequestSingleton.getInstance(newDrawing.getmContext()).getRequestQueue();

            Gson gson = new Gson();
            JSONObject newDrawingJson = new JSONObject(gson.toJson(newDrawing));

            JsonObjectRequest newDrawingRequest = new JsonObjectRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.DRAWING), newDrawingJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO ONRESPONSE CALLBACK
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //TODO ONERROR CALLBACK
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
