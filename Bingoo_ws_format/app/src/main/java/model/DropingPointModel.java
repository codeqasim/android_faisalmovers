package model;

public class DropingPointModel {

    String city1,time1;
    Integer location1;

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public Integer getLocation1() {
        return location1;
    }

    public void setLocation1(Integer location1) {
        this.location1 = location1;
    }

    public DropingPointModel(String city1, String time1, Integer location1) {
        this.city1 = city1;
        this.time1 = time1;
        this.location1 = location1;
    }
}
