package food.delivery;

public class Order extends Dish{

	private User user;
	private Restaurant restaurant;
	private Status status;

	Order(){

	}

	Order(int id, String name, int price, Restaurant res, User us){
		super(id, name, price);
		restaurant = res;
		user = us;
		status = Status.ORDER_PLACED;
	}
	
	public String getRestaurantDetails() {
		return restaurant.toString();
	}
	
	public void setStatus(Status st) {
		status = st;
	}
	
	public Status getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return super.toString()+" "+status+"\nRestaurant"+restaurant.getName()+" Customer: "+user.getName();
	}
}

