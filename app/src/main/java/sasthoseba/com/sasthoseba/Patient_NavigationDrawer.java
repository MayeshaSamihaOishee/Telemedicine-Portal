package sasthoseba.com.sasthoseba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import sasthoseba.com.sasthoseba.adapter.ViewPagerAdapter;
import sasthoseba.com.sasthoseba.fragment.Common_HomepageSliderFragment;
import sasthoseba.com.sasthoseba.fragment.Patient_AllQuestionAnswer;
import sasthoseba.com.sasthoseba.fragment.Patient_FindDoctorFragment;
import sasthoseba.com.sasthoseba.fragment.Patient_QuestionFragment;

public class Patient_NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout2;
    ViewPager viewPager2;
    TextView PatientPhone, PatientName;


    String Holder;
    SharedPreferences sharedPreferences ;
    public static final String POST_URL= "http://ehealth.mdtauhidul.me/updateData_PatientPhonenum.php";
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_Specific_Patient.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout2 = (TabLayout)findViewById(R.id.tabLayout2);
        viewPager2=(ViewPager) findViewById(R.id.viewPager2);
        setDataToViewPager();
        tabLayout2.setupWithViewPager(viewPager2);


        sharedPreferences =getBaseContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        Holder = sharedPreferences.getString("Phone", null).toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("Done")) {

                            RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    int length=response.length();
                                    for (int i=0; i<length; i++){
                                        try {
                                            String Id=response.getJSONObject(i).getString("Id");
                                            String FirstName=response.getJSONObject(i).getString("FirstName");
                                            String LastName=response.getJSONObject(i).getString("LastName");
                                            String DateOfBirth=response.getJSONObject(i).getString("DateOfBirth");
                                            String Gender=response.getJSONObject(i).getString("Gender");
                                            String Phone=response.getJSONObject(i).getString("Phone");
                                            String Password=response.getJSONObject(i).getString("Password");
                                            String Email=response.getJSONObject(i).getString("Email");
                                            String Height=response.getJSONObject(i).getString("Height");
                                            String Weight=response.getJSONObject(i).getString("Weight");
                                            String BloodGroup=response.getJSONObject(i).getString("BloodGroup");

                                            String FullName= FirstName + " "+LastName;
                                            PatientName = (TextView)findViewById(R.id.PatientName);
                                            PatientPhone = (TextView)findViewById(R.id.PatientPhone);
                                            PatientName.setText(FullName);
                                            PatientPhone.setText(Phone);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "আপনার প্রশ্নটি লিখুন", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i= new Intent(getBaseContext(), Patient_Question.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment2(new Common_HomepageSliderFragment(), "হোম");
        adapter.addFragment2(new Patient_FindDoctorFragment(), "ডাক্তার খুজুন");
        adapter.addFragment2(new Patient_AllQuestionAnswer(), "প্রশ্নের উত্তর");
        adapter.addFragment2(new Patient_QuestionFragment(), "প্রশ্ন করুন");
        viewPager2.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = null;
            intent =  new Intent(getBaseContext(), Patient_TablayoutLoginSignup.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.view_profile) {
            intent =  new Intent(getBaseContext(), Patient_TablayoutProfile.class);

        } else if (id == R.id.my_question) {
            intent =  new Intent(getBaseContext(), Patient_MyQuestion.class);

        }  else if (id == R.id.help) {

        } else if (id == R.id.about_us) {

        }


        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
