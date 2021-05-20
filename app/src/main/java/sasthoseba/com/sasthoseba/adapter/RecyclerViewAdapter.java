package sasthoseba.com.sasthoseba.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import sasthoseba.com.sasthoseba.Doctor_OnlineProfileDetails;
import sasthoseba.com.sasthoseba.Doctor_profile;
import sasthoseba.com.sasthoseba.R;


/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){
        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getImageServerUrl(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        R.drawable.doc,//Before loading server image the default showing image.
                        R.drawable.doc//Error image if requested image dose not found on server.
                )
        );

        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);
        Viewholder.ImageTitleNameView.setText(getDataAdapter1.getImageTitleName());

        //NEW CODE
        Viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       Context context = view.getContext();
                                                       Intent i = new Intent(context, Doctor_OnlineProfileDetails.class);
                                                       String Phone=getDataAdapter1.getDocPhone().toString();
                                                       i.putExtra("DoctorPhone", Phone);
                                                       context.startActivity(i);
                                                   }
                                               }
        );


    }

    @Override
    public int getItemCount() {
        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView ;

        public ViewHolder(View itemView) {

            super(itemView);
            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item) ;
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;
        }
    }
}
