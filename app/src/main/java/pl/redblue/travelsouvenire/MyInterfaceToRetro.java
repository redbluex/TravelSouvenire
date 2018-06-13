package pl.redblue.travelsouvenire;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pl.redblue.travelsouvenire.pojo.Photos;
import pl.redblue.travelsouvenire.pojo.SinglePlace;
import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MyInterfaceToRetro {

    @POST("users")
    Call<UserPersonal> addUser(@Body UserPersonal userPersonal);

    @GET("users/nick/{nickname}")
    Call<UserPersonal> getByNick(@Path("nickname") String nickname);

    @GET("/users/{id}/places")
    Call<SinglePlace> getPlaceById(@Path("id") Integer id);

    @POST("/users/{userId}/places")
    Call<SinglePlace> addPlace(@Path("userId") Integer userId, @Body SinglePlace singlePlace);

    @GET("/users/{id}")
    Call<UserPersonal> getById(@Path("id") Integer id);

    @GET("/users/{id}/places")
    Call<List<SinglePlace>> getPlaces(@Path("id") Integer id);

    @GET("/users/{id}/places")
    Observable<List<SinglePlace>> getPlacess(@Path("id") Integer id);

    @Multipart
    @POST("{id}/upload")
    Call<ResponseBody> sendPhoto(
            @Part MultipartBody.Part file,
            @Part("namePh") RequestBody namePh,
            @Path("id") Integer id);

    @Multipart
    @POST("image")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);
}
