package ServerPos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class PopupEvent {
	Server ser;

	public PopupEvent(Server ser) {
		this.ser = ser;
		
		ser.tableSeat[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {// BUTTON3_MASK는 마우스 오른쪽 버튼 눌린 것
					ser.pMenu.show(ser.tableSeat[0], me.getX(), me.getY());
					ser.tableNum = "1";
				}
			}
		});

		ser.tableSeat[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[1], me.getX(), me.getY());
					ser.tableNum = "2";
				}
			}
		});
		
		ser.tableSeat[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[2], me.getX(), me.getY());
					ser.tableNum = "3";
				}
			}
		});
		
		ser.tableSeat[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[3], me.getX(), me.getY());
					ser.tableNum = "4";
				}
			}
		});
		
		ser.tableSeat[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[4], me.getX(), me.getY());
					ser.tableNum = "5";
				}
			}
		});
		
		ser.tableSeat[5].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[5], me.getX(), me.getY());
					ser.tableNum = "6";
				}
			}
		});
		
		ser.tableSeat[6].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[6], me.getX(), me.getY());
					ser.tableNum = "7";
				}
			}
		});
		
		ser.tableSeat[7].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[7], me.getX(), me.getY());
					ser.tableNum = "8";
				}
			}
		});
		
		ser.tableSeat[8].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[8], me.getX(), me.getY());
					ser.tableNum = "9";
				}
			}
		});
		
		ser.tableSeat[9].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[9], me.getX(), me.getY());
					ser.tableNum = "10";
				}
			}
		});
		
		ser.tableSeat[10].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[10], me.getX(), me.getY());
					ser.tableNum = "11";
				}
			}
		});
		
		ser.tableSeat[11].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[11], me.getX(), me.getY());
					ser.tableNum = "12";
				}
			}
		});
		
		ser.tableSeat[12].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[12], me.getX(), me.getY());
					ser.tableNum = "13";
				}
			}
		});

		ser.tableSeat[13].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[13], me.getX(), me.getY());
					ser.tableNum = "14";
				}
			}
		});
		ser.tableSeat[14].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getModifiers() == me.BUTTON3_MASK) {
					ser.pMenu.show(ser.tableSeat[14], me.getX(), me.getY());
					ser.tableNum = "15";
				}
			}
		});

		ser.menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FoodList(ser, ser.tableNum).setVisible(true);
			}
		});

		ser.pMenu.add(ser.menu);
		ser.add(ser.pMenu);
	}
}
