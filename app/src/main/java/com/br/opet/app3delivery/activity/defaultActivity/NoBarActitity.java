package com.br.opet.app3delivery.activity.defaultActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.br.opet.app3delivery.R;

public class NoBarActitity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

}
