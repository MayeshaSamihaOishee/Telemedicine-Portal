package sasthoseba.com.sasthoseba.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import sasthoseba.com.sasthoseba.Common_Question_Details;
import sasthoseba.com.sasthoseba.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class Common_HomepageSliderFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList, QuestionArrayList, AnswerArrayList, TypeArrayList, DateArrayList;
    public static final String GET_URL= "http://ehealth.mdtauhidul.me/getData_QuestionAnswer.php";
    private SliderLayout mDemoSlider;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View v = inflater.inflate(R.layout.common_fragment_homepage_slider, null);

        arrayList=new ArrayList<>();
        QuestionArrayList = new ArrayList<>();
        AnswerArrayList = new ArrayList<>();
        TypeArrayList = new ArrayList<>();
        DateArrayList = new ArrayList<>();


        listView=v.findViewById(R.id.listquesans);
        adapter=new ArrayAdapter<String>(getActivity(), R.layout.common_homepage_listview_color, arrayList);

        listView.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int length=response.length();

                for (int i=0; i<length; i++){
                    try {
                        String Id=response.getJSONObject(i).getString("Id");
                        String QuestionType=response.getJSONObject(i).getString("QuestionType");
                        String QuestionDescription=response.getJSONObject(i).getString("QuestionDescription");
                        String Answer=response.getJSONObject(i).getString("Answer");
                        String Date=response.getJSONObject(i).getString("Date");
                        String Time=response.getJSONObject(i).getString("Time");
                        String PatientId=response.getJSONObject(i).getString("PatientId");
                        arrayList.add("প্রশ্ন: " + QuestionDescription);

                        QuestionArrayList.add(QuestionDescription);
                        AnswerArrayList.add(Answer);
                        TypeArrayList.add(QuestionType);
                        DateArrayList.add(Date);

                        adapter.notifyDataSetChanged();
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), Common_Question_Details.class);

                String QHolder=QuestionArrayList.get(position);
                i.putExtra("Question", QHolder);

                String AHolder=AnswerArrayList.get(position);
                i.putExtra("Answer", AHolder);

                String THolder=TypeArrayList.get(position);
                i.putExtra("Type", THolder);

                String DHolder=DateArrayList.get(position);
                i.putExtra("Date", DHolder);

                startActivity(i);
            }
        });




        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("কলা এর উপকারিতা", "https://blog.ayusharogyam.com/wp-content/uploads/2018/01/Ayurveda-Health-Tips-Ayush-Arogyam.jpg");
        url_maps.put("কিশমিশ এর উপকারিতা", "https://blog.ayusharogyam.com/wp-content/uploads/2018/01/Ayurveda-Health-Tips-for-blood-purification-Ayush-Arogyam.jpg");
        url_maps.put("মধু এর উপকারিতা", "https://blog.ayusharogyam.com/wp-content/uploads/2018/01/Ayurveda-Health-Tips-for-stomach-pain-Ayush-Arogyam.jpg");
        url_maps.put("আমলা এর উপকারিতা", "https://blog.ayusharogyam.com/wp-content/uploads/2018/01/Ayurveda-Health-Tips-for-Urinary-problems-Ayush-Arogyam.jpg");
        url_maps.put("অশ্বগন্ধা এর উপকারিতা", "https://blog.ayusharogyam.com/wp-content/uploads/2018/01/Ayurveda-Health-Tips-for-fertility-problems-Ayush-Arogyam.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);
        return v;
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}