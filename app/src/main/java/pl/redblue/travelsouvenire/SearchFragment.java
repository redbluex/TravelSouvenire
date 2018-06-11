package pl.redblue.travelsouvenire;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;


public class SearchFragment extends Fragment {
    Button searchButton;
    EditText editSearch;
    static Integer searchId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchButton = (Button) view.findViewById(R.id.button_find);
        editSearch = (EditText) view.findViewById(R.id.editSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = editSearch.getText().toString();
                ConnectWithWS(nick);

            }
        });
    }

    private void ConnectWithWS(String nick){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<UserPersonal> call = myInterfaceToRetro.getByNick(nick);
        call.enqueue(new Callback<UserPersonal>() {
            @Override
            public void onResponse(Call<UserPersonal> call, Response<UserPersonal> response) {
                ProfileActivity profileActivity = new ProfileActivity();
                Integer idProfile = response.body().getId();
                searchId = idProfile;

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentSearchList, profileActivity);
                fragmentTransaction.commit();

            }

            @Override
            public void onFailure(Call<UserPersonal> call, Throwable t) {

            }
        });

    }
}
