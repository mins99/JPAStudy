package jpabook;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

// 예제 9.6 임베디드 타입과 연관관계
//@Entity
//@Table(name = "MEMBER")
public class Member4 {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    Period2 workPeriod;  // 근무 기간

    @Embedded
    Address2 homeAddress;    // 집 주소

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

    public Period2 getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period2 workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address2 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address2 homeAddress) {
        this.homeAddress = homeAddress;
    }
}
