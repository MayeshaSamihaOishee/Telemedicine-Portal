package sasthoseba.com.sasthoseba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import sasthoseba.com.sasthoseba.R;
import sasthoseba.com.sasthoseba.model.TypesOfDisease;


public class GridItemArrayAdapter extends BaseAdapter {

    Context c;
    ArrayList<TypesOfDisease> arrayList;

    public GridItemArrayAdapter(Context c, ArrayList<TypesOfDisease> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    public GridItemArrayAdapter(Context context) {
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_view= inflater.inflate(R.layout.patient_finddoctor_grid_items, null);

        ImageView iVtod=custom_view.findViewById(R.id.imageViewTypesOfDisease);
        TextView tVtod=custom_view.findViewById(R.id.textViewTypesOfDisease);

        iVtod.setImageResource(arrayList.get(position).getImageID());
        tVtod.setText(arrayList.get(position).getName());

        return custom_view;
    }
}
