package in.shuvam.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity
public class ProductSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource datasource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(passwordencoder())
		.usersByUsernameQuery("Select username,password,enabled from users where username = ?")
		.authoritiesByUsernameQuery("Select username,role from users where username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/products/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/products/{id}/bid/{bid}").hasRole("USER")
		.antMatchers(HttpMethod.DELETE,"/products/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/prodcuts/**").hasAnyRole("USER","ADMIN")
		.anyRequest().permitAll().and()
		.httpBasic();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
