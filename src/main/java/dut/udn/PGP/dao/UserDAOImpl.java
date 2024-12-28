package dut.udn.PGP.dao;

import dut.udn.PGP.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT u FROM User u WHERE u.username = :username";
        return entityManager.createQuery(query, User.class)
                            .setParameter("username", username)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT u FROM User u";
        return entityManager.createQuery(query, User.class).getResultList();
    }
}
