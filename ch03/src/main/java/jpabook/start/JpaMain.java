package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            Member member = new Member();
            member.setId("member1");
            member.setUsername("회원1");

            // 1차 캐시에 저장됨
            em.persist(member);

            // 1차 캐시에서 조회
            Member findMember = em.find(Member.class, "member1");

            // 데이터베이스에서 조회
            Member findMember2 = em.find(Member.class, "member2");

            Member findMember3 = em.find(Member.class, "member1");

            System.out.println("a == b ? " + (findMember == findMember3));

            member.setId("member11");
            member.setAge(20);

            //em.update(member);        // 따로 update를 할 필요는 없다

            em.remove(member);

            // 커밋하는 순간 데이터베이스에 insert sql을 보낸다
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
