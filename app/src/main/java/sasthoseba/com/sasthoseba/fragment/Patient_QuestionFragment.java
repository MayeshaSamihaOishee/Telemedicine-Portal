package sasthoseba.com.sasthoseba.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.HashMap;
import java.util.Map;

import sasthoseba.com.sasthoseba.Doctor_profile;
import sasthoseba.com.sasthoseba.Patient_MyQuestion;
import sasthoseba.com.sasthoseba.R;

public class Patient_QuestionFragment extends Fragment {
    EditText eTQuestion;
    String [] QuestionTypeList= {"মেডিসিন", "মানসিক সাস্থ্য" , "শিশুরোগ", "গর্ভাবস্থা" , "অর্থোপেডিক" , "দন্তচিকিত্সা", "অন্যান্য"};
    Button SendQuestion;
    RequestQueue requestQueue;
    String QuestionHolder, QuestionType, QuestionType2;
    String HttpUrl = "http://ehealth.mdtauhidul.me/insertData_Question.php";

    String Holder;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View v= inflater.inflate(R.layout.patient_fragment_question, null);

        eTQuestion = v.findViewById(R.id.whatisonyourmind);
        SendQuestion = (Button)v.findViewById(R.id.sendquestion);
        requestQueue = Volley.newRequestQueue(getContext());

        sharedPreferences =getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();

        SendQuestion.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = null;
                                                boolean error=false;

                                                String question = eTQuestion.getText().toString();
                                                if (question.isEmpty()) {
                                                    eTQuestion.setError("আপনার প্রশ্নটি করুন");
                                                    error = true;
                                                }
                                                if (error==true) {
                                                    Toast.makeText(getContext(), "আপনার তথ্য সঠিক নয়", Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    GetValueFromEditText();
                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                                                            new Response.Listener<String>() {
                                                                @Override
                                                                public void onResponse(String ServerResponse) {

                                                                    if(ServerResponse.equals("Done")){
                                                                        Toast.makeText(getContext(), "আপনার প্রশ্নটি পোস্ট করা হয়েছে", Toast.LENGTH_LONG).show();
                                                                        Intent intent = null;
                                                                        intent =  new Intent(getContext(), Patient_MyQuestion.class);
                                                                        startActivity(intent);
                                                                    }
                                                                    else{
                                                                        Toast.makeText(getContext(), "আপনার প্রশ্নটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError volleyError) {
                                                                    Toast.makeText(getContext(), "আপনার প্রশ্নটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন। ", Toast.LENGTH_LONG).show();
                                                                }
                                                            }) {
                                                        @Override
                                                        protected Map<String, String> getParams() {
                                                            Map<String, String> params = new HashMap<String, String>();
                                                            params.put("QuestionDescription", QuestionHolder);
                                                            params.put("QuestionType", QuestionType);
                                                            params.put("PatientPhoneId", Holder);
                                                            return params;
                                                        }

                                                    };
                                                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                                    requestQueue.add(stringRequest);
                                                }
                                            }
                                        }
        );
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getContext(), R.layout.spinner_item,QuestionTypeList);
        MaterialBetterSpinner betterSpinner= (MaterialBetterSpinner)v.findViewById(R.id.androidspinner);
        betterSpinner.setAdapter(arrayAdapter);

        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                QuestionType2 = adapterView.getItemAtPosition(position).toString();
                int mSelectedId = position;
            }
        });





        Button btn =(Button) v.findViewById(R.id.sendquestion);
        return v;
    }
    private void GetValueFromEditText() {
        QuestionHolder= eTQuestion.getText().toString().trim();
        QuestionType= QuestionType2;
    }
}
