package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class change_password extends AppCompatActivity implements View.OnClickListener {
EditText oldpass,newpass,confirm;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldpass=(EditText)  findViewById(R.id.editTextTextPassword2);
        newpass=(EditText)  findViewById(R.id.editTextTextPassword3);
        confirm=(EditText)  findViewById(R.id.editTextTextPassword4);
        save=(Button) findViewById(R.id.button4);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String old_pass=oldpass.getText().toString();
        final String new_pass=newpass.getText().toString();
        final String cnf_pass=confirm.getText().toString();
        if (old_pass.length()==0) {
            oldpass.setError("Missing");
        }
        else if (new_pass.length()==0) {
            newpass.setError("Missing");
        }
        else if (cnf_pass.length()==0) {
            confirm.setError("Missing");
        }
        else if (!cnf_pass.equalsIgnoreCase(new_pass))
        {
            confirm.setError("password does not match");
        }
        else
        {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/and_changepass_post";



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

                                    Toast.makeText(getApplicationContext(), "changed successfully", Toast.LENGTH_LONG).show();
                                    Intent i =new Intent(getApplicationContext(),login.class);
                                    startActivity(i);

                                }
                                else
                                {
                                    oldpass.setError("password errrrrr");
                                }

                                // }


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


                    params.put("lid",sh.getString("lid",""));
                    params.put("p1",old_pass);
                    params.put("np",new_pass);
                    params.put("cp",cnf_pass);


                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);

        }
    }
}
