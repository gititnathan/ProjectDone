package Point;
 
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.text.NumberFormatter;
import javax.swing.SwingConstants;
 
public class UsePoint extends JDialog{
	PointDialog parent;
	ServerPos.Server server;
	public PointDBManager manager = new PointDBManager();
	
	
	private JLabel  contactLabel= new JLabel("연락처");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("조회된 이름");
	private JLabel  pointLabel= new JLabel("조회된 포인트");
	private JLabel willSpend = new JLabel("사용하실 포인트 : ");
//	private JTextField pointText = new JTextField();
	private JButton btnInquiry = new JButton("조회");
	private JButton btnUse = new JButton("사용");
	private JPanel panelUse = new JPanel(new GridLayout(4, 2));
	private int returnpoint;
	public int pointPrice;
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format); // textField안에 들어가는게 숫자이게만 만드는
	private  JFormattedTextField contactText;
	private JFormattedTextField pointText; 
	
	public void compInit(){
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    contactText = new JFormattedTextField(formatter);
	    pointText = new JFormattedTextField(formatter);
		
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,0,0);
 
		c.gridy = 1;
		this.add(nameLabel, c);
		this.add(pointLabel, c);
		c.gridy = 2;
 
		this.add(contactLabel, c);
		this.add(contactText, c);
		this.contactText.setPreferredSize(new Dimension(100, 27));
 
 
 
		c.gridy = 3;
		this.add(willSpend, c);
		this.add(pointText, c);
		this.pointText.setPreferredSize(new Dimension(100, 27));
		c.gridy = 4;
		this.add(btnInquiry, c);
		this.add(btnUse, c);
 
	}
 
	public void eventInit(){
		this.btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try{
						String contactGet = contactText.getText();
						if (contactGet.equals("")){
							JOptionPane.showMessageDialog(parent, "연락처를 입력하세요");
							return;
						}
 
						String contact = contactText.getText().replaceAll(",","");
						ArrayList<MembershipPoint> result = manager.selectPoint(contact); 
						// MembershipPoint형을 가진 Arraylist형 result는 dbmanager에게 contact를 넘겨주고 결과물을 받아옴.
						for( MembershipPoint tmp : result){
							nameLabel.setText(tmp.getName());
							pointLabel.setText(Integer.toString(tmp.getPoint()));
						}
					}catch(Exception e2){// Inner try
						JOptionPane.showMessageDialog(parent,"연락처에 숫자만 입력해주시기 바랍니다.");
					}//inner catch
 
			}//actionperformed
 
		});// actionListener
		this.btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
					try {
						if (parent.parent.stopAdd == 0){
							parent.parent.stopAdd = 1;
						} else{
							JOptionPane.showMessageDialog(parent, "이미 포인트를 사용하셨거나 적립하셨습니다!");
							return;
						}
						
						
						String contactGet = contactText.getText();
						if (contactGet.equals("")){
							JOptionPane.showMessageDialog(parent, "연락처를 입력하세요");
							return;
						}
 
						int point = Integer.parseInt(pointText.getText().replaceAll(",", ""));
						String contact = contactText.getText().replaceAll(",",""); // 텍스트필드에 적힌 포인트와 연락처를 가져온다.
 
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							if( tmp.getPoint() >= Integer.parseInt(pointText.getText())) { // db에서 조회한 포인트가 사용하려는 포인트보다 크면 계산을 한다!
								returnpoint = tmp.getPoint() - Integer.parseInt(pointText.getText()); // Integer.부분을 나중에 Server의 결제 금액 중 일부로 받아오는 식을 만들어야됨.
								pointPrice = Integer.parseInt(parent.parent.self.totalTF.getText()) // dialog의 parent인 server에 있는 tf 내의 값을 불러오는 것
										- Integer.parseInt(pointText.getText());
								parent.parent.self.totalTF.setText(""+pointPrice); // 새로 계산한 값을 부여하는 것.
 
								int insertResult = manager.updatePoint(returnpoint,contact);
								
								if(insertResult > 0) {
									JOptionPane.showMessageDialog(parent,"포인트가 사용되었습니다.");
									dispose();					//현재 프레임 종료 
								}else {
									JOptionPane.showMessageDialog(parent,"보유한 포인트가 부족합니다.");
								}
							} else {
								JOptionPane.showMessageDialog(parent, "보유한 포인트가 1000포인트가 되어야 합니다.");
							}
						}// 밖에서 선언해준 
						return;
					} catch (Exception e1) {
						return;
					} 
			}
		});
	}
	public UsePoint(PointDialog parent) {
		this.parent = parent;
		this.setTitle("포인트 사용");
		this.setSize(337,203);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}
