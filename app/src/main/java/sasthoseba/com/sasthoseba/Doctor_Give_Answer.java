package sasthoseba.com.sasthoseba;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.util.HashMap;
        import java.util.Map;

        import sasthoseba.com.sasthoseba.fragment.Doctor_NavigationDrawer;

public class Doctor_Give_Answer extends Activity {

    TextView Question, Type, Date;
    EditText EtAnswer;
    Button SendAnswer;
    RequestQueue requestQueue;

    String QuestionHolder,TypeHolder, DateHolder, IdHolder,AnswerHolder;
    String Post_ID = "http://ehealth.mdtauhidul.me/updateData_WhichDoctor_Answered.php";
    String Post_Data = "http://ehealth.mdtauhidul.me/insertData_Doctor_Answered.php";

    String Holder;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity_give_answer);

        Question=(TextView) findViewById(R.id.questionDetails);
        Type=(TextView) findViewById(R.id.questionTypeDetails);
        Date=(TextView) findViewById(R.id.date);

        QuestionHolder = getIntent().getStringExtra("Question");
        TypeHolder = getIntent().getStringExtra("Type");
        DateHolder = getIntent().getStringExtra("Date");
        IdHolder = getIntent().getStringExtra("Id");

        Question.setText(QuestionHolder);
        Type.setText(TypeHolder);
        Date.setText(DateHolder);

        EtAnswer=(EditText) findViewById(R.id.Answer);
        SendAnswer = (Button)findViewById(R.id.sendAnswer);
        requestQueue = Volley.newRequestQueue(getBaseContext());

        sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();













        SendAnswer.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = null;
                                                boolean error=false;

                                                String question = EtAnswer.getText().toString();
                                                if (question.isEmpty()) {
                                                    EtAnswer.setError("আপনার প্রশ্নটি করুন");
                                                    error = true;
                                                }
                                                if (error==true) {
                                                    Toast.makeText(getBaseContext(), "\n" +
                                                            "আপনার তথ্য সঠিক নয়", Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    GetValueFromEditText();
                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Post_ID,
                                                            new Response.Listener<String>() {
                                                                @Override
                                                                public void onResponse(String ServerResponse) {
                                                                    if(ServerResponse.equals("Done")){

                                                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Post_Data,
                                                                                new Response.Listener<String>() {
                                                                                    @Override
                                                                                    public void onResponse(String ServerResponse) {
                                                                                        if(ServerResponse.equals("Done")){
                                                                                            Toast.makeText(getBaseContext(), "আপনার উত্তরটি পোস্ট করা হয়েছে", Toast.LENGTH_LONG).show();
                                                                                            Intent intent = null;
                                                                                            intent =  new Intent(getBaseContext(), Doctor_NavigationDrawer.class);
                                                                                            startActivity(intent);
                                                                                        }
                                                                                        else{
                                                                                            Toast.makeText(getBaseContext(), "আপনার উত্তরটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                    }
                                                                                },
                                                                                new Response.ErrorListener() {
                                                                                    @Override
                                                                                    public void onErrorResponse(VolleyError volleyError) {
                                                                                        Toast.makeText(getBaseContext(), "আপনার উত্তরটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }) {
                                                                            @Override
                                                                            protected Map<String, String> getParams() {
                                                                                Map<String, String> params = new HashMap<String, String>();
                                                                                params.put("Answer", AnswerHolder);
                                                                                params.put("QuestionID", IdHolder);
                                                                                return params;
                                                                            }

                                                                        };
                                                                        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
                                                                        requestQueue.add(stringRequest);
                                                                    }
                                                                    else{
                                                                        Toast.makeText(getBaseContext(), "আপনার প্রশ্নটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError volleyError) {
                                                                    Toast.makeText(getBaseContext(), "আপনার প্রশ্নটি পোস্ট করা হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
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
        );


    }

    private void GetValueFromEditText() {
        AnswerHolder= EtAnswer.getText().toString().trim();
    }

}
