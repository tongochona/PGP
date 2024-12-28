package dut.udn.PGP.service;

import dut.udn.PGP.dao.KeyDAO;
import dut.udn.PGP.model.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

	@Autowired
	private KeyDAO keyDAO;

	@Override
	public void saveKey(Key key) {
		keyDAO.saveKey(key);
	}

	@Override
	public List<Key> getAllKeys() {
		return keyDAO.getAllKeys();
	}

	@Override
	public Key findKeyByUsername(String username) {
		return keyDAO.findByUsername(username);
	}
}
