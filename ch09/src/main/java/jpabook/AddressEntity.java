package jpabook;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// 예제 9.17 값 타입 컬렉션 대신에 일대다 관계 사용
@Entity
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    Address6 address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address6 getAddress() {
        return address;
    }

    public void setAddress(Address6 address) {
        this.address = address;
    }
}
