package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void main(String args[]) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();     // 트랜잭션 시작

        Member member = createMember("memberA", "회원1");

        member.setUsername("회원2");      // 준영속 상태에서 변경

        mergeMember(member);
    }

    static Member createMember(String id, String username) {
        // 영속성 컨텍스트1 시작

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em.persist(member);
        tx.commit();

        System.out.println("member = " + member.getUsername());     // 회원1

        em.close(); // 영속성 컨텍스트1 종료. member 엔티티는 준영속 상태가 된다

        return member;
    }

    static void mergeMember(Member member) {
        // 영속성 컨텍스트2 시작

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member mergeMember = em.merge(member);
        tx.commit();

        System.out.println("member = " + member.getUsername());     // 회원2

        em.close();
    }
}
