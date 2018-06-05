package pl.redblue.travelsouvenire;

import pl.redblue.travelsouvenire.pojo.SinglePlace;
import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}
