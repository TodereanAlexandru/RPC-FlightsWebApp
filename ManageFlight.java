package businessLayer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataAccess.Flight;
import dataAccess.User;

public class ManageFlight {
	private static SessionFactory factory;
	
	public ManageFlight() {
		if(factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
	}
	
	public List<Flight> getFlights() {
		List<Flight> flights = new ArrayList<Flight>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Flight");
			for(java.util.Iterator it = q.list().iterator(); it.hasNext();) {
				Flight fl = (Flight)it.next();
				flights.add(fl);
			}
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return flights;
	}
	
	public List<Flight> getFlightsByCity(String city) {
		List<Flight> flights = new ArrayList<Flight>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Flight F WHERE departureCity = :c1 OR arrivalCity = :c2");
			q.setParameter("c1", city);
			q.setParameter("c2", city);
			for(java.util.Iterator it = q.list().iterator(); it.hasNext();) {
				Flight fl = (Flight)it.next();
				flights.add(fl);
			}
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return flights;
	}
	
	public Flight findFlight(int id) {
		Flight flight = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Flight F WHERE idflight = :id");
			q.setParameter("id", id);
			for(java.util.Iterator it = q.list().iterator(); it.hasNext();) {
				flight = (Flight)it.next();
				break;
			}
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return flight;
	}
	
	public void insertFlight(Flight flight) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(flight);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void updateFlight(Flight flight) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(flight);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void deleteFlight(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Flight flight = new Flight();
			flight.setIdflight(id);
			session.delete(flight);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
