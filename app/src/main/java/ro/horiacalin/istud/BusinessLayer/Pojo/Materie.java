package ro.horiacalin.istud.BusinessLayer.Pojo;

import java.io.Serializable;

/**
 * Created by horiaacalin on 05/03/2017.
 */

@SuppressWarnings("serial")
public class Materie implements Serializable{
    private int id;
    private String name;
    private int numarCredite;
    private String numeTitular;

    public Materie(String name){

        this.name = name;
    }

    public Materie(String name, int numarCredite, String numeTitular) {
        this.name = name;
        this.numarCredite = numarCredite;
        this.numeTitular = numeTitular;
    }

    public String getName() {
        return name;
    }

    public int getNumarCredite(){
        return numarCredite;
    }

    public String getNumeTitular(){
        return numeTitular;
    }

    private static int lastMaterieID = 0;


}