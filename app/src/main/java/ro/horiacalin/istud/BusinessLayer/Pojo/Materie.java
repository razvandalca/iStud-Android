package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class Materie {
    private String mName;
    private int numarCredite;
    private String numeTitular;

    public Materie(String name) {
        mName = name;

    }

    public String getName() {
        return mName;
    }

    public int getNumarCredite(){
        return numarCredite;
    }

    public String getNumeTitular(){
        return numeTitular;
    }

    private static int lastMaterieID = 0;


}