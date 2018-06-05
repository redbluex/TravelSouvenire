package pl.redblue.travelsouvenire;

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

public class RegisterActivity extends AppCompatActivity {

    public static Integer myIds;
    EditText editLogin, editPassword;
    Button button;
    String nickCorrect, passwordCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickCorrect = editLogin.getText().toString();
                passwordCorrect = editPassword.getText().toString();
                retroConnection(nickCorrect);
            }
        });



    }

    private void retroConnection(String nickname){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<UserPersonal> call = myInterfaceToRetro.getByNick(nickname);
        call.enqueue(new Callback<UserPersonal>() {
            @Override
            public void onResponse(Call<UserPersonal> call, Response<UserPersonal> response) {

                if(passwordCorrect.equals(response.body().getPassword())){
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    myIds = response.body().getId();
                    startActivity(i);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Wrong password!" , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserPersonal> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Wrong login!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToRegisterForm(View view) {
        Intent intent = new Intent(RegisterActivity.this, NewAccount.class);
        startActivity(intent);
    }
}
