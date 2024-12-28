package dut.udn.PGP.service;

import dut.udn.PGP.model.Key;

import java.util.List;

public interface KeyService {
    void saveKey(Key key);
    Key findKeyByUsername(String username);
    List<Key> getAllKeys();
}
