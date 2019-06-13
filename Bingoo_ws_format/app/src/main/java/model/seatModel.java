package model;

import android.os.Parcel;
import android.os.Parcelable;

public class seatModel implements Parcelable {

    private String seat_on;
    private String seat_id;
    private String seat_status;
    private String Gender;
    String seat_name;
    String seat_type;
    String row_name;
    String row_index;
    String col_index;
    String row_size ;
    String col_size ;
    String isAvailableForBooking;
    String areaCategoryCod ;
    String Seat_No;
    String fare;

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

    public static Creator<seatModel> getCREATOR() {
        return CREATOR;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public seatModel(){

    }
    protected seatModel(Parcel in) {
        seat_on = in.readString();
        seat_id = in.readString();
        seat_status = in.readString();
        seat_fare = in.readString();
        Gender = in.readString();
    }

    public static final Creator<seatModel> CREATOR = new Creator<seatModel>() {
        @Override
        public seatModel createFromParcel(Parcel in) {
            return new seatModel(in);
        }

        @Override
        public seatModel[] newArray(int size) {
            return new seatModel[size];
        }
    };

    public String getSeat_on() {
        return seat_on;
    }

    public void setSeat_on(String seat_on) {
        this.seat_on = seat_on;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    public String getSeat_fare() {
        return seat_fare;
    }

    public void setSeat_fare(String seat_fare) {
        this.seat_fare = seat_fare;
    }

    private String seat_fare;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seat_on);
        dest.writeString(seat_id);
        dest.writeString(seat_status);
        dest.writeString(seat_fare);
        dest.writeString(Gender);
    }
}
