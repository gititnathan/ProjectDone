package Account;

import java.util.ArrayList;

import ServerPos.Order;
import ServerPos.Server;

public class Sum {
	public static Account.SellMenu sell = new SellMenu();
	private ServerPos.Server server;
	private Account.AccountUI account;
	private String menu1 = "������ũ���Ľ�Ÿ";
	private String menu2 = "�����̽÷����Ľ�Ÿ";
	private String menu3 = "ĥ���������丶���Ľ�Ÿ";
	private String menu4 = "������콺����ũ";
	private String menu5 = "����ä�콺����ũ";
	private String menu6 = "����ġ�콺����ũ";
	private String menu7 = "�븶�ú���������";
	private String menu8 = "����Ÿġ�������"; 
	private String menu9 = "����Ÿġ�������(with ġ�ƹ�Ÿ)";


	public void getList(String m, int c) {  //����üũ �޼���
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
	
	public void geTotalPrice(String tp) {   //���� �� �ݾ� �ż���
		String totalPrice  = sell.getTotalPrice()+tp;
		sell.setTotalPrice(totalPrice);
	}
}

