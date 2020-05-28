package jpabook;

import javax.persistence.Embeddable;

// 예제 9.10 주소 불변 객체
@Embeddable
public class Address4 {

    private String city;

    protected Address4() { }

    public Address4(String city) {
        this.city = city;
    }

    // Getter는 노출하고 Setter는 만들지 않음
    public String getCity() {
        return city;
    }

}
