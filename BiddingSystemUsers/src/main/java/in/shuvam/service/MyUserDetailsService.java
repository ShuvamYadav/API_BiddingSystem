package in.shuvam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.shuvam.entity.Users;
import in.shuvam.repo.UsersRepo;

public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UsersRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user= repo.getByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Username not found");
		return new UserDetailsServ(user);
	}

}
