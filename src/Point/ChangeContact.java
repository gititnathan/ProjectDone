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
 
	private JLabel  contactLabel= new JLabel("����ó");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("��ȸ�� �̸�");
	private JLabel nameSearch = new JLabel("");
	private JLabel changeNum = new JLabel("�����Ͻ� ����ó : ");
//	private JTextField toWhatNum = new JTextField("");
	private JButton btnInquiry = new JButton("��ȸ");
	private JButton btnChange = new JButton("����");
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
						JOptionPane.showMessageDialog(parent, "����ó�� �Է��ϼ���");
						return;
					}
					
					String contact = contactText.getText().replaceAll(",","");
					boolean result = manager.isContactExist(contact);
					if(result) {
						
						ArrayList<MembershipPoint> result2 = manager.selectPoint(contact); 
						// MembershipPoint���� ���� Arraylist�� result�� dbmanager���� contact�� �Ѱ��־ 
						for( MembershipPoint tmp : result2){
							nameSearch.setText(tmp.getName());
						}// for��
						
					}else {
						JOptionPane.showMessageDialog(parent,"��ϵ��� ���� ��ȣ�Դϴ�.");
					}// else��
				} catch (Exception e1) {
					e1.printStackTrace();
				}//try,catch �� 
			}
 
		});
 
 
		this.btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					try {
						String contactGet = contactText.getText();
						if (contactGet.equals("")){
							JOptionPane.showMessageDialog(parent, "����ó�� �Է��ϼ���");
							return;
						}
						
						String num2Change = toWhatNum.getText();
						if (num2Change.equals("")){
							JOptionPane.showMessageDialog(parent, "�����Ͻ� ����ó�� �Է��ϼ���");
							return;
						}
						
						String contact = contactText.getText().replaceAll(",",""); // �ؽ�Ʈ�ʵ忡 ���� ����Ʈ�� ����ó�� �����´�.
						String newContact = toWhatNum.getText().replaceAll(",", "");
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							int insertResult = manager.updateContact(newContact,contact);
							if(insertResult > 0) {
								JOptionPane.showMessageDialog(parent,"��ȣ�� ����Ǿ����ϴ�.");
								dispose();					//���� ������ ���� 
							}else {
								JOptionPane.showMessageDialog(parent,"������ �߻��߽��ϴ�.");
							}
						} 
						// �ۿ��� �������� 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(parent,"����ó�� ���ڸ� �Է����ּ���.");
						return;
					} 
				}catch(Exception e2){
					JOptionPane.showMessageDialog(parent,"���� �߻�! �����ڿ��� �����ϼ���.");
					return;
				}
				
 
			}
		});
	}
	public ChangeContact(PointDialog parent) {
		this.parent = parent;
		this.setTitle("����ó ����");
		this.setSize(361,200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}