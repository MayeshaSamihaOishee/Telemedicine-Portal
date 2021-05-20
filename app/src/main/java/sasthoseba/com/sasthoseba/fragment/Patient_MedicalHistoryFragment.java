package sasthoseba.com.sasthoseba.fragment;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.widget.Button;
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
        import sasthoseba.com.sasthoseba.Patient_EditMedicalInfo;
        import sasthoseba.com.sasthoseba.R;


public class Patient_MedicalHistoryFragment extends Fragment {
    ArrayList<String> arrayList;
    String Holder;
    SharedPreferences sharedPreferences ;

    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_PatientPhonenum.php";
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_PatientMedicalHistory.php";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View v = inflater.inflate(R.layout.patient_fragment_medical_history, null);

        sharedPreferences =getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();
        arrayList=new ArrayList<>();

        final TextView T_one = (TextView) v.findViewById(R.id.one);
        final TextView T_two = (TextView) v.findViewById(R.id.two);
        final TextView T_three = (TextView) v.findViewById(R.id.three);
        final TextView T_four = (TextView) v.findViewById(R.id.four);
        final TextView T_five =(TextView) v.findViewById(R.id.five);
        final TextView T_six =(TextView) v.findViewById(R.id.six);
        final TextView T_seven =(TextView) v.findViewById(R.id.seven);
        final TextView T_eight =(TextView) v.findViewById(R.id.eight);
        final TextView T_nine =(TextView) v.findViewById(R.id.nine);
        final TextView T_ten =(TextView) v.findViewById(R.id.ten);
        final TextView T_eleven =(TextView) v.findViewById(R.id.eleven);
        final TextView T_twelve =(TextView) v.findViewById(R.id.twelve);
        final TextView T_thirteen =(TextView) v.findViewById(R.id.thirteen);
        final TextView T_fourteen =(TextView) v.findViewById(R.id.fourteen);
        final TextView T_fifteen =(TextView) v.findViewById(R.id.fifteen);
        final TextView T_sixteen =(TextView) v.findViewById(R.id.sixteen);
        final TextView T_seventeen =(TextView) v.findViewById(R.id.seventeen);
        final TextView T_eighteen =(TextView) v.findViewById(R.id.eighteen);
        final TextView T_nineteen =(TextView) v.findViewById(R.id.nineteen);
        final TextView T_twenty =(TextView) v.findViewById(R.id.twenty);
        final TextView T_twentyone =(TextView) v.findViewById(R.id.twentyone);
        final TextView T_twentytwo =(TextView) v.findViewById(R.id.twentytwo);
        final TextView T_twentythree =(TextView) v.findViewById(R.id.twentythree);
        final TextView T_twentyfour =(TextView) v.findViewById(R.id.twentyfour);
        final TextView T_twentyfive =(TextView) v.findViewById(R.id.twentyfive);
        final TextView T_twentysix =(TextView) v.findViewById(R.id.twentysix);
        final TextView T_twentyseven =(TextView) v.findViewById(R.id.twentyseven);
        final TextView T_twentyeight =(TextView) v.findViewById(R.id.twentyeight);
        final TextView T_twentynine =(TextView) v.findViewById(R.id.twentynine);
        final TextView T_thirty =(TextView) v.findViewById(R.id.thirty);
        final TextView T_thirtyone =(TextView) v.findViewById(R.id.thirtyone);
        final TextView T_thirtytwo =(TextView) v.findViewById(R.id.thirtytwo);
        final TextView T_thirtythree =(TextView) v.findViewById(R.id.thirtythree);
        final TextView T_thirtyfour =(TextView) v.findViewById(R.id.thirtyfour);
        final TextView T_thirtyfive =(TextView) v.findViewById(R.id.thirtyfive);
        final TextView T_thirtysix =(TextView) v.findViewById(R.id.thirtysix);
        final TextView T_thirtyseven =(TextView) v.findViewById(R.id.thirtyseven);
        final TextView T_thirtyeight =(TextView) v.findViewById(R.id.thirtyeight);
        final TextView T_thirtynine =(TextView) v.findViewById(R.id.thirtynine);
        final TextView T_forty =(TextView) v.findViewById(R.id.forty);

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
                                            String one=response.getJSONObject(i).getString("One");
                                            String two=response.getJSONObject(i).getString("two");
                                            String three=response.getJSONObject(i).getString("three");
                                            String four=response.getJSONObject(i).getString("four");
                                            String five=response.getJSONObject(i).getString("five");
                                            String six=response.getJSONObject(i).getString("six");
                                            String seven=response.getJSONObject(i).getString("seven");
                                            String eight=response.getJSONObject(i).getString("eight");
                                            String nine=response.getJSONObject(i).getString("nine");
                                            String ten=response.getJSONObject(i).getString("ten");

                                            String eleven=response.getJSONObject(i).getString("eleven");
                                            String twelve=response.getJSONObject(i).getString("twelve");
                                            String thirteen=response.getJSONObject(i).getString("thirteen");
                                            String fourteen=response.getJSONObject(i).getString("fourteen");
                                            String fifteen=response.getJSONObject(i).getString("fifteen");
                                            String sixteen=response.getJSONObject(i).getString("sixteen");
                                            String seventeen=response.getJSONObject(i).getString("seventeen");
                                            String eighteen=response.getJSONObject(i).getString("eighteen");
                                            String nineteen=response.getJSONObject(i).getString("nineteen");
                                            String twenty=response.getJSONObject(i).getString("twenty");

                                            String twentyone=response.getJSONObject(i).getString("twentyone");
                                            String twentytwo=response.getJSONObject(i).getString("twentytwo");
                                            String twentythree=response.getJSONObject(i).getString("twentythree");
                                            String twentyfour=response.getJSONObject(i).getString("twentyfour");
                                            String twentyfive=response.getJSONObject(i).getString("twentyfive");
                                            String twentysix=response.getJSONObject(i).getString("twentysix");
                                            String twentyseven=response.getJSONObject(i).getString("twentyseven");
                                            String twentyeight=response.getJSONObject(i).getString("twentyeight");
                                            String twentynine=response.getJSONObject(i).getString("twentynine");
                                            String thirty=response.getJSONObject(i).getString("thirty");

                                            String thirtyone=response.getJSONObject(i).getString("thirtyone");
                                            String thirtytwo=response.getJSONObject(i).getString("thirtytwo");
                                            String thirtythree=response.getJSONObject(i).getString("thirtythree");
                                            String thirtyfour=response.getJSONObject(i).getString("thirtyfour");
                                            String thirtyfive=response.getJSONObject(i).getString("thirtyfive");
                                            String thirtysix=response.getJSONObject(i).getString("thirtysix");
                                            String thirtyseven=response.getJSONObject(i).getString("thirtyseven");
                                            String thirtyeight=response.getJSONObject(i).getString("thirtyeight");
                                            String thirtynine=response.getJSONObject(i).getString("thirtynine");
                                            String forty=response.getJSONObject(i).getString("forty");



                                            if(one.length() == 0 || one.equals("") || one == null)
                                            {
                                                T_one.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_one.setVisibility(View.VISIBLE);
                                            }

                                            if(two.length() == 0 || two.equals("") || two == null)
                                            {
                                                T_two.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_two.setVisibility(View.VISIBLE);
                                            }

                                            if(three.length() == 0 || three.equals("") || three == null)
                                            {
                                                T_three.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_three.setVisibility(View.VISIBLE);
                                            }

                                            if(four.length() == 0 || four.equals("") || four == null)
                                            {
                                                T_four.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_four.setVisibility(View.VISIBLE);
                                            }


                                            if(five.length() == 0 || five.equals("") || five == null)
                                            {
                                                T_five.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_five.setVisibility(View.VISIBLE);
                                            }

                                            if(six.length() == 0 || six.equals("") || six == null)
                                            {
                                                T_six.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_six.setVisibility(View.VISIBLE);
                                            }

                                            if(seven.length() == 0 || seven.equals("") || seven == null)
                                            {
                                                T_seven.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_seven.setVisibility(View.VISIBLE);
                                            }

                                            if(eight.length() == 0 || eight.equals("") || eight == null)
                                            {
                                                T_eight.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_eight.setVisibility(View.VISIBLE);
                                            }

                                            if(nine.length() == 0 || nine.equals("") || nine == null)
                                            {
                                                T_nine.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_nine.setVisibility(View.VISIBLE);
                                            }

                                            if(ten.length() == 0 || ten.equals("") || ten == null)
                                            {
                                                T_ten.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_ten.setVisibility(View.VISIBLE);
                                            }




                                            if(eleven.length() == 0 || eleven.equals("") || eleven == null)
                                            {
                                                T_eleven.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_eleven.setVisibility(View.VISIBLE);
                                            }

                                            if(twelve.length() == 0 || twelve.equals("") || twelve == null)
                                            {
                                                T_twelve.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twelve.setVisibility(View.VISIBLE);
                                            }

                                            if(thirteen.length() == 0 || thirteen.equals("") || thirteen == null)
                                            {
                                                T_thirteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirteen.setVisibility(View.VISIBLE);
                                            }

                                            if(fourteen.length() == 0 || fourteen.equals("") || fourteen == null)
                                            {
                                                T_fourteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_fourteen.setVisibility(View.VISIBLE);
                                            }


                                            if(fifteen.length() == 0 || fifteen.equals("") || fifteen == null)
                                            {
                                                T_fifteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_fifteen.setVisibility(View.VISIBLE);
                                            }

                                            if(sixteen.length() == 0 || sixteen.equals("") || sixteen == null)
                                            {
                                                T_sixteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_sixteen.setVisibility(View.VISIBLE);
                                            }

                                            if(seventeen.length() == 0 || seventeen.equals("") || seventeen == null)
                                            {
                                                T_seventeen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_seventeen.setVisibility(View.VISIBLE);
                                            }

                                            if(eighteen.length() == 0 || eighteen.equals("") || eighteen == null)
                                            {
                                                T_eighteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_eighteen.setVisibility(View.VISIBLE);
                                            }

                                            if(nineteen.length() == 0 || nineteen.equals("") || nineteen == null)
                                            {
                                                T_nineteen.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_nineteen.setVisibility(View.VISIBLE);
                                            }

                                            if(twenty.length() == 0 || twenty.equals("") || twenty == null)
                                            {
                                                T_twenty.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twenty.setVisibility(View.VISIBLE);
                                            }






                                            if(twentyone.length() == 0 || twentyone.equals("") || twentyone == null)
                                            {
                                                T_twentyone.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentyone.setVisibility(View.VISIBLE);
                                            }

                                            if(twentytwo.length() == 0 || twentytwo.equals("") || twentytwo == null)
                                            {
                                                T_twentytwo.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentytwo.setVisibility(View.VISIBLE);
                                            }

                                            if(twentythree.length() == 0 || twentythree.equals("") || twentythree == null)
                                            {
                                                T_twentythree.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentythree.setVisibility(View.VISIBLE);
                                            }

                                            if(twentyfour.length() == 0 || twentyfour.equals("") || twentyfour == null)
                                            {
                                                T_twentyfour.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentyfour.setVisibility(View.VISIBLE);
                                            }


                                            if(twentyfive.length() == 0 || twentyfive.equals("") || twentyfive == null)
                                            {
                                                T_twentyfive.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentyfive.setVisibility(View.VISIBLE);
                                            }

                                            if(twentysix.length() == 0 || twentysix.equals("") || twentysix == null)
                                            {
                                                T_twentysix.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentysix.setVisibility(View.VISIBLE);
                                            }

                                            if(twentyseven.length() == 0 || twentyseven.equals("") || twentyseven == null)
                                            {
                                                T_twentyseven.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentyseven.setVisibility(View.VISIBLE);
                                            }

                                            if(twentyeight.length() == 0 || twentyeight.equals("") || twentyeight == null)
                                            {
                                                T_twentyeight.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentyeight.setVisibility(View.VISIBLE);
                                            }

                                            if(twentynine.length() == 0 || twentynine.equals("") || twentynine == null)
                                            {
                                                T_twentynine.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_twentynine.setVisibility(View.VISIBLE);
                                            }

                                            if(thirty.length() == 0 || thirty.equals("") || thirty == null)
                                            {
                                                T_thirty.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirty.setVisibility(View.VISIBLE);
                                            }






                                            if(thirtyone.length() == 0 || thirtyone.equals("") || thirtyone == null)
                                            {
                                                T_thirtyone.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtyone.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtytwo.length() == 0 || thirtytwo.equals("") || thirtytwo == null)
                                            {
                                                T_thirtytwo.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtytwo.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtythree.length() == 0 || thirtythree.equals("") || thirtythree == null)
                                            {
                                                T_thirtythree.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtythree.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtyfour.length() == 0 || thirtyfour.equals("") || thirtyfour == null)
                                            {
                                                T_thirtyfour.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtyfour.setVisibility(View.VISIBLE);
                                            }


                                            if(thirtyfive.length() == 0 || thirtyfive.equals("") || thirtyfive == null)
                                            {
                                                T_thirtyfive.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtyfive.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtysix.length() == 0 || thirtysix.equals("") || thirtysix == null)
                                            {
                                                T_thirtysix.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtysix.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtyseven.length() == 0 || thirtyseven.equals("") || thirtyseven == null)
                                            {
                                                T_thirtyseven.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtyseven.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtyeight.length() == 0 || thirtyeight.equals("") || thirtyeight == null)
                                            {
                                                T_thirtyeight.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtyeight.setVisibility(View.VISIBLE);
                                            }

                                            if(thirtynine.length() == 0 || thirtynine.equals("") || thirtynine == null)
                                            {
                                                T_thirtynine.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_thirtynine.setVisibility(View.VISIBLE);
                                            }

                                            if(forty.length() == 0 || forty.equals("") || forty == null)
                                            {
                                                T_forty.setVisibility(View.GONE);
                                            }
                                            else {
                                                T_forty.setVisibility(View.VISIBLE);
                                            }



                                            T_one.setText(one);
                                            T_two.setText(two);
                                            T_three.setText(three);
                                            T_four.setText(four);
                                            T_five.setText(five);
                                            T_six.setText(six);
                                            T_seven.setText(seven);
                                            T_eight.setText(eight);
                                            T_nine.setText(nine);
                                            T_ten.setText(ten);
                                            T_eleven.setText(eleven);
                                            T_twelve.setText(twelve);
                                            T_thirteen.setText(thirteen);
                                            T_fourteen.setText(fourteen);
                                            T_fifteen.setText(fifteen);
                                            T_sixteen.setText(sixteen);
                                            T_seventeen.setText(seventeen);
                                            T_eighteen.setText(eighteen);
                                            T_nineteen.setText(nineteen);
                                            T_twenty.setText(twenty);
                                            T_twentyone.setText(twentyone);
                                            T_twentytwo.setText(twentytwo);
                                            T_twentythree.setText(twentythree);
                                            T_twentyfour.setText(twentyfour);
                                            T_twentyfive.setText(twentyfive);
                                            T_twentysix.setText(twentysix);
                                            T_twentyseven.setText(twentyseven);
                                            T_twentyeight.setText(twentyeight);
                                            T_twentynine.setText(twentynine);
                                            T_thirty.setText(thirty);
                                            T_thirtyone.setText(thirtyone);
                                            T_thirtytwo.setText(thirtytwo);
                                            T_thirtythree.setText(thirtythree);
                                            T_thirtyfour.setText(thirtyfour);
                                            T_thirtyfive.setText(thirtyfive);
                                            T_thirtysix.setText(thirtysix);
                                            T_thirtyseven.setText(thirtyseven);
                                            T_thirtyeight.setText(thirtyeight);
                                            T_thirtynine.setText(thirtynine);
                                            T_forty.setText(forty);

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
                Intent i= new Intent(getActivity(), Patient_EditMedicalInfo.class);
                startActivity(i);
            }
        };
        Button btn =(Button) v.findViewById(R.id.medicalHistory);
        btn.setOnClickListener(listener);
        return v;

    }
}
