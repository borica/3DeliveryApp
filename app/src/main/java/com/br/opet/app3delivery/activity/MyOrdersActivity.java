package com.br.opet.app3delivery.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.adapter.OrderAdapter;
import com.br.opet.app3delivery.model.Drawing;

import java.util.ArrayList;

public class MyOrdersActivity extends NoBarActitity {

    ListView orderListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        orderListview = findViewById(R.id.orderListview);

        orderListview.setAdapter(new OrderAdapter(this, R.layout.list_row, mockOrders()));

    }

    private ArrayList mockOrders() {
        ArrayList orders = new ArrayList();

        orders.add(new Drawing("Chaveiro carrinho", 22.3, 10.2, this));
        orders.add(new Drawing("Decorativo nave-espacial", 15.2, 30.2, this));
        orders.add(new Drawing("Controle portão-eletrico", 5.3, 3.2, this));

        return orders;
    }
}