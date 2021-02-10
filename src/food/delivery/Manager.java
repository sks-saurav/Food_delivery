package food.delivery;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	
	private Map<Integer, User> user_db;
	private Map<String, Restaurant> restaurant_db;
	
	Manager(){
		user_db = new HashMap<>();
		restaurant_db = new HashMap<>();
	}
	
	public Restaurant registerRestaurant(String name) {
		int id = user_db.size()+1;
		Restaurant res = new Restaurant(id, name);
		restaurant_db.put(name, res);
		return res;
	}
	
	public Restaurant getRestaurant(String name) {
		return restaurant_db.getOrDefault(name, null);
	}
	
	public Map<String, Restaurant> getAllRes(){
		return restaurant_db;
	}

	public Map<Integer, User> getAllUser() {
		return user_db;
	}

	public User registerUser(String name) {
		int id = user_db.size()+1;
		User user = new User(id, name);
		user_db.put(id, user);
		return  user;
	}
	
	public User getUser(int id) {
		return user_db.getOrDefault(id, null);
	}
	
	public String placeOrder(User user, Dish dish,Restaurant res) {
		
		Order order = new Order(dish.getId(), dish.getName(), dish.getPrice(), res, user);
		
		if(res.addOrder(order)) {
			user.addOrder(order);
			return order.getStatus().toString();
		}
		
		return "Sorry, order couldn't be processed :(";
	}
}
