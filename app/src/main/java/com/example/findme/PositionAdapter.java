package com.example.findme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class PositionAdapter extends BaseAdapter {
    Context context;
    ArrayList<Position> data;

    public PositionAdapter(Context context, ArrayList<Position> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        CardView element=(CardView) layoutInflater.inflate(R.layout.view_position,null);

        TextView tv_id=element.findViewById(R.id.tv_id_view);
        TextView tv_lat=element.findViewById(R.id.tv_lat_view);
        TextView tv_lon=element.findViewById(R.id.tv_lon_view);
        TextView tv_num=element.findViewById(R.id.tv_num_view);
        Button btncall=element.findViewById(R.id.btn_call_view);
        Button btnsms=element.findViewById(R.id.btn_sms_view);
        Button btnmap=element.findViewById(R.id.btn_map_view);

        Position position=data.get(i);
        tv_id.setText(""+position.id);
        tv_num.setText(""+position.numero);
        tv_lat.setText(""+position.latituden);
        tv_lon.setText(""+position.longitude);

        return element;
    }
}
