package dut.udn.PGP.controller;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dut.udn.PGP.service.*;
import jakarta.servlet.http.HttpServletRequest;
import dut.udn.PGP.model.Key;
import dut.udn.PGP.model.User;
import dut.udn.PGP.PGP;

@Controller
public class AuthController {
	private UserService userService;
	private KeyService keyService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
		User user = userService.findUserByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
		request.getSession().setAttribute("loggedInUser", user);
		model.addAttribute("user", user);
		return "redirect:/dashboard" ;
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password, Model model) {

		User user = new User();
		Key key = new Key();
		KeyPair userkeyPair;
		try {
			userkeyPair = PGP.buildKeyPair();
			PublicKey userpubKey = userkeyPair.getPublic();
			PrivateKey userprivateKey = userkeyPair.getPrivate();
			user.setUsername(username);
			user.setPassword(password);
			user.setPublickey(userpubKey);
			user.setPrivatekey(userprivateKey);
			key.setUsername(username);
			key.setPublickey(userpubKey);
			keyService.saveKey(key);
			userService.saveUser(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Account created fail!");
			return "register";
		}
		model.addAttribute("message", "Account created successfully!");
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}
}
