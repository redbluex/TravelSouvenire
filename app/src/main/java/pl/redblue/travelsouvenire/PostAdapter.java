package pl.redblue.travelsouvenire;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter {

    private ArrayList<SinglePost> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mPlace;
        public TextView mDescription;
        public TextView mPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPlace =  (TextView)itemView.findViewById(R.id.place_text);
            mDescription=(TextView)itemView.findViewById(R.id.description_text);
            mPhoto=(TextView)itemView.findViewById(R.id.foto_text);

        }
    }

    public PostAdapter(ArrayList<SinglePost> arrayList, RecyclerView recyclerView){
        this.arrayList = arrayList;
        this.recyclerView = recyclerView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_element_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       SinglePost singlePost = arrayList.get(position);
        ((MyViewHolder)holder).mPlace.setText(singlePost.getPlace());
        ((MyViewHolder)holder).mDescription.setText(singlePost.getDescription());
        ((MyViewHolder)holder).mPhoto.setText(singlePost.getPhoto());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
