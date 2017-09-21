package Point;
 
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
 
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
 
public class AddPoint extends JDialog{
	PointDialog parent;
	ServerPos.Server server;
	private PointDBManager manager = new PointDBManager();
 
 
	private JLabel  contactLabel= new JLabel("연락처");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("조회된 이름");
	private JLabel  pointLabel= new JLabel("조회된 포인트");
	private JLabel  pointUse= new JLabel("포인트");
	private JTextField pointText = new JTextField("");
	private JButton btnInquiry = new JButton("조회");
	private JButton btnAdd = new JButton("적립");
	private JPanel panelAdd = new JPanel(new GridLayout(4, 2));
	private int returnpoint;
	
	
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format);
	private  JFormattedTextField contactText;
	// numberformatter
	
	public void compInit(){
		
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    contactText = new JFormattedTextField(formatter);
	    //textfield에 format을 적용시키기 위해서 선언하는 문장. 
	    
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
		this.add(pointUse, c);
		this.add(pointText, c);
		this.pointText.setPreferredSize(new Dimension(100, 27));
		
		c.gridy = 4;
		this.add(btnInquiry, c);
		this.add(btnAdd, c);
		this.pointText.setText("" +Integer.parseInt(parent.parent.self.totalTF.getText())/ 100 *2);
		
		
	}
 
	public void eventInit(){
		this.btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
 
				} catch (Exception e1) { 
					JOptionPane.showMessageDialog(parent, "오류 발생!");
					e1.printStackTrace();
				}// outer catch 
			}//actionperformed
 
		});// actionListener
		this.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					int point = Integer.parseInt(pointText.getText());
					String contactGet = contactText.getText();
					if (contactGet.equals("")){
						JOptionPane.showMessageDialog(parent, "연락처를 입력하세요");
						return;
					}
					
					try {
						if (parent.parent.stopAdd == 0){
							parent.parent.stopAdd = 1;
						} else{
							JOptionPane.showMessageDialog(parent, "이미 포인트를 사용하셨거나 적립하셨습니다!");
							return;
						}
						
						String contact = contactText.getText().replaceAll(",",""); // 텍스트필드에 적힌 포인트와 연락처를 가져온다.
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							int estimatedPoint = Integer.parseInt(parent.parent.self.totalTF.getText())/ 100 *2;
							returnpoint = tmp.getPoint() + estimatedPoint; // Integer.부분을 나중에 Server의 결제 금액 중 일부로 받아오는 식을 만들어야됨. 
						}// 밖에서 선언해준 
						
						
						int insertResult = manager.updatePoint(returnpoint,contact);
							if(insertResult > 0) {
								JOptionPane.showMessageDialog(parent,"포인트가 적립되었습니다.");
								dispose();					//현재 프레임 종료 
							}else {
								JOptionPane.showMessageDialog(parent,"없는 번호입니다.");
							}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(parent,"오류 발생! 관리자에게 연락하세요.");
				}
			}
		});
	}
	
	public AddPoint(PointDialog parent) {
		this.parent = parent;
		this.setSize(323,182);
		this.setTitle("포인트 적립");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}