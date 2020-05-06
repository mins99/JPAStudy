package jpabook.start;

import javax.persistence.EntityManager;

public class JpaMain {

    private static void login(EntityManager em) {

        // 1. IDENTITY
        Board board = new Board();
        em.persist(board);

        System.out.println("board.id = " + board.getId());

        // 2. SEQUENCE
        /*Board2 board2 = new Board2();
        em.persist(board2);

        System.out.println("board2.id = " + board2.getId());*/

        // 3. TABLE
        /*Board3 board3 = new Board3();
        em.persist(board3);

        System.out.println("board3.id = " + board3.getId());*/
    }
}
