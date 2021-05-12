package in.shuvam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shuvam.entity.Products;
import in.shuvam.exception.BidException;
import in.shuvam.exception.ProductNotFound;
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

	public String deleteProduct(int id) {
		repo.deleteById(id);
		return "Removed";
	}

	public Products getProduct(int id) {
		return repo.findById(id).orElse(null);
		
	}

	public Products setBid(int id, double bid) throws Exception {
		Products p = repo.findById(id).orElse(null);
		if (p == null)
			throw new ProductNotFound();
		if (bid > p.getCurrent_bid()) {
			p.setCurrent_bid(bid);
			return repo.save(p);
		} else
			throw new BidException();

	}

}
