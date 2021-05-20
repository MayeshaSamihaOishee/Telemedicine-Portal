package sasthoseba.com.sasthoseba;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
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
        import java.util.HashMap;
        import java.util.Map;

public class Doctor_OnlineProfileDetails extends Activity {

    boolean check = true;
    String DoctorPhoneNumber;


    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_DoctorPhonenum.php";
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_Specific_Doctor.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity_online_profile_details);

        final TextView T_FullName = (TextView) findViewById(R.id.doctorName);
        final TextView T_SpeciaListSmall = (TextView) findViewById(R.id.specialistSmall);
        final TextView T_Qualification = (TextView) findViewById(R.id.qualificationDetails);
        final TextView T_Schedule = (TextView) findViewById(R.id.scheduleDetails);
        final TextView T_SpecialField = (TextView) findViewById(R.id.specialfieldDetails);
        final TextView T_RegistrationNo = (TextView) findViewById(R.id.registrationNumberDetails);

        DoctorPhoneNumber = getIntent().getStringExtra("DoctorPhone");

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
                params.put("PhoneNum", DoctorPhoneNumber);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);

        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Toast.makeText(getBaseContext(), "Edit feature will be enabled in next version. Please wait.", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getBaseContext(), VideoCalltoDoc.class);
                startActivity(i);
            }
        };
        Button btn =(Button)findViewById(R.id.editprofile);
        btn.setOnClickListener(listener);
    }
}

















