package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity implements View.OnClickListener {
EditText name,place,post,district,email,phone,qualification;
RadioGroup gender;
RadioButton male,female;
Button edit;
ImageView img;
String gende="",path,atype,attach="",fname;
byte[] byteArray=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name=(EditText) findViewById(R.id.editTextTextPersonName21);
        place=(EditText) findViewById(R.id.editTextTextPersonName41);
        post=(EditText) findViewById(R.id.editTextTextPersonName51);
        district=(EditText) findViewById(R.id.editTextTextPersonName61);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress7);
        phone=(EditText) findViewById(R.id.editTextPhone8);
        qualification=(EditText) findViewById(R.id.editTextTextPersonName81);
        gender=(RadioGroup) findViewById(R.id.R1111);
        img=(ImageView) findViewById(R.id.imageView1);
        edit=(Button) findViewById(R.id.button31);
        male=(RadioButton) findViewById(R.id.radiobutton5);
        female=(RadioButton) findViewById(R.id.radioButton6);
        edit.setOnClickListener(this);
        img.setOnClickListener(this);


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
                                place.setText(jj.getString("place"));
                                post.setText(jj.getString("post"));
                                district.setText(jj.getString("district"));
                                email.setText(jj.getString("email"));
                                phone.setText(jj.getString("phone"));
                                qualification.setText(jj.getString("qualification"));

                                if (jj.getString("gender").equalsIgnoreCase("Male"))
                                {
                                    male.setChecked(true);

                                }
                                else if (jj.getString("gender").equalsIgnoreCase("Female"))
                                {
                                    female.setChecked(true);
                                }
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
       if (view==edit){
            final String nam = name.getText().toString();
            final String plac = place.getText().toString();
            final String pos = post.getText().toString();
            final String dist = district.getText().toString();
            final String mail = email.getText().toString();
            final String phon = phone.getText().toString();
            final String qualfi = qualification.getText().toString();

            if (male.isChecked()) {
                gende = "Male";
            } else if (female.isChecked()) {
                gende = "Female";
            }

            if (nam.length() == 0) {
                name.setError("Missing");
            } else if (plac.length() == 0) {
                place.setError("Missing");
            } else if (pos.length() == 0) {
                post.setError("Missing");
            } else if (dist.length() == 0) {
                district.setError("Missing");
            } else if (mail.length() == 0) {
                email.setError("Missing");
            } else if (phon.length() == 0) {
                phone.setError("Missing");
            } else if (qualfi.length() == 0) {
                qualification.setError("Missing");
            } else {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_editprofile_post";


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
                                        Intent I=new Intent(getApplicationContext(),view_profile.class);
                                        startActivity(I);
                                    }


                                    // }
                                    else {
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
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("name", nam);
                        params.put("place", plac);
                        params.put("post", pos);
                        params.put("district", dist);
                        params.put("email", mail);
                        params.put("phone", phon);
                        params.put("qualification", qualfi);
                        params.put("photo", attach);
                        params.put("gender", gende);
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
        }
       else {
           showfilechooser(1);
       }
    }
    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        img.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

    }
}