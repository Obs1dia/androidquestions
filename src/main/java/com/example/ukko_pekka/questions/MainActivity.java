package com.example.ukko_pekka.questions;

import android.app.Dialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myLIstView;

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://codez.savonia.fi/jussi/j2me/test_json.php?query=Q&delay=1";
        final ArrayList<Kysymys> kysymykset = new ArrayList();

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("reponssi", response.toString());
                        try {


                            for (int i=0;i<response.length();i++) {
                                JSONObject jsonObject1 = response.getJSONObject(i);
                                Kysymys k = new Kysymys(jsonObject1);
                                kysymykset.add(k);
                            }
                            MyBaseAdapter adapter = new MyBaseAdapter(getApplicationContext(), kysymykset);
                            ListView lw = (ListView) findViewById(R.id.listview1);
                            lw.setAdapter(adapter);
                            lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    showRadioButtonDialog();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("That didn't work!","my bad");
            }
        });
        queue.add(stringRequest);


    }
    private void showRadioButtonDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        List<String> stringList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            stringList.add("RadioButton " + (i + 1));
        }
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        dialog.show();
    }
}
