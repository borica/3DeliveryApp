package com.br.opet.app3delivery.service.defaultRequest;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestSingleton {

    private static RequestSingleton mInstance;
    private RequestQueue mRequestQueue;

    private RequestSingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RequestSingleton getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new RequestSingleton(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
