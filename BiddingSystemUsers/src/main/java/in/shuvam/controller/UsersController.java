package in.shuvam.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.shuvam.entity.Users;
import in.shuvam.exception.IdException;
import in.shuvam.exception.RoleException;
import in.shuvam.repo.UsersRepo;

@RestController
public class UsersController {
	@Autowired
	private UsersRepo repo;
	
	@PostMapping("/signUp")
	public Users addUser(@RequestBody Users user) throws Exception {
		if(user.getRole().equals("ROLE_ADMIN"))
			throw new RoleException();
		else
		return repo.save(user);
	}
	@GetMapping("/getUsers")
	public List<Users> getUsers(){
		return repo.findAll();
	}
	@GetMapping("/getUsers/{id}")
	public Users getUser(@PathVariable int id) {
		return repo.findById(id).orElseThrow(()-> new IdException());
	}
	@DeleteMapping("/getUsers/{id}")
	public String delete(@PathVariable int id) {
		repo.deleteById(id);
		return "User deleted!";
	}
	@PostMapping("/addAdmin")
	public Users addAdmin(@RequestBody Users user) {
		return repo.save(user);
	}
	
}
