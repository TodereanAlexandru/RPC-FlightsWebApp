package businessLayer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataAccess.User;
import javassist.bytecode.Descriptor.Iterator;

public class ManageUser {
	private static SessionFactory factory;
	
	public ManageUser() {
		if(factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
	}
	
	public User findUser(String username, String password) {
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User U WHERE U.username = :username AND U.password = :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			for(java.util.Iterator it = q.list().iterator(); it.hasNext();) {
				user = (User)it.next();
				break;
			}
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
}
