package ro.horiacalin.istud.BusinessLayer.Managers;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.horiacalin.istud.Api.iStudAPI;
import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.BusinessLayer.Pojo.Scheduale;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ApiManager {
    private final String TAG = "API";
    private static ApiManager INSTANCE;
    private Retrofit retrofit;
    private iStudAPI service;

    private ApiManager() {
        init();
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiManager();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void init() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        service = retrofit.create(iStudAPI.class);
    }


    public void login(String email, String password, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork) {


        Call call = service.login(email, password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //404 user not found
                //403 forbiden not ok credentials
                //200 all ok 
                switch (response.code()) {
                    case 200:
                        response.body();
                        User u = (User) response.body();
                        callbackDefaultNetwork.success(u);
                        sendTokenToServer(u, FirebaseInstanceId.getInstance().getToken(), callbackDefaultNetwork);
                        break;
                    case 404:
                        callbackDefaultNetwork.fail(context.getString(R.string.login_error_404));
                        break;
                    case 403:
                        callbackDefaultNetwork.fail(context.getString(R.string.login_error_403));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callbackDefaultNetwork.fail(context.getString(R.string.login_error_fatal));
            }
        });
    }


    public void sendTokenToServer(final User user, final String token, final CallbackDefaultNetwork callbackDefaultNetwork) {


        Call call = service.sendTokenToServer(user.getId(), token);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        Log.e(TAG, "onResponse: TOKEN OK " + token);
                        user.setToken(token);
                        break;
                    case 404:
                        Log.e(TAG, response.message());
                        break;
                    case 403:
                        Log.e(TAG, response.message());
                        break;
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void resetPassword(final User user, final String oldPass, String newPass, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork) {


        Call call = service.resetPassword(user.getId(), newPass, oldPass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        Log.e(TAG, "Password Changed Success");
                        callbackDefaultNetwork.success(context.getString(R.string.resetrae_pass_ok_200));
                        break;
                    case 404:
                        callbackDefaultNetwork.fail(context.getString(R.string.reset_user_nu_exista_404));
                        break;
                    case 403:
                        callbackDefaultNetwork.fail(context.getString(R.string.parola_curenta_not_ok_403));
                        break;
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }


    public void getCourses(int userID, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork) {
        Call call = service.getCourses(userID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        response.body();
                        List<Materie> u = (List<Materie>) response.body();
                        callbackDefaultNetwork.success(u);
                        break;

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callbackDefaultNetwork.fail(context.getString(R.string.login_error_fatal));

            }
        });
    }

    public void getCourseDetails(int userID, int courseID, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork) {
        Call call = service.getCourseDetails(userID, courseID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        response.body();
                        Materie u = (Materie) response.body();
                        callbackDefaultNetwork.success(u);
                        break;

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callbackDefaultNetwork.fail(context.getString(R.string.login_error_fatal));

            }
        });
    }

    public void getScheduale(int userID, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork) {
        Call call = service.getScheduale(userID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        response.body();
                        List<Scheduale> u = (List<Scheduale>) response.body();
                        callbackDefaultNetwork.success(u);
                        break;
                    default:
                        callbackDefaultNetwork.fail("Error");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callbackDefaultNetwork.fail(context.getString(R.string.login_error_fatal));

            }
        });
    }

}
