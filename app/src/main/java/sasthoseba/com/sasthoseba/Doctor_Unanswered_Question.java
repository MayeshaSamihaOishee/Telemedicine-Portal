package sasthoseba.com.sasthoseba;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

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

public class Doctor_Unanswered_Question extends Fragment  {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList, QuestionArrayList, AnswerArrayList, TypeArrayList, DateArrayList, IdArrayList;

    String Holder;
    SharedPreferences sharedPreferences ;

    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_DoctorPhonenum.php";
    public static final String GET_URL = "http://ehealth.mdtauhidul.me/getData_UnansweredQuestion.php";

    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View v = inflater.inflate(R.layout.doctor_fragment_unanswered_question, null);
        listView = v.findViewById(R.id.quesAnsList);

        arrayList = new ArrayList<>();
        QuestionArrayList = new ArrayList<>();
        TypeArrayList = new ArrayList<>();
        DateArrayList = new ArrayList<>();
        IdArrayList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.common_homepage_listview_color, arrayList);
        listView.setAdapter(adapter);

        sharedPreferences =getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("Done")) {

                            RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                            arrayList.add("প্রশ্ন: " + QuestionDescription);

                                            QuestionArrayList.add(QuestionDescription);
                                            TypeArrayList.add(QuestionType);
                                            DateArrayList.add(Date);
                                            IdArrayList.add(Id);

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





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), Doctor_Give_Answer.class);

                String QHolder=QuestionArrayList.get(position);
                i.putExtra("Question", QHolder);

                String THolder=TypeArrayList.get(position);
                i.putExtra("Type", THolder);

                String DHolder=DateArrayList.get(position);
                i.putExtra("Date", DHolder);

                String IHolder=IdArrayList.get(position);
                i.putExtra("Id", IHolder);

                startActivity(i);
            }
        });
        return v;
    }
}





