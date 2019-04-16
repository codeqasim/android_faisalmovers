package model;

public class Cities {

    String id ;
    String name ;
    String city_abbr ;
    String country ;
    String province ;
    String active ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity_abbr() {
        return city_abbr;
    }

    public void setCity_abbr(String city_abbr) {
        this.city_abbr = city_abbr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }



    public Cities(String id, String name, String city_abbr, String country, String province, String active) {
        this.id = id;
        this.name = name;
        this.city_abbr = city_abbr;
        this.country = country;
        this.province = province;
        this.active = active;
    }
}
