package pl.redblue.travelsouvenire;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import pl.redblue.travelsouvenire.pojo.Photos;
import pl.redblue.travelsouvenire.pojo.SinglePlace;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;
import static pl.redblue.travelsouvenire.RegisterActivity.myIds;

public class PostAdapter extends RecyclerView.Adapter {

    private ArrayList<SinglePlace> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mCity;
        public TextView mCountry;
        public TextView mDescription;
        public ImageView mPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCity =  (TextView)itemView.findViewById(R.id.place_text);
            mDescription=(TextView)itemView.findViewById(R.id.description_text);
            mCountry=(TextView)itemView.findViewById(R.id.foto_text);
            mPhoto = (ImageView)itemView.findViewById(R.id.image_country);
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

       SinglePlace singlePlace = arrayList.get(position);
        ((MyViewHolder)holder).mCity.setText(singlePlace.getCity());
        ((MyViewHolder)holder).mDescription.setText(singlePlace.getDescription());
        ((MyViewHolder)holder).mCountry.setText(singlePlace.getCountry());

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               ((MyViewHolder)holder).mPhoto.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get().load(singlePlace.getLinkPhoto()).into(target);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
