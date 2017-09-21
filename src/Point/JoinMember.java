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
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
 
public class JoinMember extends JDialog{
	PointDialog parent;
	private PointDBManager manager = new PointDBManager();
	private JLabel nameLabel = new JLabel("�̸�");
	private JTextField nameText = new JTextField("");
	private JLabel  contactLabel= new JLabel("����ó");
	private JButton btnJoin = new JButton("����");
	private JButton cancelBtn = new JButton("���");
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format);
	private  JFormattedTextField contactText;
 
	public void compInit(){
 
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    contactText = new JFormattedTextField(formatter);
		
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,0,0);
		
		c.gridy = 1;
		this.add(nameLabel, c);
		this.add(nameText, c);
		this.nameText.setPreferredSize(new Dimension(100, 27));
		
		c.gridy = 2;
		this.add(contactLabel, c);
		this.add(contactText, c);
		this.contactText.setPreferredSize(new Dimension(100, 27));
		
		c.gridy = 3;
		this.add(btnJoin, c);
		this.add(cancelBtn, c);
	}
 
	public void eventInit(){
		this.cancelBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
						
						String name = nameText.getText();
						
						if(name.equals("")) {
							JOptionPane.showMessageDialog(parent,"�̸��� �Է����ּ���.");
							return;						//�޼��� ����
						}
						
						
						String contact = contactText.getText().replaceAll(",","");
						boolean result;
						try {
							result = manager.isContactExist(contact);
							if(result) {
 
								JOptionPane.showMessageDialog(parent,"�̹� ���Ե� ��ȣ�Դϴ�.");
								return;
							}else {
 
								int zero = 0;
 
								int insertResult = manager.insertMember(name, contact, zero); // DBmanager���� ���� result�� �������� ��. 
 
								if(insertResult > 0) {
									JOptionPane.showMessageDialog(parent,"ȸ�����Կ� �����߽��ϴ�.");
									dispose();
								} else{
									JOptionPane.showMessageDialog(parent, "ȸ�����Կ� ������ �߻��Ͽ����ϴ�. ");// else��
								}
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});
	}
	
	public JoinMember(PointDialog parent) {
		this.parent = parent;
		this.setTitle("����Ʈ ȸ�� ����");
		this.setSize(337,186);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}