package in.shuvam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.shuvam.entity.Users;
import in.shuvam.repo.UsersRepo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/system")
public class UsersController {
	@Autowired
	private UsersRepo repo;
	private Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).getUsers()).withRel("Get all Users");
	private Users u;
	@PostMapping("/signUp")
	@ApiOperation(value="Sign up as User")
	public Users addUser(@RequestBody Users user) throws Exception {
		if(user.getRole().equals("ROLE_ADMIN"))
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"You cannot sign up as Admin.\nTry contacting support.");
		else
		return repo.save(user);
	}
	@GetMapping("/getUsers")
	@ApiOperation(value="Get list of users and admins")
	public List<Users> getUsers(){
		return repo.findAll();
	}
	@GetMapping("/getUsers/{id}")
	@ApiOperation(value="Get user by id")
	public Users getUser(@PathVariable int id) {	
		u= repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with that id"));
		return u.add(link);
		
	}
	@DeleteMapping("/getUsers/{id}")
	@ApiOperation(value="Delete user by id")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with that id"));
		repo.deleteById(id);
		return new ResponseEntity<Object>("User deleted",HttpStatus.OK);
	}
	@PostMapping("/addAdmin")
	@ApiOperation(value="Add user with admin role")
	public Users addAdmin(@RequestBody Users user) {
		u= repo.save(user);
		return u.add(link);
	}
	
}
