package jpabook.start;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//@IdClass(MemberProductId.class)
public class MemberProduct {

    @Id @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member8 member;      // MemberProductId.member와 연결

    @Id @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product3 product;    // MemberProductId.product와 연결

    private int orderAmount;

    public Member8 getMember() {
        return member;
    }

    public void setMember(Member8 member) {
        this.member = member;
    }

    public Product3 getProduct() {
        return product;
    }

    public void setProduct(Product3 product) {
        this.product = product;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
}
