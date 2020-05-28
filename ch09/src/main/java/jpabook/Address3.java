package jpabook;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

// 예제 9.7 같은 임베디드 타입을 가지고 있는 회원
@Embeddable
public class Address3 {

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
