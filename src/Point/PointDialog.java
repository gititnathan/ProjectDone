package Point;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PointDialog extends JDialog{
	public ServerPos.Server parent = null;
	PointDialog pDialog = this;
	
	private int price;
	
	private JButton bt1 = new JButton("È¸¿ø µî·Ï");
	private JButton bt2 = new JButton("Àû¸³");
	private JButton bt3 = new JButton("»ç¿ë");
	private JButton bt4 = new JButton("¿¬¶ôÃ³ º¯°æ");
	private JPanel panel = new JPanel(new GridLayout(4, 2));
	
	private void compInit(){
	
		this.panel.add(bt1);
		this.bt1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		this.panel.add(bt2); 
		this.bt2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		this.panel.add(bt3);
		this.bt3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		this.panel.add(bt4);
		this.bt4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		this.add(panel);
		
	}
	private void eventInit(){
		this.bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinMember(pDialog).setVisible(true);
			}
		});
		this.bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPoint(pDialog).setVisible(true);
			}
		});
		this.bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UsePoint(pDialog).setVisible(true);
			}
		});
		this.bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeContact(pDialog).setVisible(true);
			}
		});
	}
	
	
	public PointDialog(ServerPos.Server parent, int price) {
		this.parent = parent;
		this.price = price;
		this.setSize(150,200);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	
}
