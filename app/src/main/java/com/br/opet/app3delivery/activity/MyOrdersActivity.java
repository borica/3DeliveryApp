package com.br.opet.app3delivery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.adapter.OrderAdapter;
import com.br.opet.app3delivery.service.DrawingService;
import com.br.opet.app3delivery.service.listeners.ListDrawingRequestListener;

import java.util.ArrayList;

public class MyOrdersActivity extends NoBarActitity {

    private ListView orderListView;
    private RelativeLayout noResultsBox;
    private RelativeLayout orderWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        orderListView = findViewById(R.id.orderListview);
        noResultsBox = findViewById(R.id.noItens);
        orderWrapper = findViewById(R.id.orderWrapper);

        getOrderList();
    }

    //Prepares Listview
    private void getOrderList() {
        activityActions.drawingService.listAllOrdersByUser(this, new ListDrawingRequestListener() {
            @Override
            public void onResponse(ArrayList listDrawing) {
                orderListView.setAdapter(new OrderAdapter(MyOrdersActivity.this, R.layout.list_row, listDrawing));

                if(listDrawing.size() > 0)
                    orderWrapper.setVisibility(View.VISIBLE);
                else
                    noResultsBox.setVisibility(View.VISIBLE);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(MyOrdersActivity.this, message, Toast.LENGTH_LONG);
            }
        });
    }

    public static class activityActions {
        public static DrawingService drawingService = new DrawingService();
    }
}