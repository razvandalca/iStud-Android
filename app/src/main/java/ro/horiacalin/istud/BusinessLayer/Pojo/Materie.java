package ro.horiacalin.istud.BusinessLayer.Pojo;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by horiaacalin on 05/03/2017.
 */

@SuppressWarnings("serial")
public class Materie implements Serializable{
    private String mName;
    private int mNumarCredite;
    private String mNumeTitular;

    public Materie(String name){

        mName = name;
    }

    public Materie(String name, int numarCredite, String numeTitular) {
        mName = name;
        mNumarCredite = numarCredite;
        mNumeTitular = numeTitular;
    }

    public String getName() {
        return mName;
    }

    public int getNumarCredite(){
        return mNumarCredite;
    }

    public String getNumeTitular(){
        return mNumeTitular;
    }

    private static int lastMaterieID = 0;


}