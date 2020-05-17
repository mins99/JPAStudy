package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

// 다대다 양방향 상품 엔티티
//@Entity
//@Table(name = "PRODUCT")
public class Product2 {

    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member7> members = new ArrayList<Member7>();

    public Product2() { }

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

    public List<Member7> getMembers() {
        return members;
    }

    public void setMembers(List<Member7> members) {
        this.members = members;
    }
}
