package in.shuvam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.shuvam.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{
	@Query("Select u from Users u where u.username= :username")
	public Users getByUsername(@Param("username") String username);

}
