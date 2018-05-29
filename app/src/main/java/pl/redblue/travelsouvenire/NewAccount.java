package pl.redblue.travelsouvenire;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewAccount extends AppCompatActivity {

    Button acceptbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        acceptbutton = (Button) findViewById(R.id.buttonAcceptNA);
        final EditText editNick = findViewById(R.id.editNick);
        final EditText editPassword = findViewById(R.id.editPassword2);


        acceptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPersonal userPersonal = new UserPersonal();
                userPersonal.setNickname(editNick.getText().toString());
                userPersonal.setPassword(editPassword.getText().toString());
                userPersonal.setDescription("...");
                SendRequestNetwork(userPersonal);
                Intent intent = new Intent(NewAccount.this, RegisterActivity.class);
                startActivity(intent);
            }

        });
    }

    private void SendRequestNetwork(UserPersonal userPersonal){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<UserPersonal> call = myInterfaceToRetro.addUser(userPersonal);

        call.enqueue(new Callback<UserPersonal>() {
            @Override
            public void onResponse(Call<UserPersonal> call, Response<UserPersonal> response) {
                //Toast.makeText(NewAccount.this, "All good! ID "+ response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserPersonal> call, Throwable t) {
                //Toast.makeText(NewAccount.this, "something wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
