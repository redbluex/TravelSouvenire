package pl.redblue.travelsouvenire;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.redblue.travelsouvenire.pojo.Photos;
import pl.redblue.travelsouvenire.pojo.SinglePlace;
import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.Path;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;


public class AddActivity extends AppCompatActivity {
    EditText editCountry, editCity, editDesc, editSearch;
    ImageView imageView;
    Button buttonAdd;
    Bitmap bitmap;
    private final int IMG_REQUEST = 1;
    String pathImage;
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
        imageView = (ImageView)findViewById(R.id.imageView);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editCity.getText().toString().equals("")
                        && !editCountry.getText().toString().equals("")) {
                    SinglePlace singlePlace = new SinglePlace();
                    singlePlace.setCity(editCity.getText().toString());
                    singlePlace.setCountry(editCountry.getText().toString());
                    singlePlace.setDescription(editDesc.getText().toString());
                    //Toast.makeText(AddActivity.this, pathImage, Toast.LENGTH_SHORT).show();
                    sendToServer(myIds, singlePlace);

                    //uploadPhoto(pathImage);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(AddActivity.this, "Country and City cant be empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode == Activity.RESULT_OK) {
            RealPathUtil realPathUtil = new RealPathUtil();
            Uri selectedImage = data.getData();
            pathImage = realPathUtil.getRealPath(AddActivity.this, selectedImage);
            Toast.makeText(AddActivity.this, pathImage, Toast.LENGTH_SHORT).show();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imageView.setImageBitmap(bitmap);
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

    private void selectImage(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMG_REQUEST);
    }
    public void uploadImage(){

        File file = new File(pathImage);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);
        Call<ResponseBody>call = myInterfaceToRetro.uploadImage(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(AddActivity.this, "Success, file uploaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddActivity.this, "We cant upload path: "+pathImage, Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void uploadPhoto(){
        File fileOri = new File(pathImage);
        String uniqueName = fileOri.getName() +  "1003";
        RequestBody namePh = RequestBody.create(MultipartBody.FORM, uniqueName);
        RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), fileOri);
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", fileOri.getName(), filePart);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/users/"+myIds+"/places/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MyInterfaceToRetro myInterfaceToRetro = retrofit.create(MyInterfaceToRetro.class);



        Call<ResponseBody> call = myInterfaceToRetro.sendPhoto(file, namePh, 2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }






    public void UploadPhotoButton(View view) {
        selectImage();
    }

    public void buttonKliknij(View view) {
        Toast.makeText(AddActivity.this, "Realpath: "+pathImage, Toast.LENGTH_SHORT).show();
       uploadPhoto();
    }
}
