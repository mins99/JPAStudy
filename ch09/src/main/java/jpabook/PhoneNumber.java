package jpabook;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

// 예제 9.6 임베디드 타입과 연관관계
@Embeddable
public class PhoneNumber {

    String areaCode;
    String localNumber;

    @ManyToOne
    PhoneServiceProvider provider;      // 엔티티 참조
}
