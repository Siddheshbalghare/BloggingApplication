package springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.dto.BlogDTO;

import springmvc.dto.UserDTO;
import springmvc.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/add-blog", method = RequestMethod.GET)
	private String addBlogPage(HttpSession httpSession) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			return "addblog";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/addblog", method = RequestMethod.POST)
	private String addBlog(@RequestParam(name = "title") String title, @RequestParam(name = "content") String content,
			@RequestParam(name = "author") String author, ModelMap map, HttpSession httpSession) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		BlogDTO blog = blogService.addblog(title, content, author, user);
		if (blog != null) {
			return allblogs(map, httpSession);
		} else {
			map.addAttribute("message", "Something went wrong..");
			return "addblog";
		}
	}

	@RequestMapping(value = "/all-blogs", method = RequestMethod.GET)
	private String allblogs(ModelMap modelMap, HttpSession httpSession) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			List<BlogDTO> blogs = blogService.findAllWebBlogs();
			if (blogs != null) {
				modelMap.addAttribute("blogs", blogs);
			} else {
				modelMap.addAttribute("message", "Blogs not found...");
			}
			modelMap.addAttribute("role", user.getRole());
			return "blogs";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/my-blogs", method = RequestMethod.GET)
	private String myBlogs(ModelMap modelMap, HttpSession httpSession) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			List<BlogDTO> blogs = blogService.findMyBlogs(user.getId());
			if (blogs != null) {
				modelMap.addAttribute("blogs", blogs);
			} else {
				modelMap.addAttribute("message", "Blogs not found...");
			}
			modelMap.addAttribute("role", user.getRole());
			return "myblogs";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "delete-blog", method = RequestMethod.GET)
	private String deleteBlog(@RequestParam(name = "blog-id") int blogId, HttpSession httpSession, ModelMap modelMap) {
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		if (user != null) {
			BlogDTO deletedBlog = blogService.deleteBlog(blogId, user.getId());
			if (deletedBlog == null) {
				modelMap.addAttribute("message", "Something went wrong..");
			}
			List<BlogDTO> blogs = null;
//			if (user.getRole().equals(Role.USER)) {
			blogs = blogService.findMyBlogs(user.getId());
			if (blogs != null) {
				modelMap.addAttribute("blogs", blogs);
			} else {
				modelMap.addAttribute("message", "Blogs not found..");
			}
			return "myblogs";
//			} else {
//				blogs = blogService.findAllWebBlogs();
//				if (blogs != null) {
//					modelMap.addAttribute("blogs", blogs);
//				} else {
//					modelMap.addAttribute("message", "Blogs not found..");
//				}
////				modelMap.addAttribute("role", user.getRole());
//				return "blogs";
//			}
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "delete-blogs", method = RequestMethod.GET)
	private String deleteBlogs(@RequestParam(name = "blog-id") int blogId, @RequestParam(name = "user-id") int userId,
			HttpSession httpSession, ModelMap modelMap) {
		BlogDTO deletedBlog = blogService.deleteBlog(blogId, userId);
		if (deletedBlog != null) {
			modelMap.addAttribute("message", "Blog is Deleted...");
			List<BlogDTO> blogs = null;
			blogs = blogService.findAllWebBlogs();
			if (blogs != null) {                        
				modelMap.addAttribute("blogs", blogs);
			} else {
				modelMap.addAttribute("message", "Blogs not found..");
			}
			return "blogs";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/editBlog", method = RequestMethod.GET)
	private String editBlog(@RequestParam(name = "id") int blogId, ModelMap map) {
		BlogDTO blog = blogService.findBlogById(blogId);
		map.addAttribute("blog", blog);
		return "editBlog";
	}

	@RequestMapping(value = "/update-blog", method = RequestMethod.POST)
	private String updateBlog(@RequestParam(name = "id") int blogId, @RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @RequestParam(name = "author") String author, ModelMap map,
			HttpSession httpSession) {
//		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		BlogDTO blog = blogService.updateblog(blogId, title, content, author);
		if (blog != null) {
			return "/blogs";
		} else {
			map.addAttribute("message", "Something went wrong..");
			return "/editblog";
		}
	}

	@RequestMapping(value = "/sort", method = RequestMethod.GET)
	protected String Sort(@RequestParam(name = "index") int index, ModelMap modelMap) {

		List<BlogDTO> blogs = blogService.sortBlogs(index);
		if (blogs != null) {

			modelMap.addAttribute("blogs", blogs);
		} else {
			modelMap.addAttribute("message", "Blogs not found...");
		}
		return "blogs";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	protected String Search(@RequestParam(name = "query") String query, ModelMap modelMap) {

		List<BlogDTO> blogs = blogService.searchBlogs(query);
		if (blogs != null) {

			modelMap.addAttribute("blogs", blogs);
		} else {
			modelMap.addAttribute("message", "Blogs not found...");
		}

		return "blogs";
	}
}
