package pl.redblue.travelsouvenire;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;

public class FragmentPersonalProfile extends Fragment {

    TextView textNick, textDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_profile, container, false);
        textNick = (TextView)v.findViewById(R.id.textNickNameProfile);
        textDescription = (TextView)v.findViewById(R.id.textDescriptionProfile);
        connectWithWS();
        return v;
    }

    private void connectWithWS(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<UserPersonal>call = myInterfaceToRetro.getById(myIds);
        call.enqueue(new Callback<UserPersonal>() {
            @Override
            public void onResponse(Call<UserPersonal> call, Response<UserPersonal> response) {
                    textNick.setText(response.body().getNickname());
                    textDescription.setText(response.body().getDescription());

            }

            @Override
            public void onFailure(Call<UserPersonal> call, Throwable t) {

            }
        });
    }

}
