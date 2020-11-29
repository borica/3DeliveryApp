package com.br.opet.app3delivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.MyOrdersActivity;
import com.br.opet.app3delivery.model.Drawing;
import com.br.opet.app3delivery.service.listeners.StringResponseListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Drawing> {

    private Context mContext;
    private int mResource;
    private ArrayList objects;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Drawing> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView orderNameTxt = convertView.findViewById(R.id.orderNameTxt);
        TextView orderSizeTxt = convertView.findViewById(R.id.orderSizeTxt);
        TextView orderDataEnvelope = convertView.findViewById(R.id.dataEnvelope);

        ImageView deleteBtn = convertView.findViewById(R.id.delete);
        ImageView editBtn = convertView.findViewById(R.id.edit);

        Gson gson = new Gson();

        orderNameTxt.setText(getItem(position).getName());
        orderSizeTxt.setText(getItem(position).getWidth() + " x " + getItem(position).getHeight());
        orderDataEnvelope.setText(gson.toJson(getItem(position)));

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MyOrdersActivity.activityActions.drawingService.deleteDrawing(mContext, getItem(position).getId(), new StringResponseListener() {
                    @Override
                    public void onResponse() {
                        objects.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "Pedido deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(mContext, "Erro ao deletar pedido: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return convertView;
    }
}
