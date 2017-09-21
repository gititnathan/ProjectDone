package Discount;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DiscountEvent {
	
	
	public DiscountEvent(ServerPos.Server ser){
		ser.discount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int price = Integer.parseInt(ser.totalTF.getText()); // text�ȿ� �ִ� String�� a��� ������ integer�� ����ش�.
					new Discount.DiscountDialog(ser, price).setVisible(true);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(ser, "������ ����� �����ϴ�");
				}
			


			}
		}); // ���� ��ư�� ���� �̺�Ʈ
	}
}
