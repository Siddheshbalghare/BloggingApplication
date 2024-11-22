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
public class BlogDAO {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private EntityManagerFactory entityManagerFactory;

	public BlogDTO addblog(BlogDTO blogDTO) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(blogDTO);
		entityTransaction.commit();
		closeConnection();
		return blogDTO;
	}

	@SuppressWarnings("unchecked")
	public List<BlogDTO> findAllWebBlogs() {
		openConnection();
		Query query = entityManager.createQuery("Select blogs from BlogDTO blogs");
		List<BlogDTO> blogs = query.getResultList();
		closeConnection();
		return blogs;
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

	public List<BlogDTO> findMyBlogs(int userId) {
		openConnection();
		UserDTO userDTO = entityManager.find(UserDTO.class, userId);
		List<BlogDTO> blogs = userDTO.getBlogs();
		closeConnection();
		return blogs;
	}

	public BlogDTO deleteBlog(int blogId, int userId) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, userId);
		BlogDTO blog = entityManager.find(BlogDTO.class, blogId);
		List<BlogDTO> blogs = user.getBlogs();
		BlogDTO blogToBeDeleted = null;
		for (BlogDTO b : blogs) {
			if (b.getId() == blogId) {
				blogToBeDeleted = b;
				break;
			}
		}

		blogs.remove(blogToBeDeleted);
		user.setBlogs(blogs);
		entityTransaction.begin();
		entityManager.remove(blog);
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return blog;
	}

	public BlogDTO findBlogById(int blogId) {
		openConnection();
		BlogDTO blog = entityManager.find(BlogDTO.class, blogId);
		closeConnection();
		return blog;
	}

	public BlogDTO updateBlog(int id, String title, String content, String author) {
		openConnection();
		BlogDTO blog = entityManager.find(BlogDTO.class, id);
		blog.setTitle(title);
		blog.setContent(content);
		blog.setAuthor(author);
		entityTransaction.begin();
		entityManager.persist(blog);
		entityTransaction.commit();
		closeConnection();
		return blog;
	}

	@SuppressWarnings("unchecked")
	public List<BlogDTO> searchBlogs(String query) {
		openConnection();
		Query query2 = entityManager.createQuery("SELECT blogs FROM BlogDTO blogs WHERE blogs.title LIKE '%" + query
				+ "%' OR blogs.content LIKE '%" + query + "%' OR blogs.author LIKE '%" + query + "%'");

		List<BlogDTO> blogs = query2.getResultList();
		closeConnection();
		return blogs;
	}

}
