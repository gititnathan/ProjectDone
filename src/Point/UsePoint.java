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
	
	
	private JLabel  contactLabel= new JLabel("����ó");
//	private JTextField contactText = new JTextField("");
	private JLabel  nameLabel= new JLabel("��ȸ�� �̸�");
	private JLabel  pointLabel= new JLabel("��ȸ�� ����Ʈ");
	private JLabel willSpend = new JLabel("����Ͻ� ����Ʈ : ");
//	private JTextField pointText = new JTextField();
	private JButton btnInquiry = new JButton("��ȸ");
	private JButton btnUse = new JButton("���");
	private JPanel panelUse = new JPanel(new GridLayout(4, 2));
	private int returnpoint;
	public int pointPrice;
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format); // textField�ȿ� ���°� �����̰Ը� �����
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
 
			}//actionperformed
 
		});// actionListener
		this.btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
					try {
						if (parent.parent.stopAdd == 0){
							parent.parent.stopAdd = 1;
						} else{
							JOptionPane.showMessageDialog(parent, "�̹� ����Ʈ�� ����ϼ̰ų� �����ϼ̽��ϴ�!");
							return;
						}
						
						
						String contactGet = contactText.getText();
						if (contactGet.equals("")){
							JOptionPane.showMessageDialog(parent, "����ó�� �Է��ϼ���");
							return;
						}
 
						int point = Integer.parseInt(pointText.getText().replaceAll(",", ""));
						String contact = contactText.getText().replaceAll(",",""); // �ؽ�Ʈ�ʵ忡 ���� ����Ʈ�� ����ó�� �����´�.
 
						ArrayList<MembershipPoint> result = manager.selectPoint(contact);
						for( MembershipPoint tmp : result){
							if( tmp.getPoint() >= Integer.parseInt(pointText.getText())) { // db���� ��ȸ�� ����Ʈ�� ����Ϸ��� ����Ʈ���� ũ�� ����� �Ѵ�!
								returnpoint = tmp.getPoint() - Integer.parseInt(pointText.getText()); // Integer.�κ��� ���߿� Server�� ���� �ݾ� �� �Ϻη� �޾ƿ��� ���� �����ߵ�.
								pointPrice = Integer.parseInt(parent.parent.self.totalTF.getText()) // dialog�� parent�� server�� �ִ� tf ���� ���� �ҷ����� ��
										- Integer.parseInt(pointText.getText());
								parent.parent.self.totalTF.setText(""+pointPrice); // ���� ����� ���� �ο��ϴ� ��.
 
								int insertResult = manager.updatePoint(returnpoint,contact);
								
								if(insertResult > 0) {
									JOptionPane.showMessageDialog(parent,"����Ʈ�� ���Ǿ����ϴ�.");
									dispose();					//���� ������ ���� 
								}else {
									JOptionPane.showMessageDialog(parent,"������ ����Ʈ�� �����մϴ�.");
								}
							} else {
								JOptionPane.showMessageDialog(parent, "������ ����Ʈ�� 1000����Ʈ�� �Ǿ�� �մϴ�.");
							}
						}// �ۿ��� �������� 
						return;
					} catch (Exception e1) {
						return;
					} 
			}
		});
	}
	public UsePoint(PointDialog parent) {
		this.parent = parent;
		this.setTitle("����Ʈ ���");
		this.setSize(337,203);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
 
}
