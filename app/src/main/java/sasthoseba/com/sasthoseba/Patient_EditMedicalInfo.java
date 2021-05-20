package sasthoseba.com.sasthoseba;
 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.CheckBox;
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

 import sasthoseba.com.sasthoseba.fragment.Patient_MedicalHistoryFragment;

public class Patient_EditMedicalInfo extends AppCompatActivity {

    Button Update;
    RequestQueue requestQueue;
    String S_one ="";
    String S_two ="";
    String S_three ="";
    String S_four ="";
    String S_five ="";
    String S_six ="";
    String S_seven ="";
    String S_eight ="";
    String S_nine ="";
    String S_ten ="";
    String S_eleven ="";
    String S_twelve ="";
    String S_thirteen ="";
    String S_fourteen ="";
    String S_fifteen ="";
    String S_sixteen ="";
    String S_seventeen ="";
    String S_eighteen ="";
    String S_nineteen ="";
    String S_twenty ="";
    String S_twentyone ="";
    String S_twentytwo ="";
    String S_twentythree ="";
    String S_twentyfour ="";
    String S_twentyfive ="";
    String S_twentysix ="";
    String S_twentyseven ="";
    String S_twentyeight ="";
    String S_twentynine ="";
    String S_thirty ="";
    String S_thirtyone ="";
    String S_thirtytwo ="";
    String S_thirtythree ="";
    String S_thirtyfour ="";
    String S_thirtyfive ="";
    String S_thirtysix ="";
    String S_thirtyseven ="";
    String S_thirtyeight ="";
    String S_thirtynine ="";
    String S_forty ="";


    CheckBox one, two, three, four, five, six, seven, eight, nine, ten,
            eleven, twelve, thirteen, fourteen, fifteen, sixteen,
            seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,
            twentythree,twentyfour,twentyfive,twentysix,twentyseven,twentyeight,
            twentynine,thirty,thirtyone,thirtytwo,thirtythree,thirtyfour,
            thirtyfive,thirtysix,thirtyseven,thirtyeight,thirtynine,forty;

    String post_patient_phone = "http://ehealth.mdtauhidul.me/updateData_PatientMedicalHistoryPhone.php";
    String post_checkbox_item = "http://ehealth.mdtauhidul.me/insertData_PatientMedicalHistory.php";

    String Holder;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity_edit_medical_info);

        Update = (Button)findViewById(R.id.update);
        requestQueue = Volley.newRequestQueue(getBaseContext());

        one = (CheckBox)findViewById(R.id.checkBox1);
        two = (CheckBox)findViewById(R.id.checkBox2);
        three = (CheckBox)findViewById(R.id.checkBox3);
        four = (CheckBox)findViewById(R.id.checkBox4);
        five = (CheckBox)findViewById(R.id.checkBox5);
        six = (CheckBox)findViewById(R.id.checkBox6);
        seven = (CheckBox)findViewById(R.id.checkBox7);
        eight = (CheckBox)findViewById(R.id.checkBox8);
        nine = (CheckBox)findViewById(R.id.checkBox9);
        ten = (CheckBox)findViewById(R.id.checkBox10);
        eleven = (CheckBox)findViewById(R.id.checkBox11);
        twelve = (CheckBox)findViewById(R.id.checkBox12);
        thirteen = (CheckBox)findViewById(R.id.checkBox13);
        fourteen = (CheckBox)findViewById(R.id.checkBox14);
        fifteen = (CheckBox)findViewById(R.id.checkBox15);
        sixteen = (CheckBox)findViewById(R.id.checkBox16);
        seventeen = (CheckBox)findViewById(R.id.checkBox17);
        eighteen = (CheckBox)findViewById(R.id.checkBox18);
        nineteen = (CheckBox)findViewById(R.id.checkBox19);
        twenty = (CheckBox)findViewById(R.id.checkBox20);

        twentyone = (CheckBox)findViewById(R.id.checkBox21);
        twentytwo = (CheckBox)findViewById(R.id.checkBox22);
        twentythree = (CheckBox)findViewById(R.id.checkBox23);
        twentyfour = (CheckBox)findViewById(R.id.checkBox24);
        twentyfive = (CheckBox)findViewById(R.id.checkBox25);
        twentysix = (CheckBox)findViewById(R.id.checkBox26);
        twentyseven = (CheckBox)findViewById(R.id.checkBox27);
        twentyeight = (CheckBox)findViewById(R.id.checkBox28);
        twentynine = (CheckBox)findViewById(R.id.checkBox29);
        thirty = (CheckBox)findViewById(R.id.checkBox30);
        thirtyone = (CheckBox)findViewById(R.id.checkBox31);
        thirtytwo = (CheckBox)findViewById(R.id.checkBox32);
        thirtythree = (CheckBox)findViewById(R.id.checkBox33);
        thirtyfour = (CheckBox)findViewById(R.id.checkBox34);
        thirtyfive = (CheckBox)findViewById(R.id.checkBox35);
        thirtysix = (CheckBox)findViewById(R.id.checkBox36);
        thirtyseven = (CheckBox)findViewById(R.id.checkBox37);
        thirtyeight = (CheckBox)findViewById(R.id.checkBox38);
        thirtynine = (CheckBox)findViewById(R.id.checkBox39);
        forty = (CheckBox)findViewById(R.id.checkBox40);


        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(one.isChecked())
                {
                    S_one=one.getText().toString();
                }
                else
                {
                    S_one="";
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(two.isChecked())
                {
                    S_two=two.getText().toString();
                }
                else
                {
                    S_two="";
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(three.isChecked())
                {
                    S_three=three.getText().toString();
                }
                else
                {
                    S_three="";
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(four.isChecked())
                {
                    S_four=four.getText().toString();
                }
                else
                {
                    S_four="";
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(five.isChecked())
                {
                    S_five=five.getText().toString();
                }
                else
                {
                    S_five="";
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(six.isChecked())
                {

                    S_six=six.getText().toString();
                }
                else
                {
                    S_six="";
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seven.isChecked())
                {
                    S_seven=seven.getText().toString();
                }
                else
                {
                    S_seven="";
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(eight.isChecked())
                {
                    S_eight=eight.getText().toString();
                }
                else
                {
                    S_eight="";
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(nine.isChecked())
                {
                    S_nine=nine.getText().toString();
                }
                else
                {
                    S_nine="";
                }
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ten.isChecked())
                {
                    S_ten=ten.getText().toString();
                }
                else
                {
                    S_ten="";
                }
            }
        });

        eleven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(eleven.isChecked())
                {
                    S_eleven=eleven.getText().toString();
                }
                else
                {
                    S_eleven="";
                }
            }
        });

        twelve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twelve.isChecked())
                {
                    S_twelve=twelve.getText().toString();
                }
                else
                {
                    S_twelve="";
                }
            }
        });

        thirteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirteen.isChecked())
                {
                    S_thirteen=thirteen.getText().toString();
                }
                else
                {
                    S_thirteen="";
                }
            }
        });

        fourteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(fourteen.isChecked())
                {
                    S_fourteen=fourteen.getText().toString();
                }
                else
                {
                    S_fourteen="";
                }
            }
        });

        fifteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(fifteen.isChecked())
                {
                    S_fifteen=fifteen.getText().toString();
                }
                else
                {
                    S_fifteen="";
                }
            }
        });

        sixteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sixteen.isChecked())
                {
                    S_sixteen=sixteen.getText().toString();
                }
                else
                {
                    S_sixteen="";
                }
            }
        });

        seventeen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seventeen.isChecked())
                {
                    S_seventeen=seventeen.getText().toString();
                }
                else
                {
                    S_seventeen="";
                }
            }
        });

        eighteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(eighteen.isChecked())
                {
                    S_eighteen=eighteen.getText().toString();
                }
                else
                {
                    S_eighteen="";
                }
            }
        });

        nineteen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(nineteen.isChecked())
                {
                    S_nineteen=nineteen.getText().toString();
                }
                else
                {
                    S_nineteen="";
                }
            }
        });

        twenty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twenty.isChecked())
                {
                    S_twenty=twenty.getText().toString();
                }
                else
                {
                    S_twenty="";
                }
            }
        });

        twentyone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentyone.isChecked())
                {
                    S_twentyone=twentyone.getText().toString();
                }
                else
                {
                    S_twentyone="";
                }
            }
        });

        twentytwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentytwo.isChecked())
                {
                    S_twentytwo=twentytwo.getText().toString();
                }
                else
                {
                    S_twentytwo="";
                }
            }
        });

        twentythree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentythree.isChecked())
                {
                    S_twentythree=twentythree.getText().toString();
                }
                else
                {
                    S_twentythree="";
                }
            }
        });

        twentyfour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentyfour.isChecked())
                {
                    S_twentyfour=twentyfour.getText().toString();
                }
                else
                {
                    S_twentyfour="";
                }
            }
        });

        twentyfive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentyfive.isChecked())
                {
                    S_twentyfive=twentyfive.getText().toString();
                }
                else
                {
                    S_twentyfive="";
                }
            }
        });

        twentysix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentysix.isChecked())
                {
                    S_twentysix=twentysix.getText().toString();
                }
                else
                {
                    S_twentysix="";
                }
            }
        });

        twentyseven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentyseven.isChecked())
                {
                    S_twentyseven=twentyseven.getText().toString();
                }
                else
                {
                    S_twentyseven="";
                }
            }
        });

        twentyeight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentyeight.isChecked())
                {
                    S_twentyeight=twentyeight.getText().toString();
                }
                else
                {
                    S_twentyeight="";
                }
            }
        });

        twentynine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(twentynine.isChecked())
                {
                    S_twentynine=twentynine.getText().toString();
                }
                else
                {
                    S_twentynine="";
                }
            }
        });

        thirty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirty.isChecked())
                {
                    S_thirty=thirty.getText().toString();
                }
                else
                {
                    S_thirty="";
                }
            }
        });

        thirtyone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtyone.isChecked())
                {
                    S_thirtyone=thirtyone.getText().toString();
                }
                else
                {
                    S_thirtyone="";
                }
            }
        });

        thirtytwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtytwo.isChecked())
                {
                    S_thirtytwo=thirtytwo.getText().toString();
                }
                else
                {
                    S_thirtytwo="";
                }
            }
        });

        thirtythree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtythree.isChecked())
                {
                    S_thirtythree=thirtythree.getText().toString();
                }
                else
                {
                    S_thirtythree="";
                }
            }
        });

        thirtyfour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtyfour.isChecked())
                {
                    S_thirtyfour=thirtyfour.getText().toString();
                }
                else
                {
                    S_thirtyfour="";
                }
            }
        });

        thirtyfive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtyfive.isChecked())
                {
                    S_thirtyfive=thirtyfive.getText().toString();
                }
                else
                {
                    S_thirtyfive="";
                }
            }
        });

        thirtysix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtysix.isChecked())
                {
                    S_thirtysix=thirtysix.getText().toString();
                }
                else
                {
                    S_thirtysix="";
                }
            }
        });

        thirtyseven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtyseven.isChecked())
                {
                    S_thirtyseven=thirtyseven.getText().toString();
                }
                else
                {
                    S_thirtyseven="";
                }
            }
        });

        thirtyeight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtyeight.isChecked())
                {
                    S_thirtyeight=thirtyeight.getText().toString();
                }
                else
                {
                    S_thirtyeight="";
                }
            }
        });

        thirtynine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(thirtynine.isChecked())
                {
                    S_thirtynine=thirtynine.getText().toString();
                }
                else
                {
                    S_thirtynine="";
                }
            }
        });

        forty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(forty.isChecked())
                {
                    S_forty=forty.getText().toString();
                }
                else
                {
                    S_forty="";
                }
            }
        });







        sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();


        Update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = null;
                                                final boolean error=false;

                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, post_patient_phone,
                                                            new Response.Listener<String>() {
                                                                @Override
                                                                public void onResponse(String ServerResponse) {
                                                                    if(ServerResponse.equals("Done")){










                                                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, post_checkbox_item,
                                                                                new Response.Listener<String>() {
                                                                                    @Override
                                                                                    public void onResponse(String ServerResponse) {
                                                                                        if(ServerResponse.equals("Updated")){
                                                                                            Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়েছে ", Toast.LENGTH_LONG).show();

                                                                                            Intent intent = null;
                                                                                            intent =  new Intent(getBaseContext(), Patient_TablayoutProfile.class);
                                                                                            startActivity(intent);
                                                                                        }
                                                                                        else if (ServerResponse.equals("Inserted")){

                                                                                            Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়েছে", Toast.LENGTH_LONG).show();

                                                                                            Intent intent = null;
                                                                                            intent =  new Intent(getBaseContext(), Patient_TablayoutProfile.class);
                                                                                            startActivity(intent);

                                                                                        }
                                                                                        else {
                                                                                            Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                                        }
                                                                                    }
                                                                                },
                                                                                new Response.ErrorListener() {
                                                                                    @Override
                                                                                    public void onErrorResponse(VolleyError volleyError) {
                                                                                        Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }) {
                                                                            @Override
                                                                            protected Map<String, String> getParams() {
                                                                                Map<String, String> params = new HashMap<String, String>();
                                                                                params.put("one", S_one);
                                                                                params.put("two", S_two);
                                                                                params.put("three", S_three);
                                                                                params.put("four", S_four);
                                                                                params.put("five", S_five);
                                                                                params.put("six", S_six);
                                                                                params.put("seven", S_seven);
                                                                                params.put("eight", S_eight);
                                                                                params.put("nine", S_nine);
                                                                                params.put("ten", S_ten);
                                                                                params.put("eleven", S_eleven);
                                                                                params.put("twelve", S_twelve);
                                                                                params.put("thirteen", S_thirteen);
                                                                                params.put("fourteen", S_fourteen);
                                                                                params.put("fifteen", S_fifteen);
                                                                                params.put("sixteen", S_sixteen);
                                                                                params.put("seventeen", S_seventeen);
                                                                                params.put("eighteen", S_eighteen);
                                                                                params.put("nineteen", S_nineteen);
                                                                                params.put("twenty", S_twenty);
                                                                                params.put("twentyone", S_twentyone);
                                                                                params.put("twentytwo", S_twentytwo);
                                                                                params.put("twentythree", S_twentythree);
                                                                                params.put("twentyfour", S_twentyfour);
                                                                                params.put("twentyfive", S_twentyfive);
                                                                                params.put("twentysix", S_twentysix);
                                                                                params.put("twentyseven", S_twentyseven);
                                                                                params.put("twentyeight", S_twentyeight);
                                                                                params.put("twentynine", S_twentynine);
                                                                                params.put("thirty", S_thirty);
                                                                                params.put("thirtyone", S_thirtyone);
                                                                                params.put("thirtytwo", S_thirtytwo);
                                                                                params.put("thirtythree", S_thirtythree);
                                                                                params.put("thirtyfour", S_thirtyfour);
                                                                                params.put("thirtyfive", S_thirtyfive);
                                                                                params.put("thirtysix", S_thirtysix);
                                                                                params.put("thirtyseven", S_thirtyseven);
                                                                                params.put("thirtyeight", S_thirtyeight);
                                                                                params.put("thirtynine", S_thirtynine);
                                                                                params.put("forty", S_forty);
                                                                                return params;
                                                                            }

                                                                        };
                                                                        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
                                                                        requestQueue.add(stringRequest);

                                                                    }
                                                                    else{
                                                                        Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError volleyError) {
                                                                    Toast.makeText(getBaseContext(), "আপনার তথ্য গুলো সঞ্চিত হয়নি। অনুগ্রহ করে আবার চেষ্টা করুন", Toast.LENGTH_LONG).show();
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
        );
    }
}
