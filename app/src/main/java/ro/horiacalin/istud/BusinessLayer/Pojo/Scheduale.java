package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by Razvan'S PC on 12.03.2017.
 */

public class Scheduale {

    private String time;
    private String room;
    private String startDate;
    private String name;
    private int frequency;
    private int id;
    private int type;


    public Scheduale(String time, String room, String startDate, String name, int frequency, int id, int type) {
        this.time = time;
        this.room = room;
        this.startDate = startDate;
        this.name = name;
        this.frequency = frequency;
        this.id = id;
        this.type = type;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
