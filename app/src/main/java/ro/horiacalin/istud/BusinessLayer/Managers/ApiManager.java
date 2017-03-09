package ro.horiacalin.istud.BusinessLayer.Managers;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.horiacalin.istud.Api.iStudAPI;
import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.R;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ApiManager {
    private static ApiManager INSTANCE;
    private Retrofit retrofit;
    private iStudAPI service;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiManager();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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


    public void getCourses(int userID, final Context context, final CallbackDefaultNetwork callbackDefaultNetwork){
        Call call =service.getCourses(userID);
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

}
