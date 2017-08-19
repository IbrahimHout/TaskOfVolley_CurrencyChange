package com.example.trest.taskofvolley_currencychange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText fromET = (EditText) findViewById(R.id.fromed);
        final EditText toET = (EditText) findViewById(R.id.toed);
        final EditText valueET = (EditText) findViewById(R.id.valueed);
        final TextView tvres = (TextView) findViewById(R.id.result);
        Button btn  = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = fromET.getText().toString();
                final String to  = toET.getText().toString();
                final double value = Double.parseDouble(valueET.getText().toString());

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://api.fixer.io/latest?base="+from+"?symbols="+to, null, new Response.Listener<JSONObject>() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://api.fixer.io/latest?base="+from, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObjcet = (JSONObject) response.get("rates");
                            double res = jsonObjcet.getDouble(to);
                            tvres.setText("= "+(value*res)+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("val","Fail");

                    }
                });

                queue.add(jsonObjectRequest);


            }
        });

    }
}
