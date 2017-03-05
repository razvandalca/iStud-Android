package ro.horiacalin.istud.Api;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public interface iStudAPI  {

    @POST("user/login")
    Call<User> login(@Path("email") String email,@Path("password") String pass);

}
