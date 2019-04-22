package model;

public class SeatData {

    String  seat_id;
    String   seat_name;
    String seat_type;
    String seat_status;
    String row_name;
    String row_index;
    String col_index;
    String row_size ;
    String col_size ;
    String isAvailableForBooking;
    String areaCategoryCod ;
    String Seat_No;
    String fare;
    String gender;


    public SeatData() {
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getSeat_name() {
        return seat_name;
    }

    public void setSeat_name(String seat_name) {
        this.seat_name = seat_name;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    public String getRow_name() {
        return row_name;
    }

    public void setRow_name(String row_name) {
        this.row_name = row_name;
    }

    public String getRow_index() {
        return row_index;
    }

    public void setRow_index(String row_index) {
        this.row_index = row_index;
    }

    public String getCol_index() {
        return col_index;
    }

    public void setCol_index(String col_index) {
        this.col_index = col_index;
    }

    public String getRow_size() {
        return row_size;
    }

    public void setRow_size(String row_size) {
        this.row_size = row_size;
    }

    public String getCol_size() {
        return col_size;
    }

    public void setCol_size(String col_size) {
        this.col_size = col_size;
    }

    public String getIsAvailableForBooking() {
        return isAvailableForBooking;
    }

    public void setIsAvailableForBooking(String isAvailableForBooking) {
        this.isAvailableForBooking = isAvailableForBooking;
    }

    public String getAreaCategoryCod() {
        return areaCategoryCod;
    }

    public void setAreaCategoryCod(String areaCategoryCod) {
        this.areaCategoryCod = areaCategoryCod;
    }

    public String getSeat_No() {
        return Seat_No;
    }

    public void setSeat_No(String seat_No) {
        Seat_No = seat_No;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
