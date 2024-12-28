package dut.udn.PGP.dao;

import dut.udn.PGP.model.Key;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class KeyDAOImpl implements KeyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveKey(Key key) {
        entityManager.persist(key);
    }

    @Override
    public Key findByUsername(String username) {
        String query = "SELECT u FROM Key u WHERE u.username = :username";
        return entityManager.createQuery(query, Key.class)
                            .setParameter("username", username)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);
    }

    @Override
    public List<Key> getAllKeys() {
        String query = "SELECT u FROM Key u";
        return entityManager.createQuery(query, Key.class).getResultList();
    }
}
