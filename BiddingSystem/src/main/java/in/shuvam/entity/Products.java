package in.shuvam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private double starting_bid;
	private double current_bid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCurrent_bid() {
		return current_bid;
	}

	public void setCurrent_bid(double current_bid) {
		this.current_bid = current_bid;
	}

	public double getStarting_bid() {
		return starting_bid;
	}

	public void setStarting_bid(double starting_bid) {
		this.starting_bid = starting_bid;
	}

}
