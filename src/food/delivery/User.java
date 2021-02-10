package food.delivery;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private String contact_no;
	private String address;
	private List<Order> all_orders;
	
	User(int id, String name){
		this.id = id;
		this.name = name;
		all_orders = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setContact(String str) {
		contact_no = str;
	}
	
	public void setAddress(String addr) {
		address = addr;
	}
	
	public void addOrder(Order order) {
		all_orders.add(order);
	}
	
	public List<Order> getAllOrders(){
		return all_orders;
	}

	@Override
	public String toString(){
		return id+" "+name;
	}
}
