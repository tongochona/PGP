package dut.udn.PGP.service;

import dut.udn.PGP.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User findUserByUsername(String username);
    List<User> getAllUsers();
}
