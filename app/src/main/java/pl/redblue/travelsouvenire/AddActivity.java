package pl.redblue.travelsouvenire;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;

import pl.redblue.travelsouvenire.pojo.SinglePlace;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;


public class AddActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 3;
    EditText editCountry, editCity, editDesc, editSearch;
    Button buttonAdd, buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent x = getIntent();
        final Integer userId = x.getIntExtra("userId", 0);
        editCity = (EditText)findViewById(R.id.editCity);
        editCountry = (EditText)findViewById(R.id.editCountry);
        editDesc= (EditText)findViewById(R.id.editDesc);
        editSearch = (EditText)findViewById(R.id.editSearch);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonSearch = (Button)findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchPhrase = editSearch.getText().toString();
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinglePlace singlePlace = new SinglePlace();
                singlePlace.setCity(editCity.getText().toString());
                singlePlace.setCountry(editCountry.getText().toString());
                singlePlace.setDescription(editDesc.getText().toString());
                sendToServer(myIds, singlePlace);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToServer(Integer userId, SinglePlace singlePlace){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<SinglePlace>call = myInterfaceToRetro.addPlace(userId, singlePlace);
        call.enqueue(new Callback<SinglePlace>() {
            @Override
            public void onResponse(Call<SinglePlace> call, Response<SinglePlace> response) {

            }

            @Override
            public void onFailure(Call<SinglePlace> call, Throwable t) {

            }
        });
    }




    public void UploadPhotoButton(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
}
