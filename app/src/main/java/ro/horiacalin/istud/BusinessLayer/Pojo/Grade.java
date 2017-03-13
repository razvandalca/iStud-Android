package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by Razvan'S PC on 09.03.2017.
 */

public class Grade {
    private  String value;
    private  int activity;

    public Grade(String value, int activity) {
        this.value = value;
        this.activity = activity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
