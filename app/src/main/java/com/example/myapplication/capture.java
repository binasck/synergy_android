//package com.example.myapplication;
//
////import androidx.annotation.Nullable;
//
//import android.app.usage.UsageEvents;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.MediaScannerConnection;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.StrictMode;
//import android.preference.PreferenceManager;
//import android.provider.MediaStore;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.android.gms.tasks.Task;
////import com.google.mlkit.vision.common.InputImage;
////import com.google.mlkit.vision.text.Text;
////import com.google.mlkit.vision.text.TextRecognition;
////import com.google.mlkit.vision.text.TextRecognizer;
////import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
//
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class capture extends AppCompatActivity implements View.OnClickListener {
//    Button btn;
//    File s;
//    ImageView img;
//
//    String mCurrentPhotoPath="";
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//    File photoFile = null;
//
//    Button bys;
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
////        Intent ij=new Intent(getApplicationContext(),Staff_view_students.class);
////        startActivity(ij);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_capture_qr);
//
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//
//        btn=(Button) findViewById(R.id.button3);
//        bys=(Button) findViewById(R.id.button14);
//
//        bys.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//showfilechooser(2);
//
//            }
//        });
//
//
//        btn.setOnClickListener(this);
//        img=(ImageView) findViewById(R.id.imageView2);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//                    // Create the File where the photo should go
//
//                    try {
//                        photoFile = createImageFile();
//                    } catch (IOException ex) {
//                        // Error occurred while creating the File
//                        Log.i("A", "IOException");
//                    }
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                        startActivityForResult(cameraIntent, 101);
//                    }
//                }
//
//
//            }
//        });
//
//    }
//    Bitmap mImageBitmap;
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 101) {
//
//            try {
//
//
////                Toast.makeText(getApplicationContext(), photoFile.length()+"", Toast.LENGTH_SHORT).show();
//
//                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
//                img.setImageBitmap(mImageBitmap);
//
//
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
////            String[] projection = { MediaStore.Images.Media.DATA};
////            Bitmap bitmap = BitmapFactory.decodeFile(s.getAbsolutePath());
//////            Cursor cursor = managedQuery(Uri.fromFile(s), projection, null, null, null);
//////            int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//////            cursor.moveToFirst();
//////            String capturedImageFilePath = cursor.getString(column_index_data);
//////            Bitmap photo = (Bitmap) data.getExtras().get("data");
////            img.setImageBitmap(bitmap);
////
////
////            Toast.makeText(getApplicationContext(),s.getAbsolutePath(), Toast.LENGTH_SHORT).show();
////
//
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
//                        mImageBitmap=myBitmap;
//                        img.setImageBitmap(myBitmap);
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
//
//
//}
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
//    @Override
//    public void onClick(View v) {
//
//        InputImage image = InputImage.fromBitmap(mImageBitmap, 0);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//        byte[] imageBytes = baos.toByteArray();
//
//        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
//
//
//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String ip = sh.getString("ip", "");
//        String url = "http://" + ip + ":5000/and_add_mark";
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            String sucs = jsonObj.getString("status");
//                            if (sucs.equalsIgnoreCase("ok")) {
//                                Toast.makeText(getApplicationContext(), "Success...", Toast.LENGTH_SHORT).show();
//
////                                Intent ij=new Intent(getApplicationContext(),Staff_view_students.class);
////                                startActivity(ij);
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Failed...", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(getApplicationContext(), "eeeee" + e.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Map<String, String> params = new HashMap<>();
//
//                params.put("p",base64String);
//                params.put("lid", sh.getString("slid", ""));
//                params.put("examid",sh.getString("examid",""));
//
//
//                return params;
//            }
//        };
//        int MY_SOCKET_TIMEOUT_MS = 100000;
//
//        postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(postRequest);
//
//
//    }
//}
//
