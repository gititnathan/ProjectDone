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
 
 
	private JLabel  contactLabel= new JLabel("����ó");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("��ȸ�� �̸�");
	private JLabel  pointLabel= new JLabel("��ȸ�� ����Ʈ");
	private JLabel  pointUse= new JLabel("����Ʈ");
	private JTextField pointText = new JTextField("");
	private JButton btnInquiry = new JButton("��ȸ");
	private JButton btnAdd = new JButton("����");
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
	    //textfield�� format�� �����Ű�� ���ؼ� �����ϴ� ����. 
	    
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
							JOptionPane.showMessageDialog(parent, "����ó�� �Է��ϼ���");
							return;
						}
 
						String contact = contactText.getText().replaceAll(",","");
						ArrayList<MembershipPoint> result = manager.selectPoint(contact); 
						// MembershipPoint���� ���� Arraylist�� result�� dbmanager���� contact�� �Ѱ��ְ� ������� �޾ƿ�.
						for( MembershipPoint tmp : result){
							nameLabel.setText(tmp.getName());
							pointLabel.setText(Integer.toString(tmp.getPoint()));
						}
					}catch(Exception e2){// Inner try
						JOptionPane.showMessageDialog(parent,"����ó�� ���ڸ� �Է����ֽñ� �ٶ��ϴ�.");
					}//inner catch
 
				} catch (Exception e1) { 
					JOptionPane.showMessageDialog(parent, "���� �߻�!");
					e1.printStackTrace();
				}// outer catch 
			}//actionperformed
 
		});// actionListener
		this.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					int point = Integer.parseInt(pointText.getText());
					String contactGet = contactText.getText();
					if (contactGet.equals("")){
						JOptionPane.showMessageDialog(parent, "����ó�� �Է��ϼ���");
						return;
					}
					
					try {
						if (parent.parent.stopAdd == 0){
							parent.parent.stopAdd = 1;
						} else{
							JOptionPane.showMessageDialog(parent, "�̹� ����Ʈ�� ����ϼ̰ų� �����ϼ̽��ϴ�!");
							return;
						}
						
						String contact = contactText.getText().replaceAll(",",""); // �ؽ�Ʈ�ʵ忡 ���� ����Ʈ�� ����ó�� �����´�.
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							int estimatedPoint = Integer.parseInt(parent.parent.self.totalTF.getText())/ 100 *2;
							returnpoint = tmp.getPoint() + estimatedPoint; // Integer.�κ��� ���߿� Server�� ���� �ݾ� �� �Ϻη� �޾ƿ��� ���� �����ߵ�. 
						}// �ۿ��� �������� 
						
						
						int insertResult = manager.updatePoint(returnpoint,contact);
							if(insertResult > 0) {
								JOptionPane.showMessageDialog(parent,"����Ʈ�� �����Ǿ����ϴ�.");
								dispose();					//���� ������ ���� 
							}else {
								JOptionPane.showMessageDialog(parent,"���� ��ȣ�Դϴ�.");
							}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(parent,"���� �߻�! �����ڿ��� �����ϼ���.");
				}
			}
		});
	}
	
	public AddPoint(PointDialog parent) {
		this.parent = parent;
		this.setSize(323,182);
		this.setTitle("����Ʈ ����");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}