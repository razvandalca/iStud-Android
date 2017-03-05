package ro.horiacalin.istud.BusinessLayer.Managers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.horiacalin.istud.Api.iStudAPI;
import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Constants;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ApiManager {
    private static ApiManager INSTANCE;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (INSTANCE==null){
            INSTANCE=new ApiManager();
            return INSTANCE;
        }
        return INSTANCE;
    }



    public void login(String email, String password, final CallbackDefaultNetwork callbackDefaultNetwork){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iStudAPI service = retrofit.create(iStudAPI.class);

        Call call= service.login(email,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                response.body();
                User u = (User) response.body();
                callbackDefaultNetwork.success(u);

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
