package com.br.opet.app3delivery.service;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.app3delivery.application.ApplicationContext;
import com.br.opet.app3delivery.model.Drawing;
import com.br.opet.app3delivery.service.defaultRequest.RequestSingleton;
import com.br.opet.app3delivery.service.listeners.ListDrawingRequestListener;
import com.br.opet.app3delivery.service.listeners.StringResponseListener;
import com.br.opet.app3delivery.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrawingService {

    public void createNewDrawing(final Drawing newDrawing, final StringResponseListener stringResponseListener) {

        ApplicationContext applicationContext = (ApplicationContext) newDrawing.getmContext().getApplicationContext();
        final String token = applicationContext.getLoggedUser().getToken();

        try {

            RequestQueue requestQueue = RequestSingleton.getInstance(newDrawing.getmContext()).getRequestQueue();

            Gson gson = new Gson();
            JSONObject newDrawingJson = new JSONObject(gson.toJson(newDrawing));

            JsonObjectRequest newDrawingRequest = new JsonObjectRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.CREATE_DRAWING), newDrawingJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    stringResponseListener.onResponse();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    stringResponseListener.onError(error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //Request headers
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", token);

                    return headers;
                }
            };

            requestQueue.add(newDrawingRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAllOrdersByUser(final Context mContext, final ListDrawingRequestListener listDrawingRequestListener) {

        ApplicationContext applicationContext = (ApplicationContext) mContext.getApplicationContext();
        final String token = applicationContext.getLoggedUser().getToken();

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        JsonObjectRequest listDrawingsRequest = new JsonObjectRequest(Request.Method.GET, AppConstants.getFullRoute(AppConstants.LIST_DRAWING_BY_USER),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                     ArrayList drawingList = new ArrayList();
                    JSONArray allDrawings = response.getJSONArray("drawings");

                    for (int i = 0; i < allDrawings.length(); i++) {
                        JSONObject drawing = allDrawings.getJSONObject(i);
                        Drawing drawingObj = new Drawing(drawing.getString("id"), drawing.getString("name"), drawing.getDouble("height"), drawing.getDouble("width"), mContext);

                        drawingList.add(drawingObj);
                    }

                    listDrawingRequestListener.onResponse(drawingList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listDrawingRequestListener.onResponse(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listDrawingRequestListener.onError(error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //Request headers
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", token);

                return headers;
            }
        };

        requestQueue.add(listDrawingsRequest);
    }

    public void deleteDrawing(final Context mContext, String drawingId, final StringResponseListener stringResponseListener) {
        ApplicationContext applicationContext = (ApplicationContext) mContext.getApplicationContext();
        final String token = applicationContext.getLoggedUser().getToken();

        RequestQueue requestQueue = RequestSingleton.getInstance(mContext).getRequestQueue();

        try {

            Gson gson = new Gson();
            final JSONObject newDrawingJson = new JSONObject();
            newDrawingJson.put("id", drawingId);

            JsonObjectRequest deleteDrawingRequest = new JsonObjectRequest(Request.Method.DELETE, AppConstants.getFullRoute(AppConstants.DELETE_DRAWING), newDrawingJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    stringResponseListener.onResponse();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    stringResponseListener.onError(error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //Request headers
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("authorization", token);

                    return headers;
                }
                @Override
                public byte[] getBody() {
                    try {
                        return newDrawingJson.toString() == null ? null : newDrawingJson.toString().getBytes("utf-8");
                    } catch (UnsupportedEncodingException e){
                        return null;
                    }
                }
            };

            requestQueue.add(deleteDrawingRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
