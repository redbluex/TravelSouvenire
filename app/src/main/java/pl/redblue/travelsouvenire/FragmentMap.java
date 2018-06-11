package pl.redblue.travelsouvenire;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.redblue.travelsouvenire.pojo.SinglePlace;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;
import static pl.redblue.travelsouvenire.SearchFragment.searchId;

public class FragmentMap extends Fragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;


    public FragmentMap(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.mapView);
        if(mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        if(searchId!=null) {
            connectionWithWS(searchId, googleMap);
        }
        else
            connectionWithWS(myIds, googleMap);

    }

    public LatLng getLocationFromAddress( String strAddress)
    {
        Geocoder coder= new Geocoder(getContext());
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }

    public void connectionWithWS(Integer id, GoogleMap googleM){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        final Observable<List<SinglePlace>> observable = myInterfaceToRetro.getPlacess(id);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SinglePlace>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SinglePlace> singlePlaces) {
                        for(SinglePlace s : singlePlaces){
                            String city = s.getCity();
                            LatLng adress = getLocationFromAddress(city);
                            if(adress!=null)
                                mGoogleMap.addMarker(new MarkerOptions().position(adress).title(s.getCity()+" "
                                        + s.getCountry()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        /*
        Call<List<SinglePlace>>call = myInterfaceToRetro.getPlaces(id);
        call.enqueue(new Callback<List<SinglePlace>>() {
            @Override
            public void onResponse(Call<List<SinglePlace>> call, Response<List<SinglePlace>> response) {
                for(int i=0; i<response.body().size(); i++) {
                    String city = response.body().get(i).getCity();
                    LatLng adress = getLocationFromAddress(city);
                    if(adress!=null)
                    mGoogleMap.addMarker(new MarkerOptions().position(adress).title(response.body().get(i).getCity()+" "
                    + response.body().get(i).getCountry()));
                }
            }

            @Override
            public void onFailure(Call<List<SinglePlace>> call, Throwable t) {

            }
        }); */
    }
}