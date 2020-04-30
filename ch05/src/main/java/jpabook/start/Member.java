package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    private Member() { }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Member(String id, String username, Team team) {
        this.id = id;
        this.username = username;
        this.team = team;
    }

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    // 연관관계 설정
    public void setTeam(Team team) {
        this.team = team;
         //team.getMembers().add(this);
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

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
