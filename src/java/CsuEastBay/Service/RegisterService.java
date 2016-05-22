package CsuEastBay.Service;

 
import CsuEastBay.Util.HibernateUtil;
import CsuEastBay.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterService {

        public boolean register(User user) {
        Session session = HibernateUtil.openSession();
        if (isUserExists(user)) {
            return false;
        }

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           
            session.saveOrUpdate(user);
             session.getTransaction().commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public boolean isUserExists(User user) {
        Session session = HibernateUtil.openSession();
        boolean result = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("from User where userId='" + user.getUserId() + "'");
            User u = (User) query.uniqueResult();
            

            if (u != null) {
                result = true;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }
}
