package ServerPos;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ReceiptFile.ReceiptDialog;

public class Server extends JFrame{

	public final Server self = this;

	//������ �ν��Ͻ�-------
	private ReceiptFile.DBManager manager = new ReceiptFile.DBManager();
	private StringBuilder sb = new StringBuilder();
	
	//������Ȳ �ν��Ͻ�------
	private Account.Sum sum = new Account.Sum();
	
	//����Ʈ ���� ���� �ڵ� -------
	public int stopMDC = 0;
	public int stopEDC = 0;
	public int stopAdd = 0;
	
	//�Ǿ� �ν��Ͻ�--------------
	final PopupMenu pMenu = new PopupMenu(); //final�� �ؾ� �̺�Ʈ �ڵ鷯 ���డ��
	MenuItem menu = new MenuItem("�޴�����");

	public int posMoney = 100000;  //���� �ݾ�

	int totalMoney;    //���ձ�
	int receivedMoney; //������
	int changesMoney;  //�ܵ�

	public String tableNum = null; //�Ǿ� �� ���̺� �̺�Ʈ ���̺� �ѹ�   
	int tableImage;			       //���̺� �̹��� ���� ��ȣ	
	int completeNum = 0;	       //���Ϸ� Ȯ�� ��ȣ

	int total = 0;
	
	private MultiThread mt;  
	
	//�������̺�-------------
	DefaultTableModel model;
	JTable list;
	JScrollPane js;
	private String[] menulist = new String[] {"����","����","����"};
	//---------------------

	//���̺� ��ư------------------------------------
	JButton[] tableSeat = new JButton[15];
	JPanel tableGrid = new JPanel(new GridLayout(3, 5));
	//--------------------------------------------

	//��� ��---------------------------------------
	private JLabel totalLb = new JLabel("��   �� : ");
	private JLabel moneyReceived  = new JLabel("������ : ");
	private JLabel changes = new JLabel("��   �� : ");
	private JPanel labelPanel = new JPanel(new GridLayout(3, 1));
	//----------------------------------------------

	//��� �ʵ�---------------------------------------------
	public JLabel totalTF = new JLabel(); //�����ʵ�
	JTextField moneyTF = new JTextField();//�������ʵ�	
	JLabel changesTF = new JLabel();      //�ܵ��ʵ�
	private JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
	//---------------------------------------------------

	//�Ϸ� ��� ��ư-----------------------------------------------
	public JButton complete = new JButton(new ImageIcon(getClass().getResource("/image/completion.png")));//�Ϸ�
	public JButton cancel = new JButton(new ImageIcon(getClass().getResource("/image/clear.png")));	      //���
	private JPanel panel = new JPanel(new GridLayout(1, 2));
	//---------------------------------------------------------
	
	//���-------------------------------
	private JLabel banner = new JLabel(new ImageIcon(getClass().getResource("/image/banner.png")));
	
	//���� ������Ȳ ���� ����Ʈ ������ ���� ��ư--------------------------
	public JButton calculate = new JButton(new ImageIcon(getClass().getResource("/image/calculate.png")));//����
	public JButton sales = new JButton(new ImageIcon(getClass().getResource("/image/sales.png")));        //���� ��Ȳ
	public JButton discount = new JButton(new ImageIcon(getClass().getResource("/image/discount.png")));  //����
	public JButton point = new JButton(new ImageIcon(getClass().getResource("/image/point.png")));        //����Ʈ
	public JButton receipt = new JButton(new ImageIcon(getClass().getResource("/image/receipt.png")));    //������
	public JButton theEnd = new JButton(new ImageIcon(getClass().getResource("/image/theend.png")));      //����
	private JPanel btnPanel = new JPanel(new GridLayout(1, 6));

	//�ؽ���--------------------------------------
	private HashMap<String, ArrayList<Order>> foodlist = new HashMap<String, ArrayList<Order>>();

	public void setMenulist(String a, ArrayList<Order> b){
		foodlist.put(a, b);
	}
	public ArrayList<Order> getMenulist(String num){ // �ֹ��� ���� ����Ʈ
		return foodlist.get(num);  
	}

	public HashMap<String, ArrayList<Order>> getFooldList(){ //�߰� �ֹ� Ȯ�� �޼���
		return foodlist;  
	} 
	//-------------------------------------------------
	
	private void table(){  //���̺��ġ
		new Table(self);
	}

	private void allEvent(){
		new TableEvent(self);              //���̺� ��ȣ
		new PopupEvent(self);              // �Ǿ�â 
		new Discount.DiscountEvent(self);  //����
		new Calculate.CalculateEvent(self);//����
		new Point.MembershipEvent(self);   //����Ʈ
		new ReceiptFile.ReceiptMain(self); // ������
		new Account.AccountMain(self);	   //������Ȳ

		//���� �̺�Ʈ --------------------------------------
		theEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	//��¥ �ð� -----------------------------
	private void time(){ 
		new Time.TimeBar(self);
	}

	//��� �� ���̺� �ʱ�ȭ �Ϸ� ��� �̺�Ʈ-------------------------------------------
	private void completeCancelEvent(){   
		moneyTF.addActionListener(new ActionListener() {  //���
			public void actionPerformed(ActionEvent e) {
				if(completeNum == 1){
					try{
						totalMoney = Integer.parseInt(totalTF.getText()); //���ձ�
						receivedMoney = Integer.parseInt(moneyTF.getText()); //������
						changesMoney = receivedMoney - totalMoney;  //�ܵ�
						changesTF.setText(""+changesMoney);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(self, "�߸��Է��Ͽ����ϴ�");
					}
				}else{
					JOptionPane.showMessageDialog(self, "��긮��Ʈ�� �����ϴ�");
				}

			}
		});

		//�Ϸ� ��ư---------------------------------------------------------------
		complete.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try{
					// ������--------------------
					for(Order tmp : foodlist.get(tableNum)) {
						sb.append(tmp.getMenu());
						sb.append(":");
						sb.append(tmp.getQuantity());
						sb.append(":");
						sb.append(tmp.getPrice()*tmp.getQuantity());
						sb.append(":");
						total += tmp.getPrice()*tmp.getQuantity();
					}

					String order = sb.toString();
					ReceiptFile.Receipt tmp = new ReceiptFile.Receipt(Integer.parseInt(tableNum),order,total,receivedMoney,changesMoney);
					int insertResult = self.manager.insertOrder(tmp);

					totalMoney = Integer.parseInt(totalTF.getText()); //���ձ�
					receivedMoney = Integer.parseInt(moneyTF.getText()); //������

					if(totalMoney <= receivedMoney){
						//���� ��Ȳ---------------------------
						for(Order tmp1 : foodlist.get(tableNum)){	
							sum.getList(tmp1.getMenu(), tmp1.getQuantity());
						}
						//---------------------------------
						if(completeNum == 1){
							int del = model.getRowCount();
							for(int i = 0;i<del;i++){
								model.removeRow(0);
							}
							total = Integer.parseInt(totalTF.getText());
							posMoney += total;
							totalTF.setText("");
							moneyTF.setText("");
							changesTF.setText("");
							total = 0;

							tableSeat[self.tableImage].setIcon(new ImageIcon(getClass().getResource("/image/table.png")));
							foodlist.remove(tableNum);
						}
						completeNum = 0;
					}else{
						moneyTF.setText("");
						changesTF.setText("");
						JOptionPane.showMessageDialog(self, "���� ���� �ʽ��ϴ�");
					}
				}catch (Exception e2) {
					moneyTF.setText("");
					changesTF.setText("");
					JOptionPane.showMessageDialog(self, "�߸��Է��Ͽ��ų� �Է����� �ʾҽ��ϴ�");
					e2.printStackTrace();
				}


			}
		});

		//��� ��ư --------------------------------------------------
		cancel.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				int del = model.getRowCount();
				for(int i = 0;i<del;i++){
					model.removeRow(0);
				}
				totalTF.setText("");
				moneyTF.setText("");
				changesTF.setText("");
				completeNum = 0;
			}
		});

	} 

	private void comInit(){
		this.setLayout(null);
		model = new DefaultTableModel(menulist,0);
		list = new JTable(model);
		js = new JScrollPane(list);

		this.add(js);
		this.js.setBounds(851, 101, 339, 300);
		this.js.setFont(new Font("���� ���", Font.BOLD, 20));
		//��� ���̺� ���� ����
		this.list.getColumnModel().getColumn(0).setPreferredWidth(250);
		this.list.getColumnModel().getColumn(1).setPreferredWidth(40);

		//��� ���̺� ���� ����
		DefaultTableCellRenderer tableRender = new DefaultTableCellRenderer();
		tableRender.setHorizontalAlignment(JLabel.CENTER);
		list.getColumnModel().getColumn(0).setCellRenderer(tableRender);
		list.getColumnModel().getColumn(1).setCellRenderer(tableRender);
		list.getColumnModel().getColumn(2).setCellRenderer(tableRender);

		//���� ������ �ܵ�-------------------------------------------
		totalLb.setHorizontalAlignment(JLabel.LEFT);
		moneyReceived.setHorizontalAlignment(JLabel.LEFT);
		changes.setHorizontalAlignment(JLabel.LEFT);

		this.labelPanel.add(totalLb);
		this.totalLb.setFont(new Font("���� ���", Font.BOLD, 20));
		this.labelPanel.add(moneyReceived);
		this.moneyReceived.setFont(new Font("���� ���", Font.BOLD, 20));
		this.labelPanel.add(changes);
		this.changes.setFont(new Font("���� ���", Font.BOLD, 20));

		this.fieldPanel.add(totalTF);
		this.totalTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.totalTF.setOpaque(true);
		this.totalTF.setBackground(Color.white);
		this.totalTF.setFont(new Font("���� ���", Font.BOLD, 25));
		this.fieldPanel.add(moneyTF);
		this.moneyTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.moneyTF.setFont(new Font("���� ���", Font.BOLD, 25));
		this.fieldPanel.add(changesTF);
		this.changesTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.changesTF.setOpaque(true);
		this.changesTF.setBackground(Color.white);
		this.changesTF.setFont(new Font("���� ���", Font.BOLD, 25));

		this.add(labelPanel);
		this.add(fieldPanel);
		this.labelPanel.setBounds(855, 401, 80, 150);  //���� ������ �ܵ� �� ���� 
		this.fieldPanel.setBounds(940, 403, 250, 150);	 //���� ������ �ܵ� �ʵ� ����
		//------------�Ϸ� ���
		this.panel.add(complete);
		this.panel.add(cancel);
		this.add(panel);  
		this.panel.setBounds(853, 555, 337, 60);  //�Ϸ� ���
		//-----------���� ������ ���� ����
		this.btnPanel.add(calculate); //����
		this.btnPanel.add(sales);     //������Ȳ
		this.btnPanel.add(discount);  //����
		this.btnPanel.add(point);     //����Ʈ
		this.btnPanel.add(receipt);   //������
		this.btnPanel.add(theEnd);    //����
		this.add(btnPanel);
		this.btnPanel.setBounds(0, 616, 1191, 40); //���� ������ ��� ���� ����
		//---�ΰ�
		this.add(banner);
		this.banner.setBounds(0, 0, 1190, 100);
		this.banner.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.banner.setOpaque(true); //Opaque���� true�� �̸� ������ �־�� ������ ����ȴ�
		this.banner.setBackground(Color.white);
	}

	public Server() throws Exception{
		this.setTitle("BLACKSTONE");
		this.setSize(1197,715);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.time();
		this.table();
		this.allEvent();
		this.completeCancelEvent();
		this.comInit();
		this.setVisible(true);
		this.setResizable(false);

		ServerSocket server = new ServerSocket(20000);

		while (true) {
			Socket sock = server.accept(); // ��Ʈ��ũ���� 20000��Ʈ�� ������ ������ ���
			mt = new MultiThread(self,sock);
			mt.start();
		}
	}

	public static void main(String[] args) throws Exception{
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			new Server();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
}