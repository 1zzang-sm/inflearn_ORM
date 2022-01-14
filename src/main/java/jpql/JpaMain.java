package jpql;


import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            Team team1 = new Team();
            team1.setName("teamB");
            em.persist(team1);

            Member member = new Member();
            member.setUsername("user1");
            member.setAge(10);
            member.setTeam(teamA);
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("user2");
            member1.setAge(20);
            member1.setTeam(team1);
            em.persist(member1);

            em.flush();
            em.clear();

            List<Member> res1 = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "user1")
                    .getResultList();
            System.out.println("res1 = " + res1);
            
            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class).getResultList();
            System.out.println("result = " + result);
            for (Member member2 : result) {
                System.out.println("member2 = " + member2.getUsername() + member2.getTeam().getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
