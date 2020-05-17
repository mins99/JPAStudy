package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

// 다대다 단방향 & 양방향 회원 엔티티
//@Entity
//@Table(name = "MEMBER")
public class Member7 {

    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
                joinColumns = @JoinColumn(name = "MEMBER_ID"),
                inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    //private List<Product> products = new ArrayList<Product>();        // 다대다 단방향
    private List<Product2> products = new ArrayList<Product2>();        // 다대다 양방향

    public Member7() { }

    // 다대다 양방향
    public void addProduct(Product2 product) {
        this.products = products;
       products.add(product);
       product.getMembers().add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 다대다 단방향
    /*public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/

    // 다대다 양방향
    public List<Product2> getProducts() {
        return products;
    }

    public void setProducts(List<Product2> products) {
        this.products = products;
    }
}
