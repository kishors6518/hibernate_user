package hibernate_user.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import hibernate_user.dto.User;


public class Userdao {
	public EntityManager getManager()
	{
		return Persistence.createEntityManagerFactory("kishor").createEntityManager();
	}
	
	public int saveUser(User user)
	{
		EntityManager manager=getManager();
		EntityTransaction transaction=manager.getTransaction();
		
		transaction.begin();
		manager.persist(user);
		transaction.commit();
		return 1;
	}
	public User getUser(String email)
	{
		EntityManager manager=getManager();
		
		//Named Parameter
//		Query query=manager.createQuery("SELECT u FROM User u WHERE u.email:email");
//		query.setParameter("email", email);
		
		//Positional Parameter
		Query query=manager.createQuery("SELECT u FROM User u WHERE u.email=?1");
		query.setParameter(1, email);
		try {
			User user=(User)query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	public int setFacebookPass(String facebook, String email)
	{
		EntityManager manager=getManager();
		EntityTransaction transaction=manager.getTransaction();
		User user2=getUser(email);
		user2.setFacebook(facebook);
		if(user2!=null)
		{
			transaction.begin();
			manager.merge(user2);
			transaction.commit();
			return 1;
		}
		else
		{
			return 0;
		}
	}

}
