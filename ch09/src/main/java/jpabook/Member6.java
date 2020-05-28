package jpabook;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// 예제 9.10 주소 불변 객체
//@Entity
//@Table(name = "MEMBER")
public class Member6 {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    Address4 homeAddress;    // 집 주소

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address4 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address4 homeAddress) {
        this.homeAddress = homeAddress;
    }

}
