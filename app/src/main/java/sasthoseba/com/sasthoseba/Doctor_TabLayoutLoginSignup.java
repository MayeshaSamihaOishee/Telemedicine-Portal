package sasthoseba.com.sasthoseba;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sasthoseba.com.sasthoseba.adapter.ViewPagerAdapter;
import sasthoseba.com.sasthoseba.fragment.Doctor_LoginFragment;
import sasthoseba.com.sasthoseba.fragment.Doctor_SignupFragment;

public class Doctor_TabLayoutLoginSignup extends AppCompatActivity {

    Toolbar toolBar7;
    TabLayout tabLayout7;
    ViewPager viewPager7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_tablayout_login_signup);

        tabLayout7 = (TabLayout)findViewById(R.id.tabLayout7);
        viewPager7=(ViewPager) findViewById(R.id.viewPager7);

        setSupportActionBar(toolBar7);
        setDataToViewPager();
        tabLayout7.setupWithViewPager(viewPager7);
    }


    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment3(new Doctor_LoginFragment(), "লগইন");
        adapter.addFragment3(new Doctor_SignupFragment(), "নিবন্ধন করুন");
        viewPager7.setAdapter(adapter);
    }
}
