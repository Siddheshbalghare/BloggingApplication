package springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springmvc.dao.UserDAO;
import springmvc.dto.BlogDTO;
import springmvc.dto.Role;
import springmvc.dto.UserDTO;

@Component
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public UserDTO addUser(String userName, String email, long mobile, String password, String role) {
		UserDTO user = new UserDTO();

		if (role.equals("USER")) {
			user.setRole(Role.USER);
		} else {
			user.setRole(Role.ADMIN);
		}
		user.setUserName(userName);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(password);
		user.setBlogs(new ArrayList<BlogDTO>());
		try {
			return userDAO.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO blockUser(int id) {
		try {
			return userDAO.blockUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO unBlockUser(int id) {
		try {
			return userDAO.unBlockUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO login(String email, String password) {
		try {
			return userDAO.login(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO updateUser(int id, String userName, String email, long mobile, String password, String role) {

		try {
			return userDAO.update(id, userName, email, mobile, password, role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteUser(long id) {
		userDAO.delete(id);

	}

	public List<UserDTO> findAllUsers() {
		return userDAO.findAllUsers();
	}
}
