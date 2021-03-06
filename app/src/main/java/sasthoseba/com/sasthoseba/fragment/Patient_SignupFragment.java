package sasthoseba.com.sasthoseba.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import sasthoseba.com.sasthoseba.Patient_NavigationDrawer;
import sasthoseba.com.sasthoseba.R;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Patient_SignupFragment extends Fragment {
    EditText eTfName, eTlName, eTdob, eTphoneNo, eTEmail, eTpass, eTconfirmpass;
    String FirstNameHolder, LastNameHolder, DateOfBirthHolder, PhoneHolder, EmailHolder, PasswordHolder,GenderHolder, GenderHolder2 ;
    Button SignupButton;
    RequestQueue requestQueue;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    public ProgressDialog pd;
    SharedPreferences sharedPreferences ;
    String Holder;
    SharedPreferences.Editor editor ;
    String HttpUrl = "http://ehealth.mdtauhidul.me/insertData_PatientSignup.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {

        View v = inflater.inflate(R.layout.patient_fragment_signup, null);
        eTfName = v.findViewById(R.id.firstName);
        eTlName = v.findViewById(R.id.lastName);
        eTdob = v.findViewById(R.id.dateOfBirth);
        eTphoneNo = v.findViewById(R.id.phonenum);
        eTEmail = v.findViewById(R.id.email);
        eTpass = v.findViewById(R.id.password);
        eTconfirmpass = v.findViewById(R.id.confirmPassword);
        SignupButton = (Button) v.findViewById(R.id.PatientRegister);
        requestQueue = Volley.newRequestQueue(getContext());
        radioSexGroup = (RadioGroup) v.findViewById(R.id.radioSex);

        sharedPreferences= this.getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        pd = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);

        SignupButton.setOnClickListener(new View.OnClickListener()  {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = null;
                                                boolean error = false;

                                                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                                                radioSexButton = (RadioButton) getActivity().findViewById(selectedId);
                                                GenderHolder2 = radioSexButton.getText().toString();

                                                String firsstname = eTfName.getText().toString();
                                                if (firsstname.isEmpty()) {
                                                    eTfName.setError("??????????????? ????????? ???????????????????????????");
                                                    error = true;
                                                } else if (firsstname.length() < 3) {
                                                    eTfName.setError("??????????????? ??????????????? ????????? ?????????");
                                                    error = true;
                                                }

                                                String lasstname = eTlName.getText().toString();
                                                if (lasstname.isEmpty()) {
                                                    eTlName.setError("????????? ????????? ???????????????????????????");
                                                    error = true;
                                                } else if (lasstname.length() < 3) {
                                                    eTlName.setError("????????? ??????????????? ????????? ?????????");
                                                    error = true;
                                                }

                                                String dateoofbirth = eTdob.getText().toString();
                                                if (dateoofbirth.isEmpty()) {
                                                    eTdob.setError("???????????? ??????????????? ???????????????????????????");
                                                    error = true;
                                                } else if ((dateoofbirth.matches("(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)[0-9]{2}"))) {

                                                } else {
                                                    eTdob.setError("???????????? ????????????????????? ???????????? ??????");
                                                    error = true;
                                                }

                                                String phoonenum = eTphoneNo.getText().toString();
                                                Holder = phoonenum;
                                                if (phoonenum.isEmpty()) {
                                                    eTphoneNo.setError("?????????????????? ??????????????? ???????????????????????????");
                                                    error = true;
                                                } else if (phoonenum.length() == 11) {
                                                    if((phoonenum.matches("(01)[156789][0-9]{8}"))){

                                                    }
                                                        else {
                                                        eTphoneNo.setError("?????????????????? ????????????????????? ????????? ?????????");
                                                        error = true;
                                                    }
                                                }  else {
                                                    eTphoneNo.setError("?????????????????? ????????????????????? ????????? ?????????");
                                                    error = true;
                                                }
                                                String eemail = eTEmail.getText().toString();
                                                if (eemail.isEmpty()) {
                                                    eTEmail.setError("??????????????? ???????????????????????????");
                                                    error = true;
                                                } else if (eemail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                                                } else {
                                                    eTEmail.setError("??????????????? ???????????? ??????");
                                                    error = true;
                                                }

                                                String passwoord = eTpass.getText().toString();
                                                if (passwoord.isEmpty()) {
                                                    eTpass.setError("?????????????????????????????? ???????????????????????????");
                                                    error = true;
                                                } else if (passwoord.length() < 6) {
                                                    eTpass.setError("?????????????????????????????? ?????????????????? ????????????????????? 6 ?????? ??????????????? ????????? ?????????");
                                                    error = true;
                                                }

                                                String confirmpasswoord = eTconfirmpass.getText().toString();
                                                if (confirmpasswoord.isEmpty()) {
                                                    eTconfirmpass.setError("??????????????? ???????????????????????????????????? ????????????????????? ????????????");
                                                    error = true;
                                                } else if (confirmpasswoord.equals(passwoord)) {
                                                } else {
                                                    eTconfirmpass.setError("?????????????????????????????? ???????????? ????????? ???????????? ?????????????????? ????????????");
                                                    error = true;
                                                }

                                                if (error == true) {
                                                    Toast.makeText(getContext(), "???????????? ???????????? ?????????", Toast.LENGTH_LONG).show();
                                                }

                                                //Last Statement
                                                else {
                                                    GetValueFromEditText();
                                                    pd.setMessage("????????????????????? ????????? ????????????????????? ????????????");
                                                    pd.show();
                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                                                            new Response.Listener<String>() {
                                                                @Override
                                                                public void onResponse(String ServerResponse) {
                                                                    pd.hide();

                                                                    if(ServerResponse.equals("Done")){
                                                                        if(sharedPreferences.getString("Phone", null) == null)
                                                                        {
                                                                            editor = sharedPreferences.edit();
                                                                            editor.putString("Phone", Holder);
                                                                            editor.commit();
                                                                        }
                                                                        else {
                                                                            editor = sharedPreferences.edit();
                                                                            editor.putString("Phone", Holder);
                                                                            editor.commit();
                                                                        }
                                                                        Toast.makeText(getContext(), "????????????????????? ! ??????????????? ??????????????????????????????????????? ????????????????????? ???????????????", Toast.LENGTH_LONG).show();
                                                                        Intent i = new Intent(getActivity(), Patient_NavigationDrawer.class);
                                                                        startActivity(i);

                                                                    }
                                                                    else if(ServerResponse.equals("PhoneExist")){
                                                                        Toast.makeText(getContext(), "?????? ????????? ????????????????????? ???????????? ???????????????????????? ??????????????????????????????????????? ????????? ?????????????????? ???????????? ????????? ??????????????? ??????????????? ?????????????????? ????????????", Toast.LENGTH_LONG).show();
                                                                    }
                                                                    else if(ServerResponse.equals("EmailExist")){
                                                                        Toast.makeText(getContext(), "?????? ??????????????? ???????????? ???????????????????????? ??????????????????????????????????????? ????????? ?????????????????? ???????????? ??????????????? ??????????????? ?????????????????? ????????????", Toast.LENGTH_LONG).show();
                                                                    }
                                                                    else {
                                                                        Toast.makeText(getContext(), "???????????? ?????????????????? ???????????????", Toast.LENGTH_LONG).show();
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError volleyError) {
                                                                    Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                                                                }
                                                            }) {
                                                        @Override
                                                        protected Map<String, String> getParams() {
                                                            Map<String, String> params = new HashMap<String, String>();

                                                            params.put("FirstName", FirstNameHolder);
                                                            params.put("LastName", LastNameHolder);
                                                            params.put("DateOfBirth", DateOfBirthHolder);
                                                            params.put("Gender",GenderHolder );
                                                            params.put("Phone", PhoneHolder);
                                                            params.put("Email", EmailHolder);
                                                            params.put("Password", PasswordHolder);
                                                            return params;
                                                        }
                                                    };
                                                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                                    requestQueue.add(stringRequest);
                                                }
                                            }
                                        }
        );
        Button btn =(Button) v.findViewById(R.id.PatientRegister);
        return v;
    }

    private void GetValueFromEditText() {
        FirstNameHolder = eTfName.getText().toString().trim();
        LastNameHolder = eTlName.getText().toString().trim();
        DateOfBirthHolder = eTdob.getText().toString().trim();
        PhoneHolder = eTphoneNo.getText().toString().trim();
        EmailHolder = eTEmail.getText().toString().trim();
        PasswordHolder=eTpass.getText().toString().trim();
        GenderHolder = GenderHolder2;
    }
}

