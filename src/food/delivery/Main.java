package food.delivery;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class Main {

	public static Manager manager;
	public static  Scanner sc;

	public static void main(String[] args) {
		
		manager = new Manager();
		sc = new Scanner(System.in);
		register_demo_restaurant(manager);
		register_demo_user(manager);

		//command line interaction initialization
		while(true){
			System.out.println("----------Food Delivery System______________\nPress A: Customer" +
					"\nPress B: new Restaurant Registration\nPress C: Quit");
			String choice = sc.next();
			if(choice.equalsIgnoreCase("A")){
				user_is_buyer();
			} else if(choice.equalsIgnoreCase("B")){
				register_new_restaurant();
			}else{
				break;
			}
		}
		sc.close();

	}
	public static void register_new_restaurant(){

		System.out.println("\n\nEnter Restaurant name (use _ insead of space):");
		String rname = sc.next();
		Restaurant res = manager.registerRestaurant(rname);
		System.out.println("Enter address (use _ insead of space):");
		res.setAddress(sc.next());
		res.setRating(4.0f);
		System.out.println("Enter menu :");

		while (true){

			System.out.println("enter item name (or type quit):");
			String item_name = sc.next();
			if(item_name.equalsIgnoreCase("quit"))
				break;
			System.out.println("price:");
			int item_price = Integer.parseInt(sc.next());
			Dish dish = new Dish(res.getMenu().size()+1, item_name, item_price);
			res.addNewDish(dish);
			System.out.println(dish.toString()+"\n");

		}
		System.out.println("\n\nRegistration Successful!!\n\n");
	}

	public static  void user_is_buyer(){
		System.out.println("------------User Menu____________________\nPress A: Existing user login\n" +
				"Press B: new user rgister");
		String choice = sc.next();
		if(choice.equalsIgnoreCase("B")){

			register_single_user();

		} else if(choice.equalsIgnoreCase("A")) {

			System.out.print("enter your id:");
			int sc_id = Integer.parseInt(sc.next());
			User user = manager.getUser(sc_id);

			if (user == null) {
				System.out.println("You are not registered!!");
				return;
			}

			while(true){

				System.out.println("\n\nHello "+user.getName());
				System.out.println("A : see all local restaurant name\nB: see menu of a rastaurant\n" +
						"C: Order\nD : manage your Profile\nE : view all My orders\nF :return to main menu");
				choice = sc.next();
				if(choice.equalsIgnoreCase("A")){
					for(Restaurant res : manager.getAllRes().values()){
						System.out.println(res.toString());
					}
				} else if(choice.equalsIgnoreCase("B")){
					display_menu();
				} else if(choice.equalsIgnoreCase("C")){
					make_order(user);
				} else if(choice.equalsIgnoreCase("D")){
					update_user_profile(user);
				}else if(choice.equalsIgnoreCase("E")){
					view_user_orders(user);
				}
				else {
					break;
				}
			}
		}
	}

	public static void display_menu(){
		System.out.println("Enter Restaurant name: ");
		String choice = sc.next();
		Restaurant res = manager.getRestaurant(choice);
		if(res != null) {
			for(Dish dish : res.getMenu()){
				System.out.println(dish.toString());
			}
		} else {
			System.out.println("no such restaurant found :(");
		}
	}

	public static void view_user_orders(User user){
		for(Order ord : user.getAllOrders()){
			System.out.println(ord.toString());
		}
	}

	public static  void  update_user_profile(User user){
		System.out.println("Enter your name :");
		String name = sc.next();
		user.setName(name);
		System.out.println("Enter contact no :");
		String contact = sc.next();
		user.setContact(contact);
		System.out.println("Enter address :");
		String address = sc.next();
		user.setAddress(address);
	}

	public static void make_order(User user){
		System.out.println("Enter Restaurant name and Dish id:");
		String res_name = sc.next();
		int dish_id = Integer.parseInt(sc.next());
		Restaurant res = manager.getRestaurant(res_name);
		manager.placeOrder(user, res.getDish(dish_id), res);
		System.out.println("order placed Sussessfully");
	}


	public  static  void  register_single_user(){
		System.out.println("Provide following details to register:");
		System.out.println("Enter your name :");
		String name = sc.next();
		User user = manager.registerUser(name);
		System.out.println("Enter contact no :");
		String contact = sc.next();
		user.setContact(contact);
		System.out.println("Enter address :");
		String address = sc.next();
		user.setAddress(address);
		System.out.println("Successfully!! registered\nYour id is :"+user.getId());
	}
	
	public static void register_demo_restaurant(Manager op){
		
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		CSVParser parser = null;
		try {
			parser = new CSVParser(new FileReader("src/food/delivery/restaurant_details.csv"), format);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(CSVRecord record : parser) {
			
			String str = "";
			op.registerRestaurant(str = record.get("name"));
			Restaurant res = op.getRestaurant(str);
			
			res.setRating(Float.parseFloat(record.get("rating")));
			
			String tagss = record.get("tags");
			for(String arr : tagss.split(",")) {
				res.setTags(arr);
			}
			
			
			res.setDeliveryTime(Integer.parseInt(record.get("delivery_time")));
			
			int i = 1;
			String menus = record.get("menu");
			for(String food : menus.split(",")) {
				String[] foods = food.split("-");
				Dish dsh = new Dish(i++, foods[0], Integer.parseInt(foods[1]));
				res.addNewDish(dsh);
			}
			
			res.setAddress(record.get("address"));
			
		}

	}

	public static void register_demo_user(Manager op){

		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		CSVParser parser = null;
		try {
			parser = new CSVParser(new FileReader("src/food/delivery/user_detail.csv"), format);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for(CSVRecord record : parser) {

			String str = "";
			User user = op.registerUser(record.get("name"));
			user.setContact(record.get("contact"));
			user.setAddress(record.get("address"));

		}

	}

}
