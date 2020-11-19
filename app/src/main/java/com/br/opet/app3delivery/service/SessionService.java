package com.br.opet.app3delivery.service;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.opet.app3delivery.model.Session;
import com.br.opet.app3delivery.model.UserModel;
import com.br.opet.app3delivery.service.defaultRequest.RequestSingleton;
import com.br.opet.app3delivery.service.listeners.SessionResponseListener;
import com.br.opet.app3delivery.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionService {

    public void authenticate(final Session newSession, final SessionResponseListener sessionResponseListener) {
        try {

            RequestQueue requestQueue = RequestSingleton.getInstance(newSession.getmContext()).getRequestQueue();
            Gson gson = new Gson();

            final JSONObject sendAuthRequest = new JSONObject(gson.toJson(newSession));

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.SESSION), sendAuthRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject responseUserJsonObj = response.getJSONObject("user");

                        //Authenticated Session Object
                        UserModel  authUser = new UserModel();

                        authUser.setName(responseUserJsonObj.getString("name"));
                        authUser.setEmail(responseUserJsonObj.getString("email"));

                        Session authSession = new Session(authUser, response.getString("token"));

                        sessionResponseListener.onResponse(authSession);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sessionResponseListener.onError(e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    String cause;

                    if(error.getMessage() == null) {
                        cause = new String(String.valueOf(error.networkResponse.statusCode));
                    } else {
                        cause = error.getMessage();
                    }

                    Log.e(this.getClass().toString(), "Erro ao autenticar:\n" + cause);
                    sessionResponseListener.onError(cause);
                }
            });

            requestQueue.add(stringRequest);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
