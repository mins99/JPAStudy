package jpabook;

import com.mysema.query.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static jpabook.QMember.member;       // target/generated-source/java 아래 경로

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

    public static void insertData(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Team team1 = new Team();
            team1.setName("팀A");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(team2);
            em.persist(member3);

            Item item1 = new Item();
            item1.setName("좋은상품");
            item1.setPrice(30000);
            em.persist(item1);

            Item item2 = new Item();
            item2.setName("좋은상품");
            item2.setPrice(10000);
            em.persist(item2);

            Product product = new Product();
            product.setName("productA");
            product.setPrice(1000);
            em.persist(product);

            em.flush();
            em.clear();

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

            String jpql = "select m from Member m join fetch m.team";

            List<Member> members = em.createQuery(jpql, Member.class)
                                    .getResultList();

            for(Member member : members) {
                // 페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
                System.out.println("username = " + member.getUsername() +", teamname = " + member.getTeam().getName());
            }

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

            String jpql = "select t from Team t join fetch t.member where t.name = '팀A'";

            List<Team> teams = em.createQuery(jpql, Team.class)
                    .getResultList();

            for(Team team : teams) {
                System.out.println("teamname = " + team.getName() +", team = " + team);

                for(Member member : team.getMember()) {
                    // 페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
                    System.out.println(" -> username = " + member.getUsername() + ", member = " + member);
                }
            }

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

            String jpql = "select distinct t from Team t join fetch t.member where t.name = '팀A'";

            List<Team> teams = em.createQuery(jpql, Team.class)
                    .getResultList();

            for(Team team : teams) {
                System.out.println("teamname = " + team.getName() +", team = " + team);

                for(Member member : team.getMember()) {
                    // 페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
                    System.out.println(" -> username = " + member.getUsername() + ", member = " + member);
                }
            }

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void queryDSL(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);
            QMember qMember = new QMember("m");     // 생성되는 JPQL의 별칭이 m
            List<Member> members = query.from(qMember)
                                        .where(qMember.username.eq("회원1"))
                                        .orderBy(qMember.username.desc())
                                        .list(qMember);
            
            for(Member member : members) {
                System.out.println("member = " + member);
            }

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void basic(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);
            List<Member> members = query.from(member)
                    .where(member.username.eq("회원1"))
                    .orderBy(member.username.desc())
                    .list(member);

            for(Member member : members) {
                System.out.println("member = " + member);
            }

            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }

    public static void basicQuery(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);
            QItem item = QItem.item;
            List<Item> list = query.from(item)
                    .where(item.name.eq("좋은상품").and(item.price.gt(20000)))
                    .list(item);

            for(Item item1 : list) {
                System.out.println("item = " + item1);
            }

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

            Product productA = em.createQuery("select p from Product p where p.name = :name", Product.class)
                                .setParameter("name", "productA")
                                .getSingleResult();

            System.out.println("productA 수정 전 = " + productA.getPrice());

            // 벌크 연산 수행으로 모든 상품 가격 10% 상승
            em.createQuery("update Product p set p.price = p.price * 1.1")
                    .executeUpdate();

            System.out.println("productA 수정 후 = " + productA.getPrice());

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }
    }


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        insertData(em);

        //testSave(em);       // 예제 10.28 페치 조인 사용
        //testSave2(em);         // 예제 10.31 컬렉션 페치 조인 사용
        //testSave3(em);          // 페치 조인과 DISTINCT

        //queryDSL(em);       // 예제 10.79 QueryDSL 시작
        //basic(em);          // 예제 10.82 import static 활용
        //basicQuery(em);     // 10.4.3 검색 조건 쿼리 예제 10.83 QueryDSL 기본 쿼리 기능

        //testSave4(em);      // 예제 10.125 벌크 연산 시 주의점 예제

        em.close();
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}

