package ReceiptFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptMain {
	
	public ReceiptMain(ServerPos.Server ser){
		ser.receipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ReceiptDialog(ser).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
}
