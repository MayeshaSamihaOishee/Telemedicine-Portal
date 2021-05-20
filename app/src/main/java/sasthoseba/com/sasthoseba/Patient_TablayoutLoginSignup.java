package sasthoseba.com.sasthoseba;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sasthoseba.com.sasthoseba.adapter.ViewPagerAdapter;
import sasthoseba.com.sasthoseba.fragment.Patient_LoginFragment;
import sasthoseba.com.sasthoseba.fragment.Patient_SignupFragment;


public class Patient_TablayoutLoginSignup extends AppCompatActivity {

    Toolbar toolBar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_tablayout_login_signup);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);

        setSupportActionBar(toolBar);
        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Patient_LoginFragment(), "লগইন");
        adapter.addFragment(new Patient_SignupFragment(), "নিবন্ধন করুন");
        viewPager.setAdapter(adapter);
    }
}
