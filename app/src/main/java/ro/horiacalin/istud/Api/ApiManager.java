package ro.horiacalin.istud.Api;

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

}
