package sasthoseba.com.sasthoseba;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import sasthoseba.com.sasthoseba.adapter.ViewPagerAdapter;
import sasthoseba.com.sasthoseba.fragment.Patient_BasicInfoFragment;
import sasthoseba.com.sasthoseba.fragment.Patient_MedicalHistoryFragment;

public class Patient_TablayoutProfile extends AppCompatActivity {

    Toolbar toolBar3;
    TabLayout tabLayout3;
    ViewPager viewPager3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_tablayout_profile);

        tabLayout3 = (TabLayout)findViewById(R.id.tabLayout3);
        viewPager3=(ViewPager) findViewById(R.id.viewPager3);

        setSupportActionBar(toolBar3);
        setDataToViewPager();
        tabLayout3.setupWithViewPager(viewPager3);
    }


    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Patient_BasicInfoFragment(), "মৌলিক তথ্য");
        adapter.addFragment(new Patient_MedicalHistoryFragment(), "চিকিৎসা ইতিহাস");
        viewPager3.setAdapter(adapter);
    }
}
