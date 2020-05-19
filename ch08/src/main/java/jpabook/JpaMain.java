package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void printUserAndTeam(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member member = em.find(Member.class, 1L);
            Member member2 = em.getReference(Member.class, 1L);
            //Team team = member.getTeam();

            System.out.println("member : " + member.getUsername());
            //System.out.println("team : " + team.getName());

            em.flush();
            em.clear();

            member2.getTeam();

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Team team = new Team();
            team.setName("team");

            Member2 member = new Member2();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member2 member2 = em.find(Member2.class, 2L);
            Team team2 = member2.getTeam();
            //team2.getName();

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave3(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Team team = new Team();
            team.setName("team");

            Member3 member = new Member3();
            member.setUsername("member1");
            member.setTeam(team);

            Product product = new Product();
            product.setName("상품1");

            Orders orders = new Orders();
            orders.setMember(member);
            orders.setProduct(product);
            orders.setOrderAmount(2);

            em.persist(team);
            em.persist(member);
            em.persist(product);
            em.persist(orders);

            em.flush();
            em.clear();

            Member3 member2 = em.find(Member3.class, 2L);

            System.out.println("Member : " + member2.getClass());
            System.out.println("Team : " + member2.getTeam().getClass());
            System.out.println("Orders : " + member2.getOrders().getClass());   // 프록시

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void saveNoCascade(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 부모 저장
            Parent parent = new Parent();
            em.persist(parent);

            // 1번 자식 저장
            Child child1 = new Child();
            child1.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child1);       // 부모 -> 자식
            em.persist(child1);

            // 2번 자식 저장
            Child child2 = new Child();
            child2.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child2);       // 부모 -> 자식
            em.persist(child2);

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void saveWithCascade(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 부모 저장
            Parent parent = new Parent();

            // 1번 자식 저장
            Child child1 = new Child();
            child1.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child1);       // 부모 -> 자식

            // 2번 자식 저장
            Child child2 = new Child();
            child2.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child2);       // 부모 -> 자식

            em.persist(parent);

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void removeTest(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 부모 저장
            Parent parent = new Parent();

            // 1번 자식 저장
            Child child1 = new Child();
            child1.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child1);       // 부모 -> 자식

            // 2번 자식 저장
            Child child2 = new Child();
            child2.setParent(parent);   // 자식 -> 부모 연관관계 설정
            parent.getChildren().add(child2);       // 부모 -> 자식

            em.persist(parent);

            Parent parent1 = em.find(Parent.class, 1L);
            parent1.getChildren().remove(0);

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        //printUserAndTeam(em);   // 8.1 예제 8.3 회원과 팀 정보를 출력하는 비즈니스 로직

        //testSave(em);           // 8.2.1 예제 8.7 즉시 로딩, 8.2.2 예제 8.10 지연 로딩

        //testSave3(em);          // 8.3 예제 8.12 지연 로딩 활용

        //saveNoCascade(em);      // 8.4 예제 8.16 부모 자식 저장
        saveWithCascade(em);      // 8.4.1 예제 8.17 부모 자식 저장

        removeTest(em);             // 8.5 예제 8.19 고아 객체 제거 기능

        em.close();
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}

