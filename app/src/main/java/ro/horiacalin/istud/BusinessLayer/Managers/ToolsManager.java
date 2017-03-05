package ro.horiacalin.istud.BusinessLayer.Managers;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ToolsManager {


    private static ToolsManager INSTANCE;

    private ToolsManager() {
    }

    public static ToolsManager getInstance() {
        if (INSTANCE==null){
            INSTANCE=new ToolsManager();
            return INSTANCE;
        }
        return INSTANCE;
    }



}
