package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by Razvan'S PC on 09.03.2017.
 */

public class Grade {
    private  double value;
    private  int activity;

    public Grade(double value, int activity) {
        this.value = value;
        this.activity = activity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
