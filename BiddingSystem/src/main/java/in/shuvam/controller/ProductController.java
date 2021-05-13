package in.shuvam.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shuvam.entity.Products;
import in.shuvam.exception.ProductNotFound;
import in.shuvam.service.ProductService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/add")
	@ApiOperation(value="Add new product")
	public Products addProduct(@RequestBody Products product) {
		return service.newProduct(product);
	}

	@PostMapping("/addProducts")
	@ApiOperation(value="Add List of products")
	public List<Products> addProducts(@RequestBody List<Products> product) {
		return service.newProducts(product);
	}

	@GetMapping("/{id}")
	@ApiOperation(value= "Get product by id")
	public Products getProduct(@PathVariable int id) {
		return service.getProduct(id);
	}

	@GetMapping("/getall")
	@ApiOperation(value="Get all the products")
	public List<Products> getProducts() {
		return service.getProducts();
	}

	@PostMapping("/{id}/bid/{bid}")
	@ApiOperation(value="State the id and the amount you want to bid")
	public Products setBid(@PathVariable int id, @PathVariable double bid,Principal principal) throws Exception {
		Products p=service.getProduct(id);
		p.setCurrent_bidder(principal.getName());
		return service.setBid(id, bid);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value="Delete product by id")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

}
