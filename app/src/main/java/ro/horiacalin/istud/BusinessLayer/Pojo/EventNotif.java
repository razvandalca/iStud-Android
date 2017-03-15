package ro.horiacalin.istud.BusinessLayer.Pojo;

import java.io.Serializable;

/**
 * Created by Razvan'S PC on 15.03.2017.
 */

public class EventNotif implements Serializable {
    private String time;
    private String room;
    private String course;
    private String title;
    private String message;
    private String date;

    public EventNotif(String time, String room, String course, String title, String message, String date) {
        this.time = time;
        this.room = room;
        this.course = course;
        this.title = title;
        this.message = message;
        this.date = date;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
