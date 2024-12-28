package dut.udn.PGP.dao;

import dut.udn.PGP.model.Key;

import java.util.List;

public interface KeyDAO {
    void saveKey(Key key);
    Key findByUsername(String username);
    List<Key> getAllKeys();
}
