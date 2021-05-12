package in.shuvam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import in.shuvam.service.MyUserDetailsService;
@Configuration
@EnableWebSecurity
public class UsersSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordencoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/addAdmin").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/signUp").permitAll()
		.and().httpBasic();
	}

}
