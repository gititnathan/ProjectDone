package ServerPos;
import java.awt.BorderLayout;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FoodList extends JDialog{

	private Server ser;
	private String num;

	private JLabel msg = new JLabel("������ �����ϴ�");

	private DefaultTableModel model;
	private JTable list;
	private JScrollPane js;

	private String[] menu = new String[] {"���ĸ�","����"};
	
	private void comInit(){
		model = new DefaultTableModel(menu, 0);
		for(Order tmp : ser.getMenulist(num)){
			model.addRow(new Object[] {tmp.getMenu(),tmp.getQuantity()});
		}
		list = new JTable(model);
		js = new JScrollPane(list);

		this.add(js);
	}


	public FoodList(Server self, String num){
		ser = self;
		this.num = num;
		this.setTitle("�޴�");
		this.setSize(400, 250);
		this.setLocationRelativeTo(self);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try{
			this.comInit();
		}catch (Exception e) {
			this.add(msg, BorderLayout.NORTH);
		}
		this.setVisible(true);

	}

}

