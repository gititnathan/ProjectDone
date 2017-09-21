package Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountMain {

	public AccountMain(ServerPos.Server ser){
		ser.sales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AccountUI(ser).setVisible(true);
				
			}
		});
	}
}
