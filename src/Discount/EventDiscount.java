package Discount;

import javax.swing.JOptionPane;

public class EventDiscount {
	private int price;
	public EventDiscount(int price){
		this.price = price;
		
	}
	public int returnEvent(){
		if(price>= 10000){
			price = price - 1000;
			
		} else {
			JOptionPane.showMessageDialog(null, "행사할인 대상이 아닙니다.");
		}
		return price;
	}
}
