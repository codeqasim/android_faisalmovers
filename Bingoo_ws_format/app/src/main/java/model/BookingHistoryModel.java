package model;

import android.widget.LinearLayout;

public class BookingHistoryModel {

    Integer star;
    String rate;

    public BookingHistoryModel(Integer star, String rate) {
        this.star = star;
        this.rate = rate;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }



}
