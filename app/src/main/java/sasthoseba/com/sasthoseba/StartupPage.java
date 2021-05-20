package sasthoseba.com.sasthoseba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);
    }

    public void iamapatient(View view) {
        Intent intent = new Intent(StartupPage.this, Patient_TablayoutLoginSignup.class);
        startActivity(intent);
    }

    public void iamadoctor(View view) {
        Intent intent = new Intent(StartupPage.this, Doctor_TabLayoutLoginSignup.class);
        startActivity(intent);
    }
}
