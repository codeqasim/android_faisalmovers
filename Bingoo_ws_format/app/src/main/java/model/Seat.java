package model;

public class Seat {

    String id ,no,price;

    public Seat() {
    }

    public Seat(String id, String no, String price) {
        this.id = id;
        this.no = no;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
