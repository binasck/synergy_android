package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_profile extends AppCompatActivity implements View.OnClickListener {
Button edit;
TextView name,place,post,district,email,phone,qualification,gender;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        edit=(Button) findViewById(R.id.button5);
        name=(TextView) findViewById(R.id.textView6);
        gender=(TextView)findViewById(R.id.textView14);
        place=(TextView) findViewById(R.id.textView15);
        post=(TextView) findViewById(R.id.textView16);
        district=(TextView) findViewById(R.id.textView17);
        email=(TextView) findViewById(R.id.textView18);
        phone=(TextView) findViewById(R.id.textView19);
        qualification=(TextView) findViewById(R.id.textView20);
        img=(ImageView) findViewById(R.id.imageView2);
        edit.setOnClickListener(this);

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("ip", "");
            String url = "http://" + ip + ":5000/and_viewprofile_post";
            String lid = sh.getString("lid", "");
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                    JSONObject jj = jsonObj.getJSONObject("data");


                                    name.setText(jj.getString("name"));
                                    gender.setText(jj.getString("gender"));
                                    place.setText(jj.getString("place"));
                                    post.setText(jj.getString("post"));
                                    district.setText(jj.getString("district"));
                                    email.setText(jj.getString("email"));
                                    phone.setText(jj.getString("phone"));
                                    qualification.setText(jj.getString("qualification"));

                                    String image = jj.getString("photo");
                                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    String ip = sh.getString("ip", "");
                                    String url = "http://" + ip + ":5000" + image;
                                    Picasso.with(getApplicationContext()).load(url).into(img);//circle

                                } else {
                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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

                //                value Passing android to python
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("lid", sh.getString("lid",""));//passing to python

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

    @Override
    public void onClick(View view) {
        Intent I=new Intent(getApplicationContext(),edit_profile.class);
        startActivity(I);
    }
}





