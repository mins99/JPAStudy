package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction();

        System.out.println("========= before =========");

        // 플러시가 실행된다
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("========= after =========");

        tx.commit();
    }

    public void testDetached() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // 회원 엔티티 생성, 비영속 상태
        Member member = new Member();
        member.setId("memberA");
        member.setUsername("회원A");

        // 회원 엔티티 영속 상태
        em.persist(member);

        // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
        em.detach(member);

        tx.commit(); // 트랜잭션 커밋
    }

    public void closeEntityManager() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();     // 트랜잭션 시작

        Member memberA = em.find(Member.class, "memberA");
        Member memberB = em.find(Member.class, "memberB");

        tx.commit();    // 트랜잭션 커밋

        em.close();     // 영속성 컨텍스트 닫기(종료)
    }
}