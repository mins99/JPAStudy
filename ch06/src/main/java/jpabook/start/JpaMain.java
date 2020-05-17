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

            Team team1 = new Team("team1");

            Member member1 = new Member("member1");
            member1.setTeam(team1);

            em.persist(team1);
            em.persist(member1);

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave2(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member2 member1 = new Member2("member1");
            Member2 member2 = new Member2("member1");

            Team2 team1 = new Team2("team1");
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);

            em.persist(member1);    // INSERT-member1
            em.persist(member2);    // INSERT-member2
            em.persist(team1);      // INSERT-team1, UPDATE-member1.fk, UPDATE-member2.fk

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

            Locker locker1 = new Locker("locker");

            Member4 member1 = new Member4("member1");
            member1.setLocker(locker1);

            em.persist(locker1);
            em.persist(member1);

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave4(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Locker2 locker1 = new Locker2("locker");

            Member5 member1 = new Member5("member1");
            member1.setLocker(locker1);

            em.persist(locker1);
            em.persist(member1);

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave5(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member6 member1 = new Member6("member1");

            Locker3 locker1 = new Locker3("locker");
            locker1.setMember(member1);

            em.persist(locker1);
            em.persist(member1);

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave6(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            /* tx.begin();

            Product productA = new Product();
            productA.setId("productA");
            productA.setName("상품A");
            em.persist(productA);

            Member7 member = new Member7();
            member.setId("member1");
            member.setUsername("회원");
            member.getProducts().add(productA);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ CREATE and INSERT end ================");

            Member7 member2 = em.find(Member7.class, "member1");
            List<Product> products = member2.getProducts();     // 객체 그래프 탐색

            for(Product product : products)
                System.out.println("product.name = " + product.getName());

            tx.commit(); //트랜잭션 커밋 */

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave7(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Product2 productA = new Product2();
            productA.setId("productA");
            productA.setName("상품A");
            em.persist(productA);

            Member7 member = new Member7();
            member.setId("member1");
            member.setUsername("회원");
            productA.getMembers().add(member);
            member.addProduct(productA);

            em.persist(member);


            System.out.println("================ CREATE and INSERT end ================");

            em.flush();
            em.clear();

            Product2 product = em.find(Product2.class, "productA");
            List<Member7> members = product.getMembers();

            for(Member7 member2 : members)
                System.out.println("member = " + member2.getUsername());

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave8(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member8 member = new Member8();
            member.setId("member1");
            member.setUsername("회원");
            em.persist(member);

            Product3 productA = new Product3();
            productA.setId("productA");
            productA.setName("상품A");
            em.persist(productA);

            //회원상품 저장
            MemberProduct memberProduct = new MemberProduct();
            memberProduct.setMember(member);
            memberProduct.setProduct(productA);
            memberProduct.setOrderAmount(2);

            em.persist(memberProduct);

            System.out.println("================ CREATE and INSERT end ================");

            em.flush();
            em.clear();

            MemberProductId memberProductId = new MemberProductId();
            memberProductId.setMember("member1");
            memberProductId.setProduct("productA");

            MemberProduct memberProduct2 = em.find(MemberProduct.class, memberProductId);

            Member8 member2 = memberProduct2.getMember();
            Product3 product = memberProduct2.getProduct();

            System.out.println("member = " + member2.getUsername());
            System.out.println("product = " + product.getName());
            System.out.println("orderAmount = " + memberProduct2.getOrderAmount());

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void testSave9(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member9 member = new Member9();
            member.setId("member1");
            member.setUsername("회원1");
            em.persist(member);

            Product3 productA = new Product3();
            productA.setId("productA");
            productA.setName("상품1");
            em.persist(productA);

            //주문 저장
            Orders orders = new Orders();
            orders.setMember(member);       // 주문 회원 - 연관관계 설정
            orders.setProduct(productA);    // 주문 상품 - 연관관계 설정
            orders.setOrderAmount(2);
            em.persist(orders);

            System.out.println("================ CREATE and INSERT end ================");

            em.flush();
            em.clear();

            Long orderId = 1L;
            Orders orders2 = em.find(Orders.class, orderId);

            Member9 member2 = orders.getMember();
            Product3 product = orders.getProduct();

            System.out.println("member = " + member2.getUsername());
            System.out.println("product = " + product.getName());
            System.out.println("orderAmount = " + orders.getOrderAmount());

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        //testSave(em);               // 6.1.2 다대일 양방향 - 예제 6.3, 6.4

        //testSave2(em);              // 6.2.1 일대다 단방향 - 예제 6.7

        //testSave3(em);              // 6.3.1 일대일 단방향(주 테이블에 외래 키) - 예제 6.10
        //testSave4(em);              // 6.3.1 일대일 양방향(주 테이블에 외래 키) - 예제 6.11
        //testSave5(em);              // 6.3.2 일대일 양방향(대상 테이블에 외래 키) - 예제 6.12

        //testSave6(em);              // 6.4.1 다대다 단방향 - 예제 6.15, 6.16
        //testSave7(em);              // 6.4.2 다대다 양방향 - 예제 6.17, 6.18
        //testSave8(em);              // 6.4.3 다대다 양방향 연결엔티티 - 예제 6.23, 6.24
        //testSave9(em);              // 6.4.4 다대다 양방향 새로운 기본 키 - 예제 6.27, 6.28


        em.close();
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
