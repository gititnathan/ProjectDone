package Account;

import java.util.ArrayList;

import ServerPos.Order;
import ServerPos.Server;

public class Sum {
	public static Account.SellMenu sell = new SellMenu();
	private ServerPos.Server server;
	private Account.AccountUI account;
	private String menu1 = "감베리크림파스타";
	private String menu2 = "스파이시로제파스타";
	private String menu3 = "칠리쉬림프토마토파스타";
	private String menu4 = "블랙갈비살스테이크";
	private String menu5 = "블랙부채살스테이크";
	private String menu6 = "블랙살치살스테이크";
	private String menu7 = "통마늘비프샐러드";
	private String menu8 = "리코타치즈샐러드"; 
	private String menu9 = "리코타치즈샐러드(with 치아바타)";


	public void getList(String m, int c) {  //수량체크 메서드
		if(menu1.equals(m)) {
			int q = sell.getQuantity1()+c;
			sell.setQuantity1(q);
			System.out.println(sell.getQuantity1());
		}else if(menu2.equals(m)) {
			int q = sell.getQuantity2()+c;
			sell.setQuantity2(q);
		}else if(menu3.equals(m)) {
			int q = sell.getQuantity3()+c;
			sell.setQuantity3(q);
		}else if(menu4.equals(m)) {
			int q = sell.getQuantity4()+c;
			sell.setQuantity4(q);
		}else if(menu5.equals(m)) {
			int q = sell.getQuantity5()+c;
			sell.setQuantity5(q);
		}else if(menu6.equals(m)) {
			int q = sell.getQuantity6()+c;
			sell.setQuantity6(q);
		}else if(menu7.equals(m)) {
			int q = sell.getQuantity7()+c;
			sell.setQuantity7(q);
		}else if(menu8.equals(m)) {
			int q = sell.getQuantity8()+c;
			sell.setQuantity8(q);
		}else if(menu9.equals(m)) {
			int q = sell.getQuantity9()+c;
			sell.setQuantity9(q);
		}

	}
	
	public void geTotalPrice(String tp) {   //오늘 번 금액 매서드
		String totalPrice  = sell.getTotalPrice()+tp;
		sell.setTotalPrice(totalPrice);
	}
}

