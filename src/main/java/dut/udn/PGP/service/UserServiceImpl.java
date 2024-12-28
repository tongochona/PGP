package dut.udn.PGP.service;

import dut.udn.PGP.dao.UserDAO;
import dut.udn.PGP.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public User findUserByUsername(String username) {
		return userDAO.findByUsername(username);
	}
}
