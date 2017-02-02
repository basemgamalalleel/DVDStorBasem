package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author user
 */
public class ActorHelper {

    Session session = null;

    public ActorHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertActor(Actor a) {
        int result = 0;
        String sql = "insert into actor(first_name, last_name, last_update) "
                + "values (:fName, :lName, :update)";
        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            SQLQuery q = session.createSQLQuery(sql);
            // associate the Actor POJO and table with the query 
            q.addEntity(Actor.class);
            // bind values to the query placeholders
            q.setParameter("fName", a.getFirstName());
            q.setParameter("lName", a.getLastName());
            q.setParameter("update", a.getLastUpdate());
            // execute the query
            result = q.executeUpdate();
            // commit the changes to the database
            // this is what allows the changes to be
            // truely viewed in the database
            // but it also makes the transaction inactive
            // which means it will have to be started again
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
