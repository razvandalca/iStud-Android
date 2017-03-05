package ro.horiacalin.istud;

import java.util.ArrayList;

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

    public static ArrayList<Materie> createContactsList(int numarMaterii) {
        ArrayList<Materie> materii = new ArrayList<Materie>();

        for (int i = 1; i <= numarMaterii; i++) {
            materii.add(new Materie("Materie: " + ++lastMaterieID));
        }

        return materii;
    }
}