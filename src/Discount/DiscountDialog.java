package Discount;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DiscountDialog extends JDialog{

	private ServerPos.Server parent = null;
	private int price;

	private ImageIcon basicMem = new ImageIcon(getClass().getResource("/image/��������.jpg"));
	private ImageIcon basicEvn = new ImageIcon(getClass().getResource("/image/�������.jpg"));
	private ImageIcon darkMem = new ImageIcon(getClass().getResource("/image/����������.jpg"));
	private ImageIcon darkEvn = new ImageIcon(getClass().getResource("/image/���������.jpg"));

	private JButton memDiscount = new JButton(basicMem);
	private JButton eventDiscount = new JButton(basicEvn);

	private JLabel priceLabel = new JLabel("");
	private JPanel panelInput = new JPanel(new GridBagLayout());

	
	private void eventInit(){

		this.memDiscount.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				memDiscount.setIcon(darkMem);

				memDiscount.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

 

			public void mouseExited(MouseEvent e) {

				memDiscount.setIcon(basicMem);

				memDiscount.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

		});

 

		this.memDiscount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				

				if (parent.stopMDC == 1){

					JOptionPane.showMessageDialog(parent, "�̹� �ش� ������ �����̽��ϴ�.");

					

 

				} else if (parent.stopMDC == 0 || parent.stopEDC == 2 || parent.stopEDC == 0 ){

					MemberDis md = new MemberDis(price); // MemberDis �ν��Ͻ� �����ڿ� price�� �ѱ��.

					parent.totalTF.setText(""+md.returnMember());

					parent.stopMDC = 1;

					dispose();

				}

			}

		});

 

		this.eventDiscount.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				eventDiscount.setIcon(darkEvn);

				eventDiscount.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

 

			public void mouseExited(MouseEvent e) {

				eventDiscount.setIcon(basicEvn);

				eventDiscount.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

		});

 

		this.eventDiscount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (parent.stopEDC == 2){

					JOptionPane.showMessageDialog(parent, "�̹� �ش� ������ �����̽��ϴ�.");

					

				} else if (parent.stopEDC == 0 || parent.stopMDC == 0 || parent.stopMDC == 1 ){

					EventDiscount ed = new EventDiscount(price);

					parent.totalTF.setText(""+ed.returnEvent());

					parent.stopEDC = 2;

					dispose();

				}

 

			}

		});

 

	}


	private void compInit(){
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10,5,10,5);
		c.gridy = 2; c.gridx = 2;
		this.memDiscount.setBorderPainted(false);
		this.memDiscount.setContentAreaFilled(false);
		this.panelInput.add(memDiscount,c);

		this.memDiscount.setPreferredSize(new Dimension(130,130));
		c.gridy = 2; c.gridx = 1;

		this.eventDiscount.setBorderPainted(false);
		this.eventDiscount.setContentAreaFilled(false);
		this.panelInput.add(eventDiscount,c);
		this.eventDiscount.setPreferredSize(new Dimension(130,130));

		this.add(panelInput);
	}

	public DiscountDialog(ServerPos.Server parent, int price){
		this.parent = parent;
		this.price = price;
		this.setSize(300,200);
		this.setLocation(1075,225);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();

	}
}
