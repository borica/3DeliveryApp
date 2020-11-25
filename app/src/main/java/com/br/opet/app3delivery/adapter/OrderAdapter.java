package com.br.opet.app3delivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.model.Drawing;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Drawing> {

    private Context mContext;
    private int mResource;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Drawing> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView orderNameTxt = convertView.findViewById(R.id.orderNameTxt);
        TextView orderSizeTxt = convertView.findViewById(R.id.orderSizeTxt);

        orderNameTxt.setText(getItem(position).getName());
        orderSizeTxt.setText(getItem(position).getWidth() + " x " + getItem(position).getHeight());

        return convertView;
    }
}
