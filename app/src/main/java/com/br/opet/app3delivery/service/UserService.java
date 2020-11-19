package com.br.opet.app3delivery.service;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.br.opet.app3delivery.model.UserModel;
import com.br.opet.app3delivery.service.defaultRequest.RequestSingleton;
import com.br.opet.app3delivery.util.AppConstants;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class UserService {

    public Boolean saveUser(final UserModel newUser) {
        try{

            RequestQueue requestQueue = RequestSingleton.getInstance(newUser.getmContext()).getRequestQueue();
            Gson gson = new Gson();

            final Boolean[] success = {false};

            final String requestBody  = gson.toJson(newUser);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.USER), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    success[0] = true;
                    Toast.makeText(newUser.getmContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(this.getClass().toString(), "Erro ao salvar usuário:\n" + error.getMessage());
                    Toast.makeText(newUser.getmContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public String getBodyContentType() {return "application/json; charset=utf-8";}

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e){
                        return null;
                    }
                }
            };

            requestQueue.add(stringRequest);

            return success[0];
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
