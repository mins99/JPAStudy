package jpabook;

import javax.persistence.Embeddable;

// 예제 9.6 임베디드 타입과 연관관계
@Embeddable
public class Zipcode {
    String zip;

    //String plusFour;
}
