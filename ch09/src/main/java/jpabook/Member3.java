package jpabook;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

// 예제 9.3 값 타입 적용 회원 엔티티
//@Entity
//@Table(name = "MEMBER")
public class Member3 {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    Period workPeriod;  // 근무 기간

    @Embedded
    Address homeAddress;    // 집 주소

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
}
