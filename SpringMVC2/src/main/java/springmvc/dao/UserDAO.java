package springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import springmvc.dto.BlogDTO;
import springmvc.dto.UserDTO;

@Component
public class UserDAO {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public UserDTO addUser(UserDTO user) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	public UserDTO login(String email, String password) {
		openConnection();
		Query query = entityManager
				.createQuery("SELECT user from UserDTO user WHERE user.email = ?1 AND user.password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		UserDTO user = (UserDTO) query.getSingleResult();
		closeConnection();
		return user;
	}

	public UserDTO update(int id, String userName, String email, long mobile, String password, String role) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setUserName(userName);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(password);
//		user.setRole(role);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<UserDTO> findAllUsers() {
		openConnection();
		Query query = entityManager.createQuery("SELECT users FROM UserDTO users");
		List<UserDTO> users = query.getResultList();
		closeConnection();
		return users;
	}

	public void delete(long id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		entityTransaction.begin();
		entityManager.remove(user);
		entityTransaction.commit();
		closeConnection();
	}

	public void mapBlogToUser(int blogId, int userId) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, userId);
		BlogDTO blog = entityManager.find(BlogDTO.class, blogId);
		List<BlogDTO> blogs = user.getBlogs();
		blogs.add(blog);
		user.setBlogs(blogs);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();

	}

	public UserDTO blockUser(int id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setBlocked(true);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	public UserDTO unBlockUser(int id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setBlocked(false);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	private void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		entityManagerFactory = Persistence.createEntityManagerFactory("springmvc");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

}