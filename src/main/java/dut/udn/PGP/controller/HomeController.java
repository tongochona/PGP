package dut.udn.PGP.controller;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import dut.udn.PGP.PGP;
import dut.udn.PGP.model.Key;
import dut.udn.PGP.model.Message;
import dut.udn.PGP.model.User;
import dut.udn.PGP.service.KeyService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	private KeyService keyService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/chat")
	public String chatPage(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("loggedInUser");
		model.addAttribute("user", user);
		return "chat";
	}

	@PostMapping("/code")
	@ResponseBody
	public String[] codePGP(@RequestBody Message message, HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("loggedInUser");
		Key key = keyService.findKeyByUsername(message.getTo());
		PublicKey senderpubKey = user.getPublickey();
		PrivateKey senderprivateKey = user.getPrivatekey();
		PublicKey receiverpubKey = key.getPublickey();
		String content = message.getContent();
		String[] ciphertext = null;
		try {
			ciphertext = PGP.senderside(senderpubKey, senderprivateKey, receiverpubKey, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciphertext;
	}

	@PostMapping("/decode")
	@ResponseBody
	public String decodePGP(@RequestBody Message message, HttpServletRequest request, Model model) {

		String text = null;

		User user = (User) request.getSession().getAttribute("loggedInUser");
		Key key = keyService.findKeyByUsername(message.getFrom());
		
		
		if (user != null) {
			if (!user.getUsername().equals(message.getTo()))
				return null;
		} else {
			try {
				user = new User();
				KeyPair userkeyPair;
				userkeyPair = PGP.buildKeyPair();
				PublicKey userpubKey = userkeyPair.getPublic();
				PrivateKey userprivateKey = userkeyPair.getPrivate();
				user.setPublickey(userpubKey);
				user.setPrivatekey(userprivateKey);
			} catch (Exception e) {
				e.printStackTrace();
				text = "Can't create Pair key";
			}
		}

		try {
			PublicKey senderpubKey = key.getPublickey();
			PrivateKey receiverprivateKey = user.getPrivatekey();
			PublicKey receiverpubKey = user.getPublickey();
			String[] content = message.getCiphertext();
			text = PGP.receiverside(content, senderpubKey, receiverpubKey, receiverprivateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			text = "Can't decrypt message";
		}

		return text;
	}

	@Autowired
	public void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}
}
