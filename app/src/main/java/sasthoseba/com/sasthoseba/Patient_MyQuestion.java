package sasthoseba.com.sasthoseba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Patient_MyQuestion extends Activity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    String Holder;
    SharedPreferences sharedPreferences ;

    public static final String GET_URL = "http://ehealth.mdtauhidul.me/getData_MyQuestion.php";
    public static final String Send_Url = "http://ehealth.mdtauhidul.me/update_temp_myQuestion.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity_my_question);

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.quesAnsList);
        adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.common_homepage_listview_color, arrayList);
        listView.setAdapter(adapter);

        sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Send_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("Done")){

                            RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    int length = response.length();
                                    for (int i = 0; i < length; i++) {
                                        try {
                                            String QuestionDescription = response.getJSONObject(i).getString("QuestionDescription");
                                            String Answer = response.getJSONObject(i).getString("Answer");

                                            arrayList.add("প্রশ্ন: " + QuestionDescription + "\n" + "\n" +"উত্তর: " + Answer);
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
