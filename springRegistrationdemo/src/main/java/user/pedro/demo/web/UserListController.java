package user.pedro.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import user.pedro.demo.model.User;
import user.pedro.demo.service.UserService;

@Controller
public class UserListController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/userlist")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUsers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listUsers", listUsers);
		return "userlist";
	}
	
}
