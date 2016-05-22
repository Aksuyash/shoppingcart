/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CsuEastBay.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author suyashadhikary
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory1();

    private static SessionFactory buildSessionFactory1() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory cretaion failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
//    static {
//        try {
//            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory cretaion failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    } 
//

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}
