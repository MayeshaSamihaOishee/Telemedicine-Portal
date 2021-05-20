package sasthoseba.com.sasthoseba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Doctor_profile extends Activity {

    Button GetImageFromGalleryButton, UploadImageOnServerButton;
    ImageView ShowSelectedImage;
    //EditText GetImageName;
    Bitmap FixBitmap;
    //String ImageTag = "ImageTag" ;
    String image_data = "image_data" ;
    String ServerUploadPath ="http://ehealth.mdtauhidul.me/UploadImage_Doctor.php" ;
    ProgressDialog progressDialog ;
    ByteArrayOutputStream byteArrayOutputStream ;
    byte[] byteArray ;
    String ConvertImage ;
    HttpURLConnection httpURLConnection ;
    URL url;
    OutputStream outputStream;
    BufferedWriter bufferedWriter ;
    int RC ;
    BufferedReader bufferedReader ;
    StringBuilder stringBuilder;
    boolean check = true;

    String Holder;
    SharedPreferences sharedPreferences ;

    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_DoctorPhonenum.php";
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_Specific_Doctor.php";
    public static final String POST_URL_DocImgPhn= "http://ehealth.mdtauhidul.me/updateData_DoctorImagePhone.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity_profile);

        sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();

        GetImageFromGalleryButton = (Button)findViewById(R.id.selectImage);
        UploadImageOnServerButton = (Button)findViewById(R.id.uploadImage);
        ShowSelectedImage = (ImageView)findViewById(R.id.doctorImage);
        //GetImageName = (EditText)findViewById(R.id.editText);
        byteArrayOutputStream = new ByteArrayOutputStream();
        GetImageFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "গ্যালারি থেকে চিত্র নির্বাচন করুন"), 1);
            }
        });
        UploadImageOnServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest NewStringRequest = new StringRequest(Request.Method.POST, POST_URL_DocImgPhn,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                if(ServerResponse.equals("Done")) {
                                    UploadImageToServer();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                //Toast.makeText(getContext(), "ডেটা সঠিকভাবে ঢোকানো হয়নি", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("PhoneNum", Holder);
                        return params;
                    }
                };
                RequestQueue NewRequestQueue = Volley.newRequestQueue(getBaseContext());
                NewRequestQueue.add(NewStringRequest);
            }
        });

        final TextView T_FullName = (TextView) findViewById(R.id.doctorName);
        final TextView T_SpeciaListSmall = (TextView) findViewById(R.id.specialistSmall);
        final TextView T_Qualification = (TextView) findViewById(R.id.qualificationDetails);
        final TextView T_Schedule = (TextView) findViewById(R.id.scheduleDetails);
        final TextView T_SpecialField = (TextView) findViewById(R.id.specialfieldDetails);
        final TextView T_RegistrationNo = (TextView) findViewById(R.id.registrationNumberDetails);
        //final TextView T_image_data = (TextView) findViewById(R.id.doctorImage); //cannot be cast image view to textview

        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("Done")) {

                            RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    int length=response.length();
                                    for (int i=0; i<length; i++){
                                        try {
                                            String FullName=response.getJSONObject(i).getString("FullName");
                                            String Phone=response.getJSONObject(i).getString("Phone");
                                            String Qualification=response.getJSONObject(i).getString("Qualification");
                                            String Schedule=response.getJSONObject(i).getString("Schedule");
                                            String RegistrationNo=response.getJSONObject(i).getString("RegistrationNo");
                                            String SpecialField=response.getJSONObject(i).getString("SpecialField");
                                            String image_data=response.getJSONObject(i).getString("image_data");
                                            String Status=response.getJSONObject(i).getString("Status");

                                            T_FullName.setText(FullName);
                                            T_SpeciaListSmall.setText(SpecialField);
                                            T_Qualification.setText(Qualification);
                                            T_Schedule.setText(Schedule);
                                            T_SpecialField.setText(SpecialField);
                                            T_RegistrationNo.setText(RegistrationNo);
                                            //T_image_data.setText(image_data);
                                        }
                                        catch (JSONException e) {
                                            Log.e("Error", e.toString());
                                        }
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("Error", error.toString());
                                }
                            });
                            queue.add(jsonArrayRequest);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Toast.makeText(getContext(), "ডেটা সঠিকভাবে ঢোকানো হয়নি", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("PhoneNum", Holder);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(), "পরবর্তী সংস্করণে সক্রিয় করা হবে। অনুগ্রহপূর্বক অপেক্ষা করুন", Toast.LENGTH_LONG).show();
            }
        };
        Button btn =(Button)findViewById(R.id.editprofile);
        btn.setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {
        super.onActivityResult(RC, RQC, I);
        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {
            Uri uri = I.getData();
            try {
                FixBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ShowSelectedImage.setImageBitmap(FixBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void UploadImageToServer(){
        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteArray = byteArrayOutputStream.toByteArray();
        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(Doctor_profile.this,"ছবি আপলোড করা হচ্ছে ","অনুগ্রহপূর্বক অপেক্ষা করুন",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                progressDialog.dismiss();
                Toast.makeText(Doctor_profile.this,"ছবি সফলভাবে আপলোড করা হয়েছে",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();
                HashMap<String,String> HashMapParams = new HashMap<String,String>();
                //HashMapParams.put(ImageTag, GetPhonenumFromSharedPref);
                HashMapParams.put(image_data, ConvertImage);
                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);
                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{
        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                url = new URL(requestURL);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(200000);
                httpURLConnection.setConnectTimeout(200000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                outputStream = httpURLConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(bufferedWriterDataFN(PData));
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                RC = httpURLConnection.getResponseCode();
                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String RC2;
                    while ((RC2 = bufferedReader.readLine()) != null){
                        stringBuilder.append(RC2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {
            stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }
            return stringBuilder.toString();
        }
    }
}
















