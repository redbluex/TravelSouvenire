package pl.redblue.travelsouvenire;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.redblue.travelsouvenire.pojo.SinglePlace;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;

public class NewPostsFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_newposts, container, false);
        ArrayList<SinglePlace> posts = new ArrayList<>();
        RecyclerView rec = (RecyclerView) rootView.findViewById(R.id.newpostsRecycle);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        connectWithWS(posts, rec);

        return rootView;
    }

    private void connectWithWS(final ArrayList<SinglePlace> arrayList, final RecyclerView recyclerView){


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<List<SinglePlace>> call = myInterfaceToRetro.getPlaces(myIds);
        call.enqueue(new Callback<List<SinglePlace>>() {
            @Override
            public void onResponse(Call<List<SinglePlace>> call, Response<List<SinglePlace>> response) {
                /* SinglePlace singlePlace = new SinglePlace();
                singlePlace.setCity("Warsaw");
                singlePlace.setCountry("Poland");
                singlePlace.setDescription("...");
                arrayList.add(singlePlace);
                recyclerView.setAdapter(new PostAdapter(arrayList,recyclerView));
                */


                for(int i=0;i<response.body().size();i++){
                    if(response.body().get(i)!=null){
                        arrayList.add(response.body().get(i));
                    }
                }
                recyclerView.setAdapter(new PostAdapter(arrayList,recyclerView));
            }

            @Override
            public void onFailure(Call<List<SinglePlace>> call, Throwable t) {

            }
        });

    }
}
