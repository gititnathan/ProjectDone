package ReceiptFile;

import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ReceiptDialog extends JDialog{

	private DBManager manager = new DBManager();
	private ReceiptDialog self = this;

	// ---------------------------------------------------------------------- compInit

	private DefaultTableModel defaultTableModel =new DefaultTableModel(new String[] {"Order_Num", "Table_Num", "Date", "Order","Total Price","Income","Change"},0)
	{
		@Override
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};

	private JTable jTable = new JTable(defaultTableModel);
	private JScrollPane jScrollPane = new JScrollPane(jTable);
	private JTextField jTextField = new JTextField(20);
	private JButton buttonSearch = new JButton("Search");
	private JComboBox<String> jComboBox = new JComboBox<String>(new String[] {"Order_Num", "Table_Num", "Order"}); 
	private JPanel southPanel = new JPanel(new FlowLayout());
	private JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

	// ---------------------------------------------------------------- printReceipt

	final PopupMenu pMenu = new PopupMenu(); 
	private MenuItem menu = new MenuItem("영수증 출력");

	public static String[] selectedRowValue = {"a","b","c","d","e","f","g"};

	// ------------------------------------------------------------------ searchEvent

	private ArrayList<Object[]> searchResult;


	// ------------------------------------------------------------------- datePicker

	private JDatePickerImpl datePicker1;
	private JDatePickerImpl datePicker2;

	private JLabel label = new JLabel("   ~   ");

	private JButton buttonOK = new JButton("OK");

	private String[] arrayDate1;
	private String[] arrayDate2;






	
	
	public void compInit() { // GUI 구성.
		
		this.jTable.setAutoCreateRowSorter(true);  // sorting
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 가운데정렬
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel tcm = jTable.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++){
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		this.jTable.getColumnModel().getColumn(0).setPreferredWidth(20);    // column 사이즈
		this.jTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		this.jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.jTable.getColumnModel().getColumn(3).setPreferredWidth(300);
		this.jTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		this.jTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		this.jTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		this.jTable.getTableHeader().setReorderingAllowed(false);

		this.southPanel.add(jComboBox);
		this.southPanel.add(jTextField);
		this.southPanel.add(buttonSearch);

		this.add(southPanel, "South");
		this.add(northPanel, "North");
		this.add(jScrollPane);

	}

	public void printReceipt() { // 영수증 출력.

		this.pMenu.add(menu);
		this.add(pMenu);

		this.jTable.addMouseListener(new MouseAdapter() {   // 마우스 우클릭 시 실행.
			public void mousePressed(MouseEvent me){
				if(me.getModifiers() == me.BUTTON3_MASK) {

					for(int i=0;i<7;i++) {
						selectedRowValue[i] = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
					}

					pMenu.show(jTable, me.getX(), me.getY());

				}
			}
		});

		this.menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReceiptPrint(self).setVisible(true);
			}
		});

	}

	public void searchByKeyword() { // 검색 버튼 실행.

		this.buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					if( jTextField.getText().trim().length() > 0 ) {

						if( jComboBox.getSelectedIndex() == 0 ) {
							
							searchResult = new ArrayList(manager.searchByKeyword(0, jTextField.getText().trim()));

						} else if( jComboBox.getSelectedIndex() == 1 ){

							searchResult = new ArrayList(manager.searchByKeyword(1, jTextField.getText().trim()));

						} else if( jComboBox.getSelectedIndex() == 2 ){

							searchResult = new ArrayList(manager.searchByKeyword(2, jTextField.getText().trim()));

						} 

						defaultTableModel.setRowCount(0); // 테이블 초기화.

						for(Object[] temp : searchResult) { // 테이블에 데이터 입력.

							defaultTableModel.addRow(temp);

						}

						if( defaultTableModel.getRowCount() > 0 )
						{
							jTable.setRowSelectionInterval(0, 0); 
						}

						self.jTextField.setText(null);
						self.jComboBox.setSelectedIndex(0);

					}

					else {
						JOptionPane.showMessageDialog(null, "검색어를 입력해주세요!");
					}

				}catch(Exception e1) {
					self.jTextField.setText(null);
					self.jComboBox.setSelectedIndex(0);
					defaultTableModel.setRowCount(0);
					JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
					e1.printStackTrace();
				}

			}
		});
	}

	public void searchByDate() { // 기간별 검색.
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH); // 실행시 현재 날짜로 세팅.
		int date = cal.get(cal.DATE);
		
		UtilDateModel model1 = new UtilDateModel();
		model1.setDate(year, month, date);
		model1.setSelected(true);

		UtilDateModel model2 = new UtilDateModel();
		model2.setDate(year, month, date);
		model2.setSelected(true);

		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");

		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");

		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);

		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);

		this.datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());

		this.datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());

		this.northPanel.add(datePicker1);

		this.northPanel.add(label);

		this.northPanel.add(datePicker2);

		this.northPanel.add(buttonOK);

		this.buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date selectedDate1 = (Date) datePicker1.getModel().getValue(); // 선택된 날짜 저장.
				Date selectedDate2 = (Date) datePicker2.getModel().getValue();

				String date1 = selectedDate1.toLocaleString();  // String형으로 변환.
				String date2 = selectedDate2.toLocaleString();

				String fDate1 = date1.substring(0, 11); // 년,월,일 부분까지 잘라내기.
				String fDate2 = date2.substring(0, 11);

				self.arrayDate1 = fDate1.split("\\."); // split.
				self.arrayDate2 = fDate2.split("\\.");

				try {
					
					searchResult = self.manager.searchByDate(arrayDate1, arrayDate2);
					
					defaultTableModel.setRowCount(0);

					for(Object[] temp : searchResult) {

						defaultTableModel.addRow(temp);

					}

					if( defaultTableModel.getRowCount() > 0 )
					{
						jTable.setRowSelectionInterval(0, 0); 
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	
	public ReceiptDialog(ServerPos.Server parent) throws Exception{
		
		this.setSize(900, 500);                          
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);; 
		this.compInit();   
		this.printReceipt();
		this.searchByKeyword();
		this.searchByDate();
		this.setModal(true);
		this.setResizable(false);

	}

}