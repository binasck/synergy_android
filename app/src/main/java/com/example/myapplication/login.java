package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button login, signup;
    TextView forgot;
    CheckBox keep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        login = (Button) findViewById(R.id.button);
        signup = (Button) findViewById(R.id.button2);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == signup) {
            Intent i = new Intent(getApplicationContext(), Signup.class);
            startActivity(i);

        } else if (view == forgot) {

        } else if (view == login) {
            final String usrnm = username.getText().toString();
            final String pass = password.getText().toString();
            if (usrnm.length() == 0) {
                username.setError("Missing");
            } else if (pass.length() == 0) {
                password.setError("Missing");
            } else {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_login_post";


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
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", jsonObj.getString("lid"));
//                                    ed.putString("lid",jsonObj.getString("status"));
                                        ed.commit();

                                        if (jsonObj.getString("type").equalsIgnoreCase("student")) {

                                            Intent it = new Intent(getApplicationContext(), Hom.class);
                                            startActivity(it);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Not user +++++++ check login page", Toast.LENGTH_LONG).show();

                                            Intent it = new Intent(getApplicationContext(), login.class);
                                            startActivity(it);
                                        }
                                    }


                                    // }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Not a valid user!Please Signup", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
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


                        params.put("username", usrnm);
                        params.put("password", pass);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}