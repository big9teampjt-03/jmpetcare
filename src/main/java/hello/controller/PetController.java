package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.config.auth.PrincipalUser;
import hello.model.Pet;
import hello.model.User;
import hello.repository.PetRepository;
import hello.service.PetService;

@Controller
public class PetController {

	@Autowired
	private PetRepository pRepository;
	@Autowired
	private PetService pService;

	// 애완동물 등록폼
	@GetMapping("pet/{userid}")
	@PreAuthorize("isAuthenticated()")
	public String pet(@PathVariable Long userid, Model model) {
		// model.addAttribute("petid",pService.insert(pet));
		return "pet";
	}

	// 등록생성
	@PostMapping("pet")
	@ResponseBody
	public String petjoin(@RequestBody Pet pet, @AuthenticationPrincipal PrincipalUser principal) {
		User user = principal.getUser();
		pet.setUser(user);
		pService.insert(pet);
		return "success";
	}

	@GetMapping("pet")
	public String petlist() {
		return "userform";
	}
	
	
}