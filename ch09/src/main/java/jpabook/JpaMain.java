package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void testSave(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member6 member = em.find(Member6.class, 1L);
            Address4 address = member.getHomeAddress();
            Address4 newAddress = new Address4(address.getCity());
            Member6 member2 = new Member6();
            member2.setHomeAddress(newAddress);

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

            Member7 member = new Member7();

            // 임베디드 값 타입
            member.setHomeAddress(new Address5("통영", "몽돌해수욕장", "660-123"));

            // 기본값 타입 컬렉션
            member.getFavoriteFoods().add("짬뽕");
            member.getFavoriteFoods().add("짜장");
            member.getFavoriteFoods().add("탕수육");

            // 임베디드 값 타입 컬렉션
            member.getAddressHistory().add(new Address5("서울", "강남", "123-123"));
            member.getAddressHistory().add(new Address5("서울", "강북", "000-000"));

            em.persist(member);

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

            Member7 member = em.find(Member7.class, 1L);

            Address5 homeAddress = member.getHomeAddress();

            Set<String> favoriteFoods = member.getFavoriteFoods();

            for(String favoriteFood : favoriteFoods)
                System.out.println("favoriteFood = " + favoriteFood);

            List<Address5> addressHistory = member.getAddressHistory();

            addressHistory.get(0);

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

            Member7 member = em.find(Member7.class, 1L);

            // 1. 임베디드 값 타입 수정
            member.setHomeAddress(new Address5("새로운도시", "신도시", "123-456"));

            // 2. 기본값 타입 컬렉션 수정
            Set<String> favoriteFoods = member.getFavoriteFoods();
            favoriteFoods.remove("탕수육");
            favoriteFoods.add("치킨");

            // 3. 임베디드 값 타입 컬렉션 수정
            List<Address5> addressHistory = member.getAddressHistory();
            addressHistory.remove(new Address5("서울", "기존 주소", "123-123"));
            addressHistory.add(new Address5("새로운도시", "신도시", "123-456"));

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        //testSave(em);       // 예제 9.10 주소 불변 객체

        //testSave2(em);          // 9.5.1 예제 9.13 값 타입 컬렉션 등록
        //testSave3(em);          // 9.5.1 예제 9.15 조회
        //testSave4(em);          // 9.5.1 예제 9.16 수정

        em.close();
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}

