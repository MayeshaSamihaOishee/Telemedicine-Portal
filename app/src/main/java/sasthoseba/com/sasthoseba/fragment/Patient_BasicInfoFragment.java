package sasthoseba.com.sasthoseba.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sasthoseba.com.sasthoseba.Patient_EditBasicInfo;
import sasthoseba.com.sasthoseba.Patient_NavigationDrawer;
import sasthoseba.com.sasthoseba.R;

public class Patient_BasicInfoFragment extends Fragment {
    //ListView listView;
    //ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    String Holder;
    SharedPreferences sharedPreferences ;

    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_PatientPhonenum.php";
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_Specific_Patient.php";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View v = inflater.inflate(R.layout.patient_fragment_basic_info, null);

        sharedPreferences =getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();
        arrayList=new ArrayList<>();
        //listView=v.findViewById(R.id.list_View);
        //adapter=new ArrayAdapter<String>(getContext(), R.layout.common_homepage_listview_color, arrayList);
       // listView.setAdapter(adapter);

        final TextView T_FullName = (TextView) v.findViewById(R.id.fullnameDetails);
        final TextView T_Birthdate = (TextView) v.findViewById(R.id.birthdateDetails);
        final TextView T_Gender = (TextView) v.findViewById(R.id.genderDetails);
        final TextView T_Mobile = (TextView) v.findViewById(R.id.mobileDetails);
        final TextView T_Email = (TextView) v.findViewById(R.id.emailDetails);
        final TextView T_Height = (TextView) v.findViewById(R.id.heightDetails);
        final TextView T_Weight = (TextView) v.findViewById(R.id.weightDetails);
        final TextView T_Bloodgroup = (TextView) v.findViewById(R.id.bloodgroupDetails);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("Done")) {

                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    int length=response.length();
                                    for (int i=0; i<length; i++){
                                        try {
                                            String Id=response.getJSONObject(i).getString("Id");
                                            String FullName=response.getJSONObject(i).getString("FullName");
                                            String DateOfBirth=response.getJSONObject(i).getString("DateOfBirth");
                                            String Gender=response.getJSONObject(i).getString("Gender");
                                            String Phone=response.getJSONObject(i).getString("Phone");
                                            String Email=response.getJSONObject(i).getString("Email");
                                            String Height=response.getJSONObject(i).getString("Height");
                                            String Weight=response.getJSONObject(i).getString("Weight");
                                            String BloodGroup=response.getJSONObject(i).getString("BloodGroup");

                                            T_FullName.setText(FullName);
                                            T_Birthdate.setText(DateOfBirth);
                                            T_Gender.setText(Gender);
                                            T_Mobile.setText(Phone);
                                            T_Email.setText(Email);
                                            T_Height.setText(Height);
                                            T_Weight.setText(Weight);
                                            T_Bloodgroup.setText(BloodGroup);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);







        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i= new Intent(getActivity(), Patient_EditBasicInfo.class);
                startActivity(i);
            }
        };
        Button btn =(Button) v.findViewById(R.id.editprofile);
        btn.setOnClickListener(listener);
        return v;
    }
}



