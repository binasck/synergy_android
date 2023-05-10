//package com.example.myapplication;
//
////import androidx.annotation.NonNull;
////import androidx.annotation.RequiresApi;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
////import com.google.android.gms.tasks.OnFailureListener;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.android.gms.tasks.Task;
////import com.google.mlkit.vision.common.InputImage;
////import com.google.mlkit.vision.text.Text;
////import com.google.mlkit.vision.text.TextRecognition;
////import com.google.mlkit.vision.text.TextRecognizer;
////import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
//
//import org.json.JSONObject;
//import org.w3c.dom.Text;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CApture_qr extends AppCompatActivity implements View.OnClickListener {
//    Button bt;
//    Button btchoose,bbts;
//
//
//    private ImageView imageView;
//    private static final int CAMERA_REQUEST = 1888;
//    private static final int MY_CAMERA_PERMISSION_CODE = 100;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_capture_qr);
//
//        bt=(Button)findViewById(R.id.button3);
//        bt.setOnClickListener(this);
//        btchoose=(Button)findViewById(R.id.button14);
//     //   bbts=(Button)findViewById(R.id.button13);
//
//        btchoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(),"AA",Toast.LENGTH_LONG).show();
//            }
//        });  bbts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(),"AA",Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        imageView=(ImageView) findViewById(R.id.imageView2);
//        imageView.setOnClickListener(this);
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_PERMISSION_CODE)
//        {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//            else
//            {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    Bitmap photo;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//
//            Toast.makeText(this, "Captured", Toast.LENGTH_SHORT).show();
//            try {
//
//                photo = (Bitmap) data.getExtras().get("data");
//                if(photo==null)
//                {
//                    Toast.makeText(this, "Image is Null", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(this, "Image is not Null", Toast.LENGTH_SHORT).show();
//                }
//                imageView.setImageBitmap(photo);
//
//
//
//
//
//
//                Toast.makeText(this,"pic received", Toast.LENGTH_SHORT).show();
//
//            }
//            catch (Exception ex)
//            {
//                Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
////
//        }
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 2) {
//                //
//
//
//                Uri uri = data.getData();
//
//                try {
//                    path = FileUtils.getPath(this, uri);
//
//                    File fil = new File(path);
//                    float fln = (float) (fil.length() / 1024);
//                    atype = path.substring(path.lastIndexOf(".") + 1);
//
//
//                    fname = path.substring(path.lastIndexOf("/") + 1);
//
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//
//                    File imgFile = new File(path);
//
//                    if (imgFile.exists()) {
//
//                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                        photo=myBitmap;
//                        imageView.setImageBitmap(myBitmap);
//
//                    }
//
//
//                    File file = new File(path);
//                    byte[] b = new byte[8192];
//                    Log.d("bytes read", "bytes read");
//
//                    InputStream inputStream = new FileInputStream(file);
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//                    int bytesRead = 0;
//
//                    while ((bytesRead = inputStream.read(b)) != -1) {
//                        bos.write(b, 0, bytesRead);
//                    }
//                    byteArray = bos.toByteArray();
//
//                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
//                    attach = str;
//
//
//                } catch (Exception e) {
//                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
//                }
//
//                ///
//
//            }
//        }
//    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onClick(View view) {
////        if(view==btchoose)
////        { Toast.makeText(getApplicationContext(),"ssss",Toast.LENGTH_LONG).show();
////            //showfilechooser(2);
////
////        }
////        else
//
//
//            if (view == imageView) {
//            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
//
//                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//            }
//            else
//            {
//                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        }
//
//
//        else {
//           // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.t);
//            InputImage image = InputImage.fromBitmap(photo, 0);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//            byte[] imageBytes = baos.toByteArray();
//
//            String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
//
//            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
//            Task<Text> result =
//                    recognizer.process(image)
//                            .addOnSuccessListener(new OnSuccessListener<Text>() {
//                                @Override
//                                public void onSuccess(Text visionText) {
//
//                                    Toast.makeText(getApplicationContext(), visionText.getText().toString(), Toast.LENGTH_SHORT).show();
//
//
//                                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                    String ip = sh.getString("ip", "");
//                                    String url = "http://" + ip + ":5000/and_add_mark";
//
//                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                                            new Response.Listener<String>() {
//                                                @Override
//                                                public void onResponse(String response) {
//                                                    try {
//                                                        JSONObject jsonObj = new JSONObject(response);
//                                                        String sucs = jsonObj.getString("status");
//                                                        if (sucs.equalsIgnoreCase("ok")) {
//                                                            Toast.makeText(getApplicationContext(), "Success...", Toast.LENGTH_SHORT).show();
//
////                                                            Intent ij=new Intent(getApplicationContext(),Staff_view_students.class);
////                                                            startActivity(ij);
//                                                        } else {
//                                                            Toast.makeText(getApplicationContext(), "Failed...", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    } catch (Exception e) {
//                                                        Toast.makeText(getApplicationContext(), "eeeee" + e.toString(), Toast.LENGTH_LONG).show();
//                                                    }
//                                                }
//                                            },
//                                            new Response.ErrorListener() {
//                                                @Override
//                                                public void onErrorResponse(VolleyError error) {
//                                                    // error
//                                                    Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                    ) {
//                                        @Override
//                                        protected Map<String, String> getParams() {
//                                            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                            Map<String, String> params = new HashMap<>();
//
//                                            params.put("p",base64String);
//                                            params.put("lid", sh.getString("slid", ""));
//                                            params.put("examid",sh.getString("examid",""));
//
//
//                                            return params;
//                                        }
//                                    };
//                                    int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                            MY_SOCKET_TIMEOUT_MS,
//                                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                                    requestQueue.add(postRequest);
//
//
//
//
//                                }
//                            })
//                            .addOnFailureListener(
//                                    new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            // Task failed with an exception
//                                            // ...
//                                        }
//                                    });
//        }
//
//    }
//    String path, atype, fname, attach="no";
//    byte[] byteArray = null;
//    void showfilechooser(int string) {
//        // TODO Auto-generated method stub
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        //getting all types of files
//
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//        try {
//            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
//        } catch (android.content.ActivityNotFoundException ex) {
//            // Potentially direct the user to the Market with a Dialog
//            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
//}