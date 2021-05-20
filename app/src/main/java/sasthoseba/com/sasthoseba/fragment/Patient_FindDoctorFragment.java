package sasthoseba.com.sasthoseba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import sasthoseba.com.sasthoseba.Patient_NavigationDrawer;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_Child;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_Dental;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_MaternalHealth;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_Medicine;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_MentalHealth;
import sasthoseba.com.sasthoseba.RecyclerDoctorOnline_Orthopedic;
import sasthoseba.com.sasthoseba.R;
import sasthoseba.com.sasthoseba.StartupPage;
import sasthoseba.com.sasthoseba.adapter.GridItemArrayAdapter;
import sasthoseba.com.sasthoseba.model.TypesOfDisease;

public class Patient_FindDoctorFragment extends Fragment {

    GridView grid_view;
    ArrayList<TypesOfDisease> typesOfDiseases;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View view = inflater.inflate(R.layout.patient_fragment_find_doctor, null);
        GridView grid_view = (GridView) view.findViewById(R.id.grid_view);

        typesOfDiseases=new ArrayList<>();
        setData();

        GridItemArrayAdapter adapter = new GridItemArrayAdapter(getContext(), typesOfDiseases);
        grid_view.setAdapter(adapter);

        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = null;
                switch(position)
                {
                    case 0:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_Medicine.class);
                        break;
                    case 1:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_MentalHealth.class);
                        break;
                    case 2:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_Child.class);
                        break;
                    case 3:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_MaternalHealth.class);
                        break;
                    case 4:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_Orthopedic.class);
                        break;
                    case 5:
                        intent =  new Intent( getContext(),RecyclerDoctorOnline_Dental.class);
                        break;
                    case 6:
                        intent =  new Intent( getContext(),Patient_NavigationDrawer.class);
                        break;
                }
                startActivity(intent);
            }
        });

        return view;
    }

    private void setData() {
        TypesOfDisease medicine= new TypesOfDisease(R.drawable.medicine, "মেডিসিন");
        TypesOfDisease mentalhealth= new TypesOfDisease(R.drawable.mentalhealth,"মানসিক সাস্থ্য");
        TypesOfDisease pediatrics= new TypesOfDisease(R.drawable.pediatrics, "শিশুরোগ");
        TypesOfDisease pregnancy= new TypesOfDisease(R.drawable.pregnancy, "গর্ভাবস্থা");
        TypesOfDisease orthopedic= new TypesOfDisease(R.drawable.orthopedic, "অর্থোপেডিক");
        TypesOfDisease dentistry= new TypesOfDisease(R.drawable.dentistry, "দন্তচিকিত্সা");
        TypesOfDisease other= new TypesOfDisease(R.drawable.other, "অন্যান্য / নিশ্চিত নন?");

        typesOfDiseases.add(medicine);
        typesOfDiseases.add(mentalhealth);
        typesOfDiseases.add(pediatrics);
        typesOfDiseases.add(pregnancy);
        typesOfDiseases.add(orthopedic);
        typesOfDiseases.add(dentistry);
        typesOfDiseases.add(other);
    }
}
