package ServerPos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class TableEvent {
	Server ser;

	public TableEvent(Server ser) {
		this.ser = ser;

		ser.tableSeat[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("1")) {
							ser.model.addRow(new Object[] {tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity()});
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 0;
						ser.tableNum = "1";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
						ser.stopAdd = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}

			}
		});

		ser.tableSeat[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("2")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 1;
						ser.tableNum = "2";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}

			}
		});

		ser.tableSeat[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("3")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 2;
						ser.tableNum = "3";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("4")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 3;
						ser.tableNum = "4";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("5")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 4;
						ser.tableNum = "5";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("6")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 5;
						ser.tableNum = "6";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("7")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 6;
						ser.tableNum = "7";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("8")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 7;
						ser.tableNum = "8";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("9")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 8;
						ser.tableNum = "9";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("10")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 9;
						ser.tableNum = "10";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("11")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 10;
						ser.tableNum = "11";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[11].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("12")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 11;
						ser.tableNum = "12";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("12")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 12;
						ser.tableNum = "13";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[13].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("13")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 13;
						ser.tableNum = "14";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});

		ser.tableSeat[14].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ser.completeNum == 0){
					try {
						for (Order tmp : ser.getMenulist("14")) {
							ser.model.addRow(new Object[] { tmp.getMenu(), tmp.getQuantity(), tmp.getPrice() * tmp.getQuantity() });
							ser.total += tmp.getPrice() * tmp.getQuantity();
						}
						ser.totalTF.setText("" + ser.total);
						ser.tableImage = 14;
						ser.tableNum = "15";
						ser.total = 0;
						ser.completeNum = 1;
						ser.stopMDC = 0;
						ser.stopEDC = 0;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(ser, "주문이없습니다");
					}
				}else{
					JOptionPane.showMessageDialog(ser, "현재 테이블 계산을 완료하세요");
				}
			}
		});
	}

}