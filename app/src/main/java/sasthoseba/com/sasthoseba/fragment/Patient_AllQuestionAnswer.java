package sasthoseba.com.sasthoseba.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sasthoseba.com.sasthoseba.Common_Question_Details;
import sasthoseba.com.sasthoseba.R;

public class Patient_AllQuestionAnswer extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList, QuestionArrayList, AnswerArrayList, TypeArrayList, DateArrayList;
    public static final String GET_URL = "http://ehealth.mdtauhidul.me/getData_QuestionAnswer.php";

    String [] QuestionTypeList= {"মেডিসিন", "মানসিক সাস্থ্য" , "শিশুরোগ", "গর্ভাবস্থা" , "অর্থোপেডিক" , "দন্তচিকিত্সা", "অন্যান্য"};
    RequestQueue requestQueue;
    Button filterSearch;
    String QuestionType, QuestionType2;
    String Send_Url = "http://ehealth.mdtauhidul.me/qtype.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View v = inflater.inflate(R.layout.patient_fragment_all_question_answer, null);
        filterSearch = (Button)v.findViewById(R.id.filterSearch);
        listView = v.findViewById(R.id.quesAnsList);

        arrayList = new ArrayList<>();
        QuestionArrayList = new ArrayList<>();
        AnswerArrayList = new ArrayList<>();
        TypeArrayList = new ArrayList<>();
        DateArrayList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.common_homepage_listview_color, arrayList);
        listView.setAdapter(adapter);


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int length = response.length();
                for (int i = 0; i < length; i++) {
                    try {
                        String Id = response.getJSONObject(i).getString("Id");
                        String QuestionType = response.getJSONObject(i).getString("QuestionType");
                        String QuestionDescription = response.getJSONObject(i).getString("QuestionDescription");
                        String Answer = response.getJSONObject(i).getString("Answer");
                        String Date = response.getJSONObject(i).getString("Date");
                        String Time = response.getJSONObject(i).getString("Time");
                        String PatientId = response.getJSONObject(i).getString("PatientId");
                        arrayList.add("প্রশ্ন: " + QuestionDescription + "\n" + "উত্তর: " + Answer);

                        QuestionArrayList.add(QuestionDescription);
                        AnswerArrayList.add(Answer);
                        TypeArrayList.add(QuestionType);
                        DateArrayList.add(Date);

                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
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


        filterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetValueFromEditText();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                //Toast.makeText(getActivity(), "আপনার প্রশ্ন পোস্ট করা হয়েছে", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                //Toast.makeText(getActivity(), "তথ্য সন্নিবেশ করা হয়নি", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("QuestionType", QuestionType);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);
            }


        } );


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), Common_Question_Details.class);

                String QHolder=QuestionArrayList.get(position);
                i.putExtra("Question", QHolder);

                String AHolder=AnswerArrayList.get(position);
                i.putExtra("Answer", AHolder);

                String THolder=TypeArrayList.get(position);
                i.putExtra("Type", THolder);

                String DHolder=DateArrayList.get(position);
                i.putExtra("Date", DHolder);

                startActivity(i);
            }
        });



        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,QuestionTypeList);
        MaterialBetterSpinner betterSpinner= (MaterialBetterSpinner)v.findViewById(R.id.androidspinner);
        betterSpinner.setAdapter(arrayAdapter);
        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                QuestionType2 = adapterView.getItemAtPosition(position).toString();
                int mSelectedId = position;
            }
        });

        return v;
    }



























    private void GetValueFromEditText() {
        QuestionType= QuestionType2;
    }
}





