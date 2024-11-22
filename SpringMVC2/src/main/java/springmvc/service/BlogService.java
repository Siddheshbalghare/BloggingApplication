package springmvc.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springmvc.dao.BlogDAO;
import springmvc.dao.UserDAO;
import springmvc.dto.BlogDTO;
import springmvc.dto.UserDTO;

@Component
public class BlogService {

	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private UserDAO userDAO;

	public BlogDTO addblog(String title, String content, String author, UserDTO user) {
		BlogDTO blogDTO = new BlogDTO();
		blogDTO.setTitle(title);
		blogDTO.setContent(content);
		blogDTO.setAuthor(author);
		blogDTO.setDate(new Date(System.currentTimeMillis()));
		blogDTO.setUserId(user.getId());
		try {
			BlogDTO blog = blogDAO.addblog(blogDTO);
			userDAO.mapBlogToUser(blogDTO.getId(), user.getId());
			return blog;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<BlogDTO> findAllWebBlogs() {
		List<BlogDTO> blogs = blogDAO.findAllWebBlogs();
		if (blogs.size() > 0) {
			return blogs;
		} else {
			return null;
		}
	}

	public List<BlogDTO> findMyBlogs(int userId) {
		List<BlogDTO> blogs = blogDAO.findMyBlogs(userId);
		if (blogs.size() > 0) {
			return blogs;
		} else {
			return null;
		}
	}

//	public void deleteMyBlog(int blogId,HttpSession httpSession,ModelMap map) {
//		
//		UserDTO user=(UserDTO)httpSession.getAttribute("user");
//		userDAO.deleteBlog(user.getId(),blogId);
//		
//	}

	public BlogDTO deleteBlog(int blogId, int userId) {
		try {
			return blogDAO.deleteBlog(blogId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public BlogDTO findBlogById(int blogId) {
		return blogDAO.findBlogById(blogId);
	}

	public BlogDTO updateblog(int blogId, String title, String content, String author) {

		try {
			BlogDTO myblog = blogDAO.updateBlog(blogId, title, content, author);
			return myblog;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<BlogDTO> sortBlogs(int index) {
		List<BlogDTO> blogs = blogDAO.findAllWebBlogs();
		Collections.sort(blogs);
		if (index == 1) {
			Collections.reverse(blogs);
			return blogs;
		} else {
			return blogs;
		}
	}

	public List<BlogDTO> searchBlogs(String query) {
		List<BlogDTO> blogs = blogDAO.searchBlogs(query);
		if (blogs.size() > 0) {
			return blogs;
		} else {
			return null;
		}
	}

}
