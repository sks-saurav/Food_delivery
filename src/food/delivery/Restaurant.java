package food.delivery;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private int id;
	private String name;
	private float ratings;
	private List<Dish> menu;
	private String address;
	private int max_order_capacity=30;
	private int order_count=0;
	private List<String> tag;
	private int delivery_time;
	private List<Order> all_order;
	
	Restaurant(int id, String name){
		this.id = id;
		this.name = name;
		menu = new ArrayList<>();
		tag = new ArrayList<>();
		all_order = new ArrayList<>();
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
	
	public void setAddress(String add) {
		address = add;
	}
	
	public void addNewDish(Dish newDish) {
		menu.add(newDish);
	}
	
	public List<Dish> getMenu(){
		return menu;
	}
	
	public void setTags(String t) {
		tag.add(t);
	}
	
	public List<String> getTags(){
		return tag;
	}
	
	public void setDeliveryTime(int time) {
		delivery_time = time;
	}
	
	public int getDeliveryTime() {
		return delivery_time;
	}
	
	public void setRating(float rat) {
		ratings = rat;
	}
	
	public boolean addOrder(Order order) {
		if(max_order_capacity <= order_count)
			return false;
		all_order.add(order);
		order_count++;
		return true;
	}
	
	public List<Order> getallOrders(){
		return all_order;
	}

	public Dish getDish(int dish_id){
		for(Dish d : menu){
			if(d.getId() == dish_id)
				return d;
		}
		return  null;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name : "+name);
		sb.append(" id : "+id);
		sb.append(" Rating : "+ratings);
		return sb.toString();
	}
}
