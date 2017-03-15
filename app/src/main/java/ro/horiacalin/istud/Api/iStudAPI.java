package ro.horiacalin.istud.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.BusinessLayer.Pojo.Scheduale;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public interface iStudAPI  {


    @FormUrlEncoded
    @POST("user/login")
    Call<User> login(@Field("email") String email, @Field("password") String pass);


    @GET("course/read")
    Call<List<Materie>> getCourses(@Query("userid") int userID);

    @GET("course/read")
    Call<Materie> getCourseDetails(@Query("userid") int userID,@Query("courseid") int courseId);

    @GET("schedule/read")
    Call<List<Scheduale>> getScheduale(@Query("userid") int userID);

    @FormUrlEncoded
    @POST("user/token")
    Call<Void> sendTokenToServer(@Field("userid") int userID,@Field("token") String token);


    @FormUrlEncoded
    @POST("user/reset_pass")
    Call<Void> resetPassword(@Field("userid") int userID,@Field("new_pass") String newPass,@Field("old_pass") String oldPass);






}
