package jpabook;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// 예제 9.7 같은 임베디드 타입을 가지고 있는 회원
//@Entity
//@Table(name = "MEMBER")
public class Member5 {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    Address3 homeAddress;    // 집 주소

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE"))
    })
    Address3 companyAddress;    // 회사 주소

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

    public Address3 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address3 homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address3 getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address3 companyAddress) {
        this.companyAddress = companyAddress;
    }
}
