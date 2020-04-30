package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void testSave(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 팀1 저장
            Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            // 회원1 저장
            Member member1 = new Member("member1", "회원1");
            member1.setTeam(team1);     // 연관관계 설정 member1 -> team1
            em.persist(member1);

            team1.getMembers().add(member1);      // 무시(연관관계의 주인이 아님)

            // 회원2 저장
            Member member2 = new Member("member2", "회원2");
            member2.setTeam(team1);     // 연관관계 설정 member2 -> team1
            em.persist(member2);

            team1.getMembers().add(member2);      // 무시(연관관계의 주인이 아님)

//            em.flush();
//            em.clear();  // 주석 풀고 biDirection 실행시 정상동작 ??

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSearch(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            Member member = em.find(Member.class, "member1");
            Team team = member.getTeam();
            System.out.println("팀 이름 = " + team.getName());

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void queryLogicJoin(EntityManager em) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            String jpql = "select m from Member m join m.team t where "
                    + "t.name=:teamName";

            List<Member> resultList = em.createQuery(jpql, Member.class)
                    .setParameter("teamName", "팀1")
                    .getResultList();

            for(Member m : resultList) {
                System.out.println("member.username = " + m.getUsername());
            }
            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void updateRelation(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            // 새로운 팀2
            Team team2 = new Team("team2", "팀2");
            em.persist(team2);

            // 회원1에 새로운 팀2 설정
            Member member = em.find(Member.class, "member1");
            member.setTeam(team2);

//            em.flush();
//            em.clear();

            System.out.println("수정된 팀 이름 = " + member.getTeam().getName());
            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void deleteRelation(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            Member member1 = em.find(Member.class, "member1");
            member1.setTeam(null);

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void deleteRelation2(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            Member member1 = em.find(Member.class, "member1");
            Member member2 = em.find(Member.class, "member2");
            Team team = member1.getTeam();

            member1.setTeam(null);
            member2.setTeam(null);
            em.remove(team);

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void biDirection(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            Team team = em.find(Team.class, "team1");
            List<Member> members = team.getMembers();

            for(Member m : members) {
                System.out.println("member.username = " + m.getUsername());
            }

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    // 5.6 양방향 연관관계의 주의점
    public static void testSaveNonOwner(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            // 회원1 저장
            Member member1 = new Member("member1", "회원1");
            em.persist(member1);

            // 회원2 저장
            Member member2 = new Member("member2", "회원2");
            em.persist(member2);

            Team team1 = new Team("team1", "팀1");
            // 주인이 아닌 곳만 연관관계 설정
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);

            em.persist(team1);

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    // 5.6.1 순수한 객체까지 고려한 양방향 연관관계
    public static void test순수한객체_양방향() {

        // 팀1
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1);     // 연관관계 설정 member1 -> team1
        member2.setTeam(team1);     // 연관관계 설정 member2 -> team1

        List<Member> members = team1.getMembers();
        System.out.println("members.size() = " + members.size());
    }

    public static void test순수한객체_양방향2() {

        // 팀1
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1);     // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1);    // 연관관계 설정 team1 -> member1

        member2.setTeam(team1);     // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2);    // 연관관계 설정 team1 -> member2

        List<Member> members = team1.getMembers();
        System.out.println("members.size() = " + members.size());
    }

    public static void testORM_양방향(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            System.out.println("============================");

            // 팀1 저장
            Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            Member member1 = new Member("member1", "회원1");

            //양방향 연관관계 설정
            member1.setTeam(team1);             // 연관관계 설정 member1 -> team1
//            team1.getMembers().add(member1);    // 연관관계 설정 team1 -> member1
            em.persist(member1);

            Member member2 = new Member("member2", "회원2");

            //양방향 연관관계 설정
            member2.setTeam(team1);             // 연관관계 설정 member2 -> team1
//            team1.getMembers().add(member2);    // 연관관계 설정 team1 -> member2
            em.persist(member2);

            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        testSave(em);               // 5.2.1 저장 & 5.5 양방향 연관관계 저장

        //testSearch(em);             // 5.2.2 조회 - 객체 그래프 탐색
        //queryLogicJoin(em);         // 5.2.2 조회 - JPQL 조인 검색

        //updateRelation(em);         // 5.2.3 수정

        //deleteRelation(em);         // 5.2.4 연관관계 제거

        //deleteRelation2(em);        // 5.2.5 연관된 엔티티 삭제

        //biDirection(em);            // 5.3.2 일대다 컬렉션 조회

        //testSaveNonOwner(em);       // 5.6 양방향 연관관계의 주의점

        //test순수한객체_양방향();           // 5.6.1 순수한 객체까지 고려한 양방향 연관관계
        //test순수한객체_양방향2();
        //testORM_양방향(em);                // 5.6.1 & 5.6.2

        //testSaveOwner(em);            // 5.6.1 순수한 객체까지 고려한 양방향 연관관계

        em.close();
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
