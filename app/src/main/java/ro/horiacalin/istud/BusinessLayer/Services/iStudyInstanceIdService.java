package ro.horiacalin.istud.BusinessLayer.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Managers.ApiManager;
import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;

/**
 * Created by Razvan'S PC on 09.03.2017.
 */

public class iStudyInstanceIdService extends FirebaseInstanceIdService {
    private String TAG = "iStudyInstanceIdService";

    @Override
    public void onTokenRefresh() {
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();


        try {

            ApiManager.getInstance().sendTokenToServer(ToolsManager.getInstance().getUser(getApplicationContext()), refreshedToken, new CallbackDefaultNetwork() {
                @Override
                public void success(Object object) {
                    ToolsManager.getInstance().getUser(getApplicationContext()).setToken(refreshedToken);
                    Log.e(TAG, "success Added token on server ");
                }

                @Override
                public void fail(String message) {

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "onTokenRefresh: User is not loged in yet.");
        }
    }


}
