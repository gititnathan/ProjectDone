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
 
public class ChangeContact extends JDialog{
	PointDialog parent;
	public PointDBManager manager = new PointDBManager();
 
	private JLabel  contactLabel= new JLabel("연락처");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("조회된 이름");
	private JLabel nameSearch = new JLabel("");
	private JLabel changeNum = new JLabel("변경하실 연락처 : ");
//	private JTextField toWhatNum = new JTextField("");
	private JButton btnInquiry = new JButton("조회");
	private JButton btnChange = new JButton("변경");
	private JPanel panelUse = new JPanel(new GridLayout(4, 2));
	private int returnpoint;
	public int pointPrice;
	
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format);
	private  JFormattedTextField contactText;
	private JFormattedTextField toWhatNum;
	
	public void compInit(){
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    contactText = new JFormattedTextField(formatter);
	    toWhatNum = new JFormattedTextField(formatter);
		
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,0,0);
		
		c.gridy = 1;
		this.add(nameLabel, c);
		this.add(nameSearch, c);
		
		c.gridy = 2;
		this.add(contactLabel, c);
		this.add(contactText, c);
		this.contactText.setPreferredSize(new Dimension(100, 27));
		
		c.gridy = 3;
		this.add(changeNum, c);
		this.add(toWhatNum, c);
		this.toWhatNum.setPreferredSize(new Dimension(100, 27));
		
		
		c.gridy = 4;
		this.add(btnInquiry, c);
		this.add(btnChange, c);
	}
 
	public void eventInit(){
		this.btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 
 
				try {
					String contactGet = contactText.getText();
					if (contactGet.equals("")){
						JOptionPane.showMessageDialog(parent, "연락처를 입력하세요");
						return;
					}
					
					String contact = contactText.getText().replaceAll(",","");
					boolean result = manager.isContactExist(contact);
					if(result) {
						
						ArrayList<MembershipPoint> result2 = manager.selectPoint(contact); 
						// MembershipPoint형을 가진 Arraylist형 result는 dbmanager에게 contact를 넘겨주어서 
						for( MembershipPoint tmp : result2){
							nameSearch.setText(tmp.getName());
						}// for문
						
					}else {
						JOptionPane.showMessageDialog(parent,"등록되지 않은 번호입니다.");
					}// else문
				} catch (Exception e1) {
					e1.printStackTrace();
				}//try,catch 끝 
			}
 
		});
 
 
		this.btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					try {
						String contactGet = contactText.getText();
						if (contactGet.equals("")){
							JOptionPane.showMessageDialog(parent, "연락처를 입력하세요");
							return;
						}
						
						String num2Change = toWhatNum.getText();
						if (num2Change.equals("")){
							JOptionPane.showMessageDialog(parent, "변경하실 연락처를 입력하세요");
							return;
						}
						
						String contact = contactText.getText().replaceAll(",",""); // 텍스트필드에 적힌 포인트와 연락처를 가져온다.
						String newContact = toWhatNum.getText().replaceAll(",", "");
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							int insertResult = manager.updateContact(newContact,contact);
							if(insertResult > 0) {
								JOptionPane.showMessageDialog(parent,"번호가 변경되었습니다.");
								dispose();					//현재 프레임 종료 
							}else {
								JOptionPane.showMessageDialog(parent,"에러가 발생했습니다.");
							}
						} 
						// 밖에서 선언해준 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(parent,"연락처에 숫자만 입력해주세요.");
						return;
					} 
				}catch(Exception e2){
					JOptionPane.showMessageDialog(parent,"오류 발생! 관리자에게 연락하세요.");
					return;
				}
				
 
			}
		});
	}
	public ChangeContact(PointDialog parent) {
		this.parent = parent;
		this.setTitle("연락처 변경");
		this.setSize(361,200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}