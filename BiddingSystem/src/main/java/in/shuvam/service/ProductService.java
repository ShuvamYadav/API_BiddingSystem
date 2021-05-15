package in.shuvam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import in.shuvam.entity.Products;
import in.shuvam.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;

	public Products newProduct(Products product) {
		return repo.save(product);
	}

	public List<Products> newProducts(List<Products> product) {
		return repo.saveAll(product);
	}

	public List<Products> getProducts() {
		return repo.findAll();
	}

	public void deleteProduct(int id) {
		Products p = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
		if(p != null)
			repo.deleteById(id);
	}

	public Products getProduct(int id) {
		return repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
		
	}

	public Products setBid(int id, double bid) throws Exception {
		Products p = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
		if (bid > p.getCurrent_bid()) {
			p.setCurrent_bid(bid);
			return repo.save(p);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Bid must be higher than current bid");

	}

}
