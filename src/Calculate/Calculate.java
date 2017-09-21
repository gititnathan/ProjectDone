package Calculate;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Account.AccountMain;
import Account.AccountUI;

public class Calculate extends JDialog{
	
	ServerPos.Server ser;
	Account.DBManager db = new Account.DBManager();  
	
	private JLabel posMoney = new JLabel("���� �ݾ�");
	private JLabel calculate = new JLabel("���� �ݾ�");
	private JTextField money = new JTextField();
	private JTextField calculateMoney = new JTextField();
	private JButton complete = new JButton("���� �Ϸ�");
	private JButton close = new JButton("�ݱ�");
	
	private void comInit(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 
		c.insets = new Insets(10,10,0,0);
		
		money.setText(ser.posMoney+"");
		c.gridy = 1;
		this.add(posMoney, c);
		this.add(money, c);
		this.money.setPreferredSize(new Dimension(100, 25));
		
		int mon = ser.posMoney - 100000;
		
		calculateMoney.setText(mon+"");
		try {
			db.insertAllPrice(mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		c.gridy = 2;
		this.add(calculate, c);
		this.add(calculateMoney, c);
		this.calculateMoney.setPreferredSize(new Dimension(100, 25));
		
		c.gridy = 3;
		this.add(complete, c);
		this.add(close, c);
		this.close.setPreferredSize(new Dimension(70, 24));
	}
	
	private void event(){
		complete.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(ser.posMoney>100000){
					ser.posMoney = 100000;
					money.setText(ser.posMoney+"");
					calculateMoney.setText("0");
				}else{
					JOptionPane.showMessageDialog(ser, "10���� �����Դϴ�");
				}
				
			}
		});
		
		close.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public Calculate(ServerPos.Server ser){
		this.ser = ser;
		this.setTitle("����");
		this.setSize(300,200);
		this.setLocationRelativeTo(ser);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.comInit();
		this.event();
		this.setVisible(true);
	}
}
