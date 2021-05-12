package in.shuvam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shuvam.entity.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{
	
}
