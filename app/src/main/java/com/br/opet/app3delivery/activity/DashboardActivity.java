package com.br.opet.app3delivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.application.ApplicationContext;

public class DashboardActivity extends NoBarActitity implements View.OnClickListener {

    private static final String TAG = DashboardActivity.class.getName();

    private ApplicationContext applicationContext;

    private TextView dashboardWelcomeTxt;
    private CardView cardNewOrder;
    private CardView cardMyOrders;
    private RelativeLayout logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        applicationContext = (ApplicationContext) this.getApplicationContext();

        dashboardWelcomeTxt = findViewById(R.id.dashboardWelcomeTxt);
        cardNewOrder = findViewById(R.id.cardNewOrder);
        cardMyOrders = findViewById(R.id.cardMyOrders);
        logout = findViewById(R.id.logout);

        //Sets name of the user
        dashboardWelcomeTxt.setText("Bem-vindo " + applicationContext.getLoggedUser().getUser().getName() + "!\nComo podemos ajudar?");

        cardNewOrder.setOnClickListener(this);
        cardMyOrders.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.cardNewOrder:
                i = new Intent(this, NewOrderActivity.class);
                startActivity(i);
                break;
            case R.id.cardMyOrders:
                i = new Intent(this, MyOrdersActivity.class);
                startActivity(i);
                break;
            case R.id.logout:
                //Send to login
                i = new Intent(this, LoginActivity.class);

                //Invalidating current session
                applicationContext.setLoggedUser(null);
                startActivity(i);
                finish();
                break;
            default: break;
        }
    }

}