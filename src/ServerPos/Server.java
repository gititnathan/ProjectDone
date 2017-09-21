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

	//영수증 인스턴스-------
	private ReceiptFile.DBManager manager = new ReceiptFile.DBManager();
	private StringBuilder sb = new StringBuilder();
	
	//매출현황 인스턴스------
	private Account.Sum sum = new Account.Sum();
	
	//포인트 할인 관련 코드 -------
	public int stopMDC = 0;
	public int stopEDC = 0;
	public int stopAdd = 0;
	
	//판업 인스턴스--------------
	final PopupMenu pMenu = new PopupMenu(); //final로 해야 이벤트 핸들러 실행가능
	MenuItem menu = new MenuItem("메뉴보기");

	public int posMoney = 100000;  //포스 금액

	int totalMoney;    //총합금
	int receivedMoney; //받은돈
	int changesMoney;  //잔돈

	public String tableNum = null; //판업 및 테이블 이벤트 테이블 넘버   
	int tableImage;			       //테이블 이미지 변경 번호	
	int completeNum = 0;	       //계산완료 확인 번호

	int total = 0;
	
	private MultiThread mt;  
	
	//제이테이블-------------
	DefaultTableModel model;
	JTable list;
	JScrollPane js;
	private String[] menulist = new String[] {"음식","수량","가격"};
	//---------------------

	//테이블 버튼------------------------------------
	JButton[] tableSeat = new JButton[15];
	JPanel tableGrid = new JPanel(new GridLayout(3, 5));
	//--------------------------------------------

	//계산 라벨---------------------------------------
	private JLabel totalLb = new JLabel("총   합 : ");
	private JLabel moneyReceived  = new JLabel("받은돈 : ");
	private JLabel changes = new JLabel("잔   돈 : ");
	private JPanel labelPanel = new JPanel(new GridLayout(3, 1));
	//----------------------------------------------

	//계산 필드---------------------------------------------
	public JLabel totalTF = new JLabel(); //총합필드
	JTextField moneyTF = new JTextField();//받은돈필드	
	JLabel changesTF = new JLabel();      //잔돈필드
	private JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
	//---------------------------------------------------

	//완료 취소 버튼-----------------------------------------------
	public JButton complete = new JButton(new ImageIcon(getClass().getResource("/image/completion.png")));//완료
	public JButton cancel = new JButton(new ImageIcon(getClass().getResource("/image/clear.png")));	      //취소
	private JPanel panel = new JPanel(new GridLayout(1, 2));
	//---------------------------------------------------------
	
	//배너-------------------------------
	private JLabel banner = new JLabel(new ImageIcon(getClass().getResource("/image/banner.png")));
	
	//정산 매출현황 할인 포인트 영수증 종료 버튼--------------------------
	public JButton calculate = new JButton(new ImageIcon(getClass().getResource("/image/calculate.png")));//정산
	public JButton sales = new JButton(new ImageIcon(getClass().getResource("/image/sales.png")));        //매출 현황
	public JButton discount = new JButton(new ImageIcon(getClass().getResource("/image/discount.png")));  //할인
	public JButton point = new JButton(new ImageIcon(getClass().getResource("/image/point.png")));        //포인트
	public JButton receipt = new JButton(new ImageIcon(getClass().getResource("/image/receipt.png")));    //영수증
	public JButton theEnd = new JButton(new ImageIcon(getClass().getResource("/image/theend.png")));      //종료
	private JPanel btnPanel = new JPanel(new GridLayout(1, 6));

	//해쉬맵--------------------------------------
	private HashMap<String, ArrayList<Order>> foodlist = new HashMap<String, ArrayList<Order>>();

	public void setMenulist(String a, ArrayList<Order> b){
		foodlist.put(a, b);
	}
	public ArrayList<Order> getMenulist(String num){ // 주문한 음식 리스트
		return foodlist.get(num);  
	}

	public HashMap<String, ArrayList<Order>> getFooldList(){ //추가 주문 확인 메서드
		return foodlist;  
	} 
	//-------------------------------------------------
	
	private void table(){  //테이블배치
		new Table(self);
	}

	private void allEvent(){
		new TableEvent(self);              //테이블 번호
		new PopupEvent(self);              // 판업창 
		new Discount.DiscountEvent(self);  //할인
		new Calculate.CalculateEvent(self);//정산
		new Point.MembershipEvent(self);   //포인트
		new ReceiptFile.ReceiptMain(self); // 영수증
		new Account.AccountMain(self);	   //매출현황

		//종료 이벤트 --------------------------------------
		theEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	//날짜 시간 -----------------------------
	private void time(){ 
		new Time.TimeBar(self);
	}

	//계산 및 테이블 초기화 완료 취소 이벤트-------------------------------------------
	private void completeCancelEvent(){   
		moneyTF.addActionListener(new ActionListener() {  //계산
			public void actionPerformed(ActionEvent e) {
				if(completeNum == 1){
					try{
						totalMoney = Integer.parseInt(totalTF.getText()); //총합금
						receivedMoney = Integer.parseInt(moneyTF.getText()); //받은돈
						changesMoney = receivedMoney - totalMoney;  //잔돈
						changesTF.setText(""+changesMoney);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(self, "잘못입력하였습니다");
					}
				}else{
					JOptionPane.showMessageDialog(self, "계산리스트가 없습니다");
				}

			}
		});

		//완료 버튼---------------------------------------------------------------
		complete.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try{
					// 영수증--------------------
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

					totalMoney = Integer.parseInt(totalTF.getText()); //총합금
					receivedMoney = Integer.parseInt(moneyTF.getText()); //받은돈

					if(totalMoney <= receivedMoney){
						//매출 현황---------------------------
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
						JOptionPane.showMessageDialog(self, "돈이 맞지 않습니다");
					}
				}catch (Exception e2) {
					moneyTF.setText("");
					changesTF.setText("");
					JOptionPane.showMessageDialog(self, "잘못입력하였거나 입력하지 않았습니다");
					e2.printStackTrace();
				}


			}
		});

		//취소 버튼 --------------------------------------------------
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
		this.js.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		//계산 테이블 넓이 조절
		this.list.getColumnModel().getColumn(0).setPreferredWidth(250);
		this.list.getColumnModel().getColumn(1).setPreferredWidth(40);

		//계산 테이블 센터 정렬
		DefaultTableCellRenderer tableRender = new DefaultTableCellRenderer();
		tableRender.setHorizontalAlignment(JLabel.CENTER);
		list.getColumnModel().getColumn(0).setCellRenderer(tableRender);
		list.getColumnModel().getColumn(1).setCellRenderer(tableRender);
		list.getColumnModel().getColumn(2).setCellRenderer(tableRender);

		//총합 받은돈 잔돈-------------------------------------------
		totalLb.setHorizontalAlignment(JLabel.LEFT);
		moneyReceived.setHorizontalAlignment(JLabel.LEFT);
		changes.setHorizontalAlignment(JLabel.LEFT);

		this.labelPanel.add(totalLb);
		this.totalLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		this.labelPanel.add(moneyReceived);
		this.moneyReceived.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		this.labelPanel.add(changes);
		this.changes.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		this.fieldPanel.add(totalTF);
		this.totalTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.totalTF.setOpaque(true);
		this.totalTF.setBackground(Color.white);
		this.totalTF.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		this.fieldPanel.add(moneyTF);
		this.moneyTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.moneyTF.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		this.fieldPanel.add(changesTF);
		this.changesTF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.changesTF.setOpaque(true);
		this.changesTF.setBackground(Color.white);
		this.changesTF.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		this.add(labelPanel);
		this.add(fieldPanel);
		this.labelPanel.setBounds(855, 401, 80, 150);  //총합 받은돈 잔돈 라벨 묶음 
		this.fieldPanel.setBounds(940, 403, 250, 150);	 //총합 받은돈 잔돈 필드 묶음
		//------------완료 취소
		this.panel.add(complete);
		this.panel.add(cancel);
		this.add(panel);  
		this.panel.setBounds(853, 555, 337, 60);  //완료 취소
		//-----------정산 영수증 종료 할인
		this.btnPanel.add(calculate); //정산
		this.btnPanel.add(sales);     //매출현황
		this.btnPanel.add(discount);  //할인
		this.btnPanel.add(point);     //포인트
		this.btnPanel.add(receipt);   //영수증
		this.btnPanel.add(theEnd);    //종료
		this.add(btnPanel);
		this.btnPanel.setBounds(0, 616, 1191, 40); //정산 영수증 결산 할인 묶음
		//---로고
		this.add(banner);
		this.banner.setBounds(0, 0, 1190, 100);
		this.banner.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.banner.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다
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
			Socket sock = server.accept(); // 네트워크에서 20000포트로 들어오는 데이터 듣기
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