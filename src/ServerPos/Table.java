package ServerPos;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Table {
	Server ser;

	public Table(Server ser){
		this.ser = ser;

		for(int i = 0; i<ser.tableSeat.length;i++){
			ser.tableSeat[i] = new JButton(new ImageIcon(getClass().getResource("/image/table.png")));
		}

		for(int i = 0;i<ser.tableSeat.length;i++){
			ser.tableGrid.add(ser.tableSeat[i]);
		}
		ser.add(ser.tableGrid);
		ser.tableGrid.setBounds(1, 101, 850, 515);

		this.mousEvent();
	}

	private void mousEvent(){ //버튼 마우스 손가락 
		ser.tableSeat[0].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[0].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[1].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[1].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[1].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		
		ser.tableSeat[2].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[2].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		
		ser.tableSeat[3].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[3].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[3].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[4].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[4].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[4].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[5].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[5].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[5].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[6].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[6].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[6].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[7].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[7].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[7].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[8].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[8].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[8].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[9].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[9].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[9].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.tableSeat[10].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[10].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[10].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		ser.tableSeat[11].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[11].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[11].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		ser.tableSeat[12].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[12].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[12].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		ser.tableSeat[13].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[13].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[13].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		ser.tableSeat[14].addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.tableSeat[14].setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.tableSeat[14].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		//-- 정산 매출현황 할인 포인트 영수증 종료 버튼 ----------- 
		
		ser.calculate.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.calculate.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.calculate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.sales.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.sales.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.sales.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.discount.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.discount.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.discount.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.point.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.point.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.point.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.receipt.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.receipt.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.receipt.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.theEnd.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.theEnd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.theEnd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		//---완료 취소 ---------------
		
		ser.complete.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.complete.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.complete.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		ser.cancel.addMouseListener(new MouseAdapter() { // memDiscount라는 버튼에다가 마우스 리스너 넣어준거.
			public void mouseEntered(MouseEvent e) {
				ser.cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				ser.cancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		
	}
}
