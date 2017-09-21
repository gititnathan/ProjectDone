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

	public void setPrice(int price) { // ���ϰ��� ���� setPrice��� �޼��带 �����ϰ�, int���� ��� price��� �Ķ���� ������ ���ڷ� �޴´�.
		this.price = price; // �� Ŭ������ ������ ��ü ���� price ������ price �Ķ���� ������ ����.
	}

	public int getTotalPrice() { // int���� ���ϰ����� ��� getTotalPrice��� �޼��带 ����.
		return getPrice() * getQuantity(); // �� �޼��带 ȣ���� ���� getPrice() * getQuantity() ���� ����. 
	}

	public String printPrice() {
		return this.menu + " " + priceFormat(getPrice()) + "��";
	}

	public ArrayList<Order> getSubMenu() {
		return subMenu;
	}

	public String priceFormat(int price){
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
		return format.format(price);
	}
}