package model;

import android.widget.ImageView;

public class BoardingPointModel {
    Integer location;
    String city,time;


    public BoardingPointModel(Integer location, String city, String time) {
        this.location = location;
        this.city = city;
        this.time = time;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
