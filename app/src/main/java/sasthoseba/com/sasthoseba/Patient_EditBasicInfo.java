package sasthoseba.com.sasthoseba;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import java.util.HashMap;
        import java.util.Map;

public class Patient_EditBasicInfo extends AppCompatActivity {

    EditText eTFeet, eTInch, eTWeight, eTBloodgroup;
    String WeightHolder, BloodGroupHolder, FeetHolder, InchHolder;
    String HeightHolder;
    String Holder;
    SharedPreferences sharedPreferences ;
    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_PatientBasicPhone.php";
    public static final String UPDATE_URL= "http://ehealth.mdtauhidul.me/insertData_PatientEditBasicInfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity_edit_basic_info);
        eTFeet = findViewById(R.id.feet);
        eTInch = findViewById(R.id.inch);
        eTWeight=findViewById(R.id.weight);
        eTBloodgroup = findViewById(R.id.bloodgroup);

    }

    public void donemethod(View v) {

        Intent intent = null;
        boolean error=false;
        FeetHolder= eTFeet.getText().toString().trim();
        if (FeetHolder.isEmpty()){
            eTFeet.setError("উচ্চতা পূরণ করুন");
            error=true;
        }
        else  if  (FeetHolder.startsWith("3") || FeetHolder.startsWith("4") || FeetHolder.startsWith("5") || FeetHolder.startsWith("6") || FeetHolder.startsWith("7") || FeetHolder.startsWith("8") ||  FeetHolder.startsWith("9") || FeetHolder.startsWith("2") || FeetHolder.startsWith("1")) {
        }

        else {
            eTFeet.setError("উচ্চতা সঠিক নয়");
            error=true;
        }

        InchHolder= eTInch.getText().toString().trim();
        if (InchHolder.isEmpty()){
            eTInch.setError("উচ্চতা পূরণ করুন");
            error=true;
        }
        else if ( (InchHolder.equals("0")) ||   (InchHolder.equals("1")) || (InchHolder.equals("2")) || (InchHolder.equals("3")) || (InchHolder.equals("4")) || (InchHolder.equals("5")) || (InchHolder.equals("6")) || (InchHolder.equals("7")) || (InchHolder.equals("8")) || (InchHolder.equals("9")) || (InchHolder.equals("10")) || (InchHolder.equals("11")))
        {
        }
        else {
            eTInch.setError("উচ্চতা সঠিক নয়");
            error=true;
        }

        HeightHolder = FeetHolder+" Feet"+" "+InchHolder+" Inch";

        WeightHolder= eTWeight.getText().toString().trim();
        if (WeightHolder.isEmpty()){
            eTWeight.setError("ওজন পূরণ করুন");
            error=true;
        }
        else if (WeightHolder.length()>3){
            eTWeight.setError("ওজন সঠিক নয়");
            error=true;
        }
        else {
            Float i = Float.parseFloat(WeightHolder);
            if (i < 300.00 && i > 0.00) {

            } else {
                eTWeight.setError("ওজন 300 কিলোগ্রামের চেয়ে বেশি হতে পারে না");
                error = true;
            }
        }

        BloodGroupHolder= eTBloodgroup.getText().toString().trim();

        if (BloodGroupHolder.isEmpty()){
            eTBloodgroup.setError("রক্তের গ্রুপ অনুপস্থিত");
            error=true;
        }

        else if ((BloodGroupHolder.equals("A+")) ||(BloodGroupHolder.equals("A-")) || (BloodGroupHolder.equals("O+")) || (BloodGroupHolder.equals("O-")) || (BloodGroupHolder.equals("B+")) || (BloodGroupHolder.equals("B-")) || (BloodGroupHolder.equals("AB+")) || (BloodGroupHolder.equals("AB-")) || (BloodGroupHolder.equals("a+")) || (BloodGroupHolder.equals("a-")) || (BloodGroupHolder.equals("b+")) ||
                (BloodGroupHolder.equals("b-")) || (BloodGroupHolder.equals("ab+")) || (BloodGroupHolder.equals("ab-"))     )
        {
        }
        else {
            eTBloodgroup.setError("রক্তের গ্রুপ সঠিক নয়");
            error=true;
        }
        if (error){
            Toast.makeText(Patient_EditBasicInfo.this , "ডেটা সঠিক নয়" , Toast.LENGTH_LONG).show();
        }
        else {
            sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
            Holder = sharedPreferences.getString("Phone", null).toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String ServerResponse) {
                            if(ServerResponse.equals("Done")) {

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_URL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String ServerResponse) {

                                                if(ServerResponse.equals("Updated")) {
                                                    Toast.makeText(getBaseContext(), "প্রাথমিক তথ্য সম্পাদন করা হয়েছে", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(getBaseContext(), Patient_TablayoutProfile.class);
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    Toast.makeText(getBaseContext(),"অনুগ্রহ করে আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(getBaseContext(), Patient_TablayoutProfile.class);
                                                    startActivity(intent);
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError volleyError) {

                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("BloodGroup", BloodGroupHolder);
                                        params.put("Weight", WeightHolder);
                                        params.put("Height", HeightHolder);
                                        return params;
                                    }
                                };
                                RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
                                requestQueue.add(stringRequest);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
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
        }
    }
}
