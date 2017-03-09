package ro.horiacalin.istud.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
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

}
