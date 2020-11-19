package com.br.opet.app3delivery.activity;

import android.content.Intent;
import android.os.Bundle;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;

public class MainActivity extends NoBarActitity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start Login
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);

    }

}