package Calculate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CalculateEvent {

	public CalculateEvent(ServerPos.Server ser){
		ser.calculate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Calculate(ser).setVisible(true);
			}
		}); // ���� ��ư�� ���� �̺�Ʈ
	}
}
