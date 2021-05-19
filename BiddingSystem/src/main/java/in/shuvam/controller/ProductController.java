package in.shuvam.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shuvam.entity.Products;
import in.shuvam.service.ProductService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;
	private Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProducts()).withRel("Get all products");
	Products p;
	@PostMapping("/add")
	@ApiOperation(value="Add new product")
	public Products addProduct(@RequestBody Products product) {
		 p=service.newProduct(product);
		return p.add(link);
	}

	@PostMapping("/addproducts")
	@ApiOperation(value="Add List of products")
	public List<Products> addProducts(@RequestBody List<Products> product) {
		List<Products> p= service.newProducts(product);
		p.forEach(i->i.add(link));
		return p;
	}

	@GetMapping("/{id}")
	@ApiOperation(value= "Get product by id")
	public Products getProduct(@PathVariable int id) {
		p= service.getProduct(id);
		return p.add(link);
	}

	@GetMapping("/getall")
	@ApiOperation(value="Get all the products")
	public List<Products> getProducts() {
		return service.getProducts();
	}

	@PostMapping("/{id}/bid/{bid}")
	@ApiOperation(value="State the id and the amount you want to bid")
	public Products setBid(@PathVariable int id, @PathVariable double bid,Principal principal) throws Exception {
		p=service.getProduct(id);
		p.setCurrent_bidder(principal.getName());
		return service.setBid(id, bid);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value="Delete product by id")
	public Link deleteProduct(@PathVariable int id) {
		Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProducts()).withRel("Product removed.Get updated products");
		service.deleteProduct(id);
		return link;
	}

}
