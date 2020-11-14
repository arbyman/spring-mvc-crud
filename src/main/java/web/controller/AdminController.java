package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}

	@GetMapping(value = "/show/{id}")
	public String showUser(@PathVariable("id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "show";
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteUserById(@PathVariable long id) {
		userService.delete(id);
		return "redirect:/admin";
	}

	@GetMapping(value = "/new")
	public String add(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping(value = "/new")
	public String addUser(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/edit/{id}")
	public String editUser(@PathVariable("id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "edit";
	}

	@PatchMapping(value = "/edit/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.update(id, user);
		return "redirect:/admin";
	}
}