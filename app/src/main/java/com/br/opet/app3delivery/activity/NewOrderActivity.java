package com.br.opet.app3delivery.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.model.Drawing;
import com.br.opet.app3delivery.service.DrawingService;
import com.br.opet.app3delivery.service.listeners.StringResponseListener;

public class NewOrderActivity extends NoBarActitity implements View.OnClickListener, View.OnFocusChangeListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = NewOrderActivity.class.getName();

    private TextView pricePreviewValue;
    private TextView providerDisclaimer;
    private EditText orderName;
    private EditText orderWidth;
    private EditText orderHeight;
    private Button newOrderBtn;
    private Spinner providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        orderName = findViewById(R.id.orderName);
        orderWidth = findViewById(R.id.orderWidth);
        orderHeight = findViewById(R.id.orderHeight);
        newOrderBtn = findViewById(R.id.newOrderBtn);
        providers = findViewById(R.id.providerSpinner);
        pricePreviewValue = findViewById(R.id.pricePreviewValue);
        providerDisclaimer = findViewById(R.id.providerDisclaimer);

        orderName.setOnClickListener(this);
        orderWidth.setOnClickListener(this);
        orderHeight.setOnClickListener(this);
        newOrderBtn.setOnClickListener(this);

        orderWidth.setOnFocusChangeListener(this);
        orderHeight.setOnFocusChangeListener(this);

        //Populating Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.providersMock, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        providers.setAdapter(spinnerAdapter);
        providers.setOnItemSelectedListener(this);

        pricePreviewValue.setText("0.0");
    }

    @Override
    public void onClick(View view) {

        if(valid()) {

            Drawing newOrderDrawing = new Drawing(null, orderName.getText().toString(), Double.parseDouble(orderWidth.getText().toString()), Double.parseDouble(orderHeight.getText().toString()), this);

            new DrawingService().createNewDrawing(newOrderDrawing, new StringResponseListener() {
                @Override
                public void onError(String message) {
                    Log.e(TAG,  "Erro ao salvar novo pedido:\n"+message);
                    Toast.makeText(NewOrderActivity.this, "Erro ao salvar novo pedido!", Toast.LENGTH_LONG);
                }

                @Override
                public void onResponse() {
                    Toast.makeText(NewOrderActivity.this, "Pedido foi realizado com sucesso!", Toast.LENGTH_LONG);
                }
            });

        } else {
            Toast.makeText(this, "Favor preencher os dados corretamente!", Toast.LENGTH_LONG);
        }

    }

    private boolean valid() {
        boolean isValid = true;

        if(orderName.getText().toString().isEmpty()) {
            orderName.setError("Favor informar nome do pedido!");
            isValid = false;
        }
        if(orderWidth.getText().toString().isEmpty()) {
            orderWidth.setError("Favor informar o valor de largura do pedido!");
            isValid = false;
        }
        if(orderHeight.getText().toString().isEmpty()) {
            orderHeight.setError("Favor informar o valor de altura do pedido!");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();

        switch(selected) {
            case "3DPrints":
                providerDisclaimer.setText("Vantagens: Este fornecedor oferece desconto de 10% para pedidos em lote.");
                break;
            case "Third Dimension":
                providerDisclaimer.setText("Vantagens: Este fornecedor oferece frete grátis para pedidos de valor acima de R$100,00 e peso menor que 50KG");
                break;
            case "Impressão Elevada":
                providerDisclaimer.setText("Vantagens: Este fornecedor oferece cartela de pontos para descontos futuros/");
                break;
            case "Arte Impressa":
                providerDisclaimer.setText("Vantagens: Este fornecedor oferece desconto em impressões com material Poliuretano.");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onFocusChange(View view, boolean b) {

        if(!orderHeight.getText().toString().isEmpty() && !orderWidth.getText().toString().isEmpty()) {
            double height = Double.valueOf(orderHeight.getText().toString());
            double width =  Double.valueOf(orderWidth.getText().toString());

            if (height <= 30 || width <= 30) {
                pricePreviewValue.setText("100");;
            }

            if (height >= 31 &&  height <= 70 || width >= 31 && width <= 70) {
                pricePreviewValue.setText("200");;
            }

            if (height >= 71 && height <= 120 || width >= 71 && width <= 120) {
                pricePreviewValue.setText("350");;
            }

            if (height >= 121 || width >= 121) {
                pricePreviewValue.setText("500");;
            }
        }
    }
}