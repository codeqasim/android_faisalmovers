package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bingobus7Model implements Serializable {

    String opId ;
    String  operator;
    String logo ;
    String fromCity ,toCity,departureTime,querydepartureTime,arrivalTime,price,busType,stops,seatsLeft,scheduleID;

    ArrayList<String > Amenities_array_list = new ArrayList<>();
    ArrayList<String > Amenities_array_listimage = new ArrayList<>();
    ArrayList<String > boardingPoints = new ArrayList<>();


    public ArrayList<String> getAmenities_array_listimage() {
        return Amenities_array_listimage;
    }

    public void setAmenities_array_listimage(ArrayList<String> amenities_array_listimage) {
        Amenities_array_listimage = amenities_array_listimage;
    }

    public Bingobus7Model() {
    }



    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getQuerydepartureTime() {
        return querydepartureTime;
    }

    public void setQuerydepartureTime(String querydepartureTime) {
        this.querydepartureTime = querydepartureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(String seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }


    public ArrayList<String> getAmenities_array_list() {
        return Amenities_array_list;
    }

    public void setAmenities_array_list(ArrayList<String> amenities_array_list) {
        Amenities_array_list = amenities_array_list;
    }
    public ArrayList<String> getBoardingPoints() {
        return boardingPoints;
    }

    public void setBoardingPoints(ArrayList<String> boardingPoints) {
        this.boardingPoints = boardingPoints;
    }
}
