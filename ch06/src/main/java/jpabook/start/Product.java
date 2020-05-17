package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 다대다 단방향 상품 엔티티
//@Entity
public class Product {

    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    public Product() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
