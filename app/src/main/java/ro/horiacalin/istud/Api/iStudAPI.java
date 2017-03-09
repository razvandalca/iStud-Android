package ro.horiacalin.istud.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public interface iStudAPI  {


    @FormUrlEncoded
    @POST("user/login")
    Call<User> login(@Field("email") String email, @Field("password") String pass);

}
