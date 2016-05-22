package CsuEastBay.Service;


import CsuEastBay.Util.HibernateUtil;
import CsuEastBay.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {

    public boolean authenticateUser(String userId, String password) {
        User user = getUserByUserId(userId);          
        if(user!=null && user.getUserId().equals(userId) && user.getPassword().equals(password)){
            return true;
        }else{ 
            return false;
        }
    }

    public User getUserByUserId(String userId) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("from User where userId='"+userId+"'");
            user = (User)query.uniqueResult();
            
        } catch (Exception e) {
             
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    
    public List<User> getListOfUsers(){
        List<User> list = new ArrayList<User>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;        
        try {
            tx = session.beginTransaction();
            
            list = session.createQuery("from User").list();                        
            
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
