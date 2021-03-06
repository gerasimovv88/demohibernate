package ru.vladigeras.demohibernate.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vladi_geras on 26.03.2019
 */
@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "orders_items",
			joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<Order> orders = new HashSet<>();

	public Item() {
	}

	public Item(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public void addToOrder(Order order) {
		if (order != null) {
			order.addItem(this);
			this.orders.add(order);
		}
	}

	public void removeFromOrder(Order order) {
		if (order != null) {
			order.removeItem(this);
			this.orders.remove(order);
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
