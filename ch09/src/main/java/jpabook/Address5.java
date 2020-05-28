package jpabook;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 예제 9.12 값 타입 컬렉션
@Embeddable
public class Address5 {

    @Column
    private String city;

    private String street;

    private String zipcode;

    public Address5() { }

    public Address5(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
