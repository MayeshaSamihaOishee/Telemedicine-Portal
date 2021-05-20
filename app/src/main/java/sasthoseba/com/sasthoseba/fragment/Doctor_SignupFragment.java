package sasthoseba.com.sasthoseba.fragment;

import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import sasthoseba.com.sasthoseba.R;

public class Doctor_SignupFragment extends Fragment {

    EditText eTfName, eTlName, eTdob, eTphoneNo, eTEmail, eTpass, eTconfirmpass , eTregnum, eTqualificationdoc, eTscheduledoc;
    String [] SpecialField= {"মেডিসিন", "মানসিক সাস্থ্য" , "শিশুরোগ", "গর্ভাবস্থা" , "অর্থোপেডিক" , "দন্তচিকিত্সা", "অন্যান্য"};
    String SpecialFieldType, SpecialFieldTypeCopy;
    Button SignupButtonDoctor;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    public ProgressDialog pd;
    SharedPreferences sharedPreferences ;
    String Holder;
    SharedPreferences.Editor editor ;
    RequestQueue requestQueue;

    String FirstNameHolderD, LastNameHolderD,GenderHolder2, GenderHolder, DateOfBirthHolderD, PhoneHolderD, EmailHolderD, PasswordHolderD, QualificationHolderD, ScheduleHolderD, RegistrationNoHolderD ;

    String HttpUrl = "http://ehealth.mdtauhidul.me/insertData_DoctorSignup.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {

        View v = inflater.inflate(R.layout.doctor_fragment_signup, null);

        eTfName = v.findViewById(R.id.firstName);
        eTlName = v.findViewById(R.id.lastName);
        eTdob = v.findViewById(R.id.dateOfBirth);
        eTphoneNo = v.findViewById(R.id.phonenum);
        eTEmail = v.findViewById(R.id.email);
        eTpass = v.findViewById(R.id.password);
        eTconfirmpass = v.findViewById(R.id.confirmPassword);
        eTregnum = v.findViewById(R.id.doctorregistration);
        eTqualificationdoc = v.findViewById(R.id.educationquality);
        eTscheduledoc= v.findViewById(R.id.doctorschedule);
        radioSexGroup = (RadioGroup) v.findViewById(R.id.radioSex);

        SignupButtonDoctor = (Button)v.findViewById(R.id.registerdoctor);
        requestQueue = Volley.newRequestQueue(getContext());

        sharedPreferences= this.getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        pd = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);

        SignupButtonDoctor.setOnClickListener(new View.OnClickListener(){
                                                  @Override
                                                  public void onClick(View v) {

                                                      Intent intent = null;
                                                      boolean error=false;

                                                      int selectedId = radioSexGroup.getCheckedRadioButtonId();
                                                      radioSexButton = (RadioButton) getActivity().findViewById(selectedId);
                                                      GenderHolder2 = radioSexButton.getText().toString();


                                                      String firsstname = eTfName.getText().toString();
                                                      if (firsstname.isEmpty()) {
                                                          eTfName.setError("প্রথম নাম অনুপস্থিত");
                                                          error = true;
                                                      }
                                                      else if (firsstname.length() < 3) {
                                                          eTfName.setError("প্রথম নামটি খুব ছোট");
                                                          error = true;
                                                      }

                                                      String lasstname = eTlName.getText().toString();
                                                      if (lasstname.isEmpty()) {
                                                          eTlName.setError("শেষ নাম অনুপস্থিত");
                                                          error = true;
                                                      } else if (lasstname.length() < 3) {
                                                          eTlName.setError("শেষ নামটি খুব ছোট");
                                                          error = true;
                                                      }

                                                      String dateoofbirth = eTdob.getText().toString();
                                                      if (dateoofbirth.isEmpty()) {
                                                          eTdob.setError("জন্ম তারিখ অনুপস্থিত");
                                                          error = true;
                                                      } else if ((dateoofbirth.matches("(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)[0-9]{2}"))) {

                                                      } else {
                                                          eTdob.setError("জন্ম তারিখটি সঠিক নয়");
                                                          error = true;
                                                      }

                                                      String phoonenum = eTphoneNo.getText().toString();
                                                      Holder = phoonenum;
                                                      if (phoonenum.isEmpty()) {
                                                          eTphoneNo.setError("মোবাইল নম্বর অনুপস্থিত");
                                                          error = true;
                                                      } else if (phoonenum.length() == 11) {
                                                          if ((phoonenum.matches("(01)[156789][0-9]{8}"))) {
                                                          }
                                                          else {
                                                              eTphoneNo.setError("মোবাইল নম্বরটি সঠিক নয়");
                                                              error = true;
                                                          }
                                                      } else {
                                                          eTphoneNo.setError("মোবাইল নম্বরটি সঠিক নয়");
                                                          error = true;
                                                      }

                                                      String eemail = eTEmail.getText().toString();
                                                      if (eemail.isEmpty()) {
                                                          eTEmail.setError("ইমেইল অনুপস্থিত");
                                                          error = true;
                                                      } else if (eemail.matches( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                              + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                                                      } else {
                                                          eTEmail.setError("ইমেইল সঠিক নয়");
                                                          error = true;
                                                      }

                                                      String passwoord = eTpass.getText().toString();
                                                      if (passwoord.isEmpty()) {
                                                          eTpass.setError("পাসওয়ার্ড অনুপস্থিত");
                                                          error = true;
                                                      } else if (passwoord.length() < 6) {
                                                          eTpass.setError("পাসওয়ার্ড অবশ্যই কমপক্ষে 6 টি অক্ষর হতে হবে");
                                                          error = true;
                                                      }

                                                      String confirmpasswoord = eTconfirmpass.getText().toString();
                                                      if (confirmpasswoord.isEmpty()) {
                                                          eTconfirmpass.setError("আপনার পাসওয়ার্ডটি নিশ্চিত করুন");
                                                          error = true;
                                                      } else if (confirmpasswoord.equals(passwoord)) {
                                                      } else {
                                                          eTconfirmpass.setError("পাসওয়ার্ডটি সঠিক নয় ");
                                                          error = true;
                                                      }

                                                      String registrationnum = eTregnum.getText().toString();
                                                      if (registrationnum.isEmpty()) {
                                                          eTregnum.setError("ডাক্তারের নিবন্ধন সংখ্যা অনুপস্থিত");
                                                          error = true;
                                                      }

                                                      else if (registrationnum.startsWith("0") ) {
                                                          eTregnum.setError("নিবন্ধন সংখ্যা অবৈধ");
                                                          error = true;
                                                      }
                                                      else {
                                                      }

                                                      if (error==true) {
                                                          //Toast.makeText(getContext(), "ডেটা সঠিক নয়", Toast.LENGTH_LONG).show();
                                                      }
                                                      else {
                                                          GetValueFromEditText();
                                                          pd.setMessage("অনুগ্রহ করে অপেক্ষা করুন");
                                                          pd.show();
                                                          StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                                                                  new Response.Listener<String>() {

                                                                      @Override
                                                                      public void onResponse(String response) {
                                                                          pd.hide();

                                                                          if(response.equals("Done")){
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
                                                                              Toast.makeText(getContext(), "স্বাগতম ! আপনার রেজিস্ট্রেশান সম্পন্ন হয়েছে", Toast.LENGTH_LONG).show();
                                                                              Intent i = new Intent(getActivity(), Doctor_NavigationDrawer.class);
                                                                              startActivity(i);

                                                                          }
                                                                          else if(response.equals("PhoneExist")){
                                                                              Toast.makeText(getContext(), "এই ফোন নম্বরটি দিয়ে ইতিমধ্যে রেজিস্ট্রেশান করা হয়েছে। অন্য ফোন নম্বর দিয়ে চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                          }
                                                                          else if(response.equals("EmailExist")){
                                                                              Toast.makeText(getContext(), "এই ইমেইল দিয়ে ইতিমধ্যে রেজিস্ট্রেশান করা হয়েছে। অন্য ইমেইল দিয়ে চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                          }
                                                                          else if(response.equals("RegistrationNoExist")){
                                                                              Toast.makeText(getContext(), "এই রেজিস্ট্রেশান নম্বরটি দিয়ে ইতিমধ্যে রেজিস্ট্রেশান করা হয়েছে। অন্য রেজিস্ট্রেশান নম্বর দিয়ে চেষ্টা করুন", Toast.LENGTH_LONG).show();
                                                                          }
                                                                          else {
                                                                              Toast.makeText(getContext(), "আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                                                                          }
                                                                      }
                                                                  },
                                                                  new Response.ErrorListener() {
                                                                      @Override
                                                                      public void onErrorResponse(VolleyError volleyError) {
                                                                          pd.hide();
                                                                          Toast.makeText(getContext(), "কিছু ভুল হয়েছে,  আবার চেষ্টা করুন ।", Toast.LENGTH_LONG).show();

                                                                      }
                                                                  }) {
                                                              @Override
                                                              protected Map<String, String> getParams() {

                                                                  // Creating Map String Params.
                                                                  Map<String, String> params = new HashMap<String, String>();

                                                                  // Adding All values to Params.
                                                                  params.put("FirstName", FirstNameHolderD);
                                                                  params.put("LastName", LastNameHolderD);
                                                                  params.put("DateOfBirth", DateOfBirthHolderD);
                                                                  params.put("Gender",GenderHolder );
                                                                  params.put("Phone", PhoneHolderD);
                                                                  params.put("Email", EmailHolderD);
                                                                  params.put("Password", PasswordHolderD);
                                                                  params.put("Qualification", QualificationHolderD);
                                                                  params.put("Schedule", ScheduleHolderD);
                                                                  params.put("SpecialField", SpecialFieldType);
                                                                  params.put("RegistrationNo", RegistrationNoHolderD);
                                                                  return params;
                                                              }

                                                          };
                                                          // Creating RequestQueue.
                                                          RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                                          // Adding the StringRequest object into requestQueue.
                                                          requestQueue.add(stringRequest);
                                                      }
                                                  }
                                              }
        );
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getContext(), R.layout.spinner_item,SpecialField);

        MaterialBetterSpinner betterSpinner= (MaterialBetterSpinner)v.findViewById(R.id.androidspinner);
        betterSpinner.setAdapter(arrayAdapter);

        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SpecialFieldTypeCopy = adapterView.getItemAtPosition(position).toString();
                int mSelectedId = position;
            }
        });



        Button btn =(Button) v.findViewById(R.id.registerdoctor);
        return v;
    }
    private void GetValueFromEditText() {
        FirstNameHolderD = eTfName.getText().toString().trim();
        LastNameHolderD = eTlName.getText().toString().trim();
        DateOfBirthHolderD = eTdob.getText().toString().trim();
        PhoneHolderD = eTphoneNo.getText().toString().trim();
        EmailHolderD= eTEmail.getText().toString().trim();
        PasswordHolderD=eTpass.getText().toString().trim();
        QualificationHolderD = eTqualificationdoc.getText().toString().trim();
        ScheduleHolderD = eTscheduledoc.getText().toString().trim();
        RegistrationNoHolderD =eTregnum.getText().toString().trim();
        GenderHolder = GenderHolder2;
        SpecialFieldType=SpecialFieldTypeCopy;

    }
}

