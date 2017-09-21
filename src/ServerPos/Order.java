package ServerPos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Order implements Serializable {
	//private static final long serialVersionUID = 2478671524360384162L;

	private String menu;

	private int quantity;

	private int price;

	private ArrayList<Order> subMenu = new ArrayList<Order>();


	public Order(String menu, int quantity, int price) {
		this.menu = menu;
		this.quantity = quantity;
		this.price = price;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) { // 리턴값이 없는 setPrice라는 메서드를 선언하고, int값을 담는 price라는 파라미터 변수를 인자로 받는다.
		this.price = price; // 이 클래스로 생성된 객체 안의 price 변수에 price 파라미터 변수를 저장.
	}

	public int getTotalPrice() { // int값을 리턴값으로 담는 getTotalPrice라는 메서드를 선언.
		return getPrice() * getQuantity(); // 이 메서드를 호출한 곳에 getPrice() * getQuantity() 값을 리턴. 
	}

	public String printPrice() {
		return this.menu + " " + priceFormat(getPrice()) + "원";
	}

	public ArrayList<Order> getSubMenu() {
		return subMenu;
	}

	public String priceFormat(int price){
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
		return format.format(price);
	}
}