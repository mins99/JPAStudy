package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    // 5.3.1 양방향 연관관계 매핑
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    private Team() { }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(String id, String name, List<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
