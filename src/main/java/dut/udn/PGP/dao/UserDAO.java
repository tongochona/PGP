package dut.udn.PGP.dao;

import dut.udn.PGP.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);
    User findByUsername(String username);
    List<User> getAllUsers();
}
