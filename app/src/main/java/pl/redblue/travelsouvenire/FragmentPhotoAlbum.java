package pl.redblue.travelsouvenire;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.redblue.travelsouvenire.pojo.SinglePlace;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;

public class FragmentPhotoAlbum extends Fragment {
    String[] tablica = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Kot_z_mysz%C4%85.jpg/220px-Kot_z_mysz%C4%85.jpg"
    };



    private List<String> mThumbIds = new ArrayList<String>(Arrays.asList(tablica));


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_album, container, false);
        final GridView gridview = (GridView) view.findViewById(R.id.gridview);


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        io.reactivex.Observable<List<SinglePlace>> observable = myInterfaceToRetro.getPlacess(myIds);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SinglePlace>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SinglePlace> singlePlaces) {
                        for(SinglePlace sp : singlePlaces){
                            mThumbIds.add(sp.getLinkPhoto());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        gridview.setAdapter(new ImageAdapter(getContext(), mThumbIds));

                    }
                });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



    


    }


