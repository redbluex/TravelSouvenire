package pl.redblue.travelsouvenire;

import pl.redblue.travelsouvenire.pojo.UserPersonal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyInterfaceToRetro {

    @POST("adduser")
    Call<UserPersonal> addUser(@Body UserPersonal userPersonal);

    @GET("users/{nickname}")
    Call<UserPersonal> getByNick(@Path("nickname") String nickname);
}
