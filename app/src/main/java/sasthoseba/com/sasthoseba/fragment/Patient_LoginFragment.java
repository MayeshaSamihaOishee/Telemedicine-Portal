package sasthoseba.com.sasthoseba.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import sasthoseba.com.sasthoseba.Patient_NavigationDrawer;
import sasthoseba.com.sasthoseba.R;

public class Patient_LoginFragment extends Fragment {

    EditText eTPhone, eTPassword;
    Button LoginButton;
    String HttpUrl = "http://ehealth.mdtauhidul.me/patient_login.php";
    private ProgressDialog pd;

    SharedPreferences sharedPreferences ;
    String ValueHolder;
    SharedPreferences.Editor editor ;
    RequestQueue requestQueue;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {

        View v = inflater.inflate(R.layout.patient_fragment_login, null);
        eTPhone = v.findViewById(R.id.phonenumber);
        eTPassword = v.findViewById(R.id.password);
        pd = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);

        sharedPreferences= this.getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                boolean error = false;

                String phn = eTPhone.getText().toString();
                ValueHolder = phn;

                if (phn.isEmpty()) {
                    eTPhone.setError("?????????????????? ??????????????? ???????????????????????????");
                    error = true;
                } else if (phn.length() == 11) {
                    if ((phn.matches("(01)[156789][0-9]{8}"))) {
                    }
                    else {
                        eTPhone.setError("?????????????????? ????????????????????? ????????? ?????????");
                        error = true;
                    }
                }  else {
                    eTPhone.setError("?????????????????? ????????????????????? ????????? ?????????");
                    error = true;
                }

                String pass = eTPassword.getText().toString();
                if (pass.isEmpty()) {
                    eTPassword.setError("?????????????????????????????? ???????????????????????????");
                    error = true;
                } else if (pass.length() < 6) {
                    eTPassword.setError("?????????????????????????????? ?????????????????? ????????????????????? 6 ?????? ??????????????? ????????? ?????????");
                    error = true;
                }
                if (error) {
                    Toast.makeText(getContext(), "???????????? ???????????? ?????????", Toast.LENGTH_LONG).show();
                }

                //Last Statement
                else
                    {
                        loginRequest();
                }
            }
        };
        Button btn = (Button) v.findViewById(R.id.login);
        btn.setOnClickListener(listener);
        return v;
    }

    private void loginRequest(){
        pd.setMessage("????????????????????? ????????? ????????????????????? ????????????");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        if(response.equals("Login")) {

                            if(sharedPreferences.getString("Phone", null) == null)
                            {
                                editor = sharedPreferences.edit();
                                editor.putString("Phone", ValueHolder);
                                editor.commit();
                                //Toast.makeText(getActivity(),"New Value Submitted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                editor = sharedPreferences.edit();
                                editor.putString("Phone", ValueHolder);
                                editor.commit();
                                //Toast.makeText(getActivity(),"Value Updated", Toast.LENGTH_LONG).show();
                            }

                            //Toast.makeText(getContext(), "???????????? ????????????????????? ???????????? ??????????????????", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getActivity(), Patient_NavigationDrawer.class);
                            startActivity(i);
                        }
                        else if (response.equals("Register")){
                            Toast.makeText(getContext(), "?????? ???????????? ????????? ??????????????? ??????????????? ?????????????????? ???????????????????????????????????? ???????????????", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "???????????? ???????????? ???????????? ????????? ???????????? ?????????????????? ???????????????", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pd.hide();
                        Toast.makeText(getContext(), "on error response", Toast.LENGTH_LONG).show();
                        Log.d("ErrorResponse", finalResponse);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Phone", eTPhone.getText().toString());
                params.put("Password", eTPassword.getText().toString());
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
}