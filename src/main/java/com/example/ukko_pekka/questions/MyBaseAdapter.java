package com.example.ukko_pekka.questions;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.ukko_pekka.questions.Kysymys;
import com.example.ukko_pekka.questions.R;

import java.util.ArrayList;


public class MyBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Kysymys> kysymykset;

    public MyBaseAdapter(Context context, ArrayList<Kysymys> henkilot) {
        this.context = context;
        this.kysymykset = henkilot;
    }
    @Override
    public int getCount() {
        return kysymykset.size();
    }

    @Override
    public Object getItem(int position) {
        return kysymykset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.listview_layout, parent, false);
        }

        Kysymys currentQ = (Kysymys) getItem(position);

        TextView q_text =(TextView) convertView.findViewById(R.id.tw_kysymys);
        TextView id_text = (TextView) convertView.findViewById(R.id.tw_q_id);
        q_text.setText(currentQ.getQuestion());
        id_text.setText("Question: " + currentQ.getId());
        return convertView;
    }
}
