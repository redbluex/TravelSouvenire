package pl.redblue.travelsouvenire;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.redblue.travelsouvenire.pojo.SinglePlace;

public class PostAdapter extends RecyclerView.Adapter {

    private ArrayList<SinglePlace> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mCity;
        public TextView mCountry;
        public TextView mDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCity =  (TextView)itemView.findViewById(R.id.place_text);
            mDescription=(TextView)itemView.findViewById(R.id.description_text);
            mCountry=(TextView)itemView.findViewById(R.id.foto_text);

        }
    }



    public PostAdapter(ArrayList<SinglePlace> arrayList, RecyclerView recyclerView){
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
       SinglePlace singlePlace = arrayList.get(position);
        ((MyViewHolder)holder).mCity.setText(singlePlace.getCity());
        ((MyViewHolder)holder).mDescription.setText(singlePlace.getDescription());
        ((MyViewHolder)holder).mCountry.setText(singlePlace.getCountry());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
