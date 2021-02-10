package food.delivery;

public class Dish {

	protected int id;
	protected String name;
	protected int price;

	Dish(){

	}

	Dish(int id, String name, int price){
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
	
	public int gerPrice() {
		return price;
	}

	@Override
	public String toString() {
		return id+". "+name+" - Rs."+price;
	}
}
