package com.br.opet.app3delivery.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;

public class NewOrderActivity extends NoBarActitity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
    }
}