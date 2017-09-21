package Point;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MembershipEvent {
	
	
	public MembershipEvent(ServerPos.Server ser){
		ser.point.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int price = Integer.parseInt(ser.totalTF.getText()); // text안에 있는 String을 a라는 변수에 integer로 담아준다.
					new PointDialog(ser, price).setVisible(true);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(ser, "할인할 목록이 없습니다");
				}
			


			}
		}); // 할인 버튼에 대한 이벤트
	}
}

