package com.example.nlp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity implements View.OnClickListener {
    ImageView img1;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        img1=(ImageView) findViewById(R.id.imageView);
        ed1=(EditText) findViewById(R.id.editTextTextPersonName4);
        ed2=(EditText) findViewById(R.id.editTextTextPersonName5);
        ed3=(EditText) findViewById(R.id.editTextTextPersonName6);
        ed4=(EditText) findViewById(R.id.editTextTextPersonName7);
        ed5=(EditText) findViewById(R.id.editTextTextPersonName8);
        ed6=(EditText) findViewById(R.id.editTextTextPersonName9);
        ed7=(EditText) findViewById(R.id.editTextTextPersonName10);
        ed8=(EditText) findViewById(R.id.editTextTextPersonName11);
        btn1=(Button) findViewById(R.id.button3);
        btn1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/view_visited_game";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                            }

                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("uid","");
                params.put("uid",id);
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);






        Intent ii=new Intent(getApplicationContext(),Login.class);
        startActivity(ii);
    }
}