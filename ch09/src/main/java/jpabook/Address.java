package jpabook;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 예제 9.3 값 타입 적용 회원 엔티티
@Embeddable
public class Address {

    @Column(name="city")
    private String city;

    private String street;

    private String zipcode;

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
