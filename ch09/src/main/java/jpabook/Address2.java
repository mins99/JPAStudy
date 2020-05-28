package jpabook;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

// 예제 9.6 임베디드 타입과 연관관계
@Embeddable
public class Address2 {

    private String city;

    private String street;

    private String state;

    @Embedded
    private Zipcode zipcode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }
}
