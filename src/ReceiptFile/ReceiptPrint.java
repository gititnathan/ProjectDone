package ReceiptFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class ReceiptPrint extends JDialog implements Printable, ActionListener{
	
	private JDialog frameToPrint;

	private ReceiptDialog receiptDialog;
	
	private ReceiptPrint self = this;

	private String[] arrayOrder;

	
	// --------------------------------------------- North Component.

	private JPanel northPanel = new JPanel(new GridLayout(4,1));

	private JPanel nPanel1 = new JPanel(new GridLayout(1,2));
	private JLabel timeLabel = new JLabel();
	private JLabel orderNumLabel = new JLabel();

	private JPanel nPanel2 = new JPanel();
	private JLabel line1 = new JLabel("===================================================");

	private JPanel nPanel3 = new JPanel(new GridLayout(1,3));
	private JLabel orderLabel = new JLabel("      제 품 명");
	private JLabel qttLabel = new JLabel("      수 량");
	private JLabel priceLabel = new JLabel("      금 액");

	private JPanel nPanel4 = new JPanel();
	private JLabel line2 = new JLabel("===================================================");
	
	// --------------------------------------------- Center Component.
	
	private JPanel centerPanel = new JPanel(new GridLayout(0,3));
	private JLabel[] centerLabel = new JLabel[24];

	// --------------------------------------------- South Component.
	
	private JPanel southPanel = new JPanel(new GridLayout(10,1));

	private JPanel sPanel1 = new JPanel();
	private JLabel line3 = new JLabel("===================================================");

	private JPanel sPanel2 = new JPanel(new GridLayout(1,4));
	private JLabel subTotal = new JLabel();
	private JLabel blankLabel1 = new JLabel();
	private JLabel blankLabel2 = new JLabel();
	private JLabel subTotalNum = new JLabel();

	private JPanel sPanel3 = new JPanel(new GridLayout(1,4));
	private JLabel tax = new JLabel();
	private JLabel blankLabel3 = new JLabel();
	private JLabel blankLabel4 = new JLabel();
	private JLabel taxNum = new JLabel();

	private JPanel sPanel4 = new JPanel();
	private JLabel line4 = new JLabel("------------------------------------------------------------------------------------------");

	private JPanel sPanel5 = new JPanel(new GridLayout(1,4));
	private JLabel total = new JLabel();
	private JLabel blankLabel5 = new JLabel();
	private JLabel blankLabel6 = new JLabel();
	private JLabel totalNum = new JLabel();

	private JPanel sPanel6 = new JPanel();
	private JLabel line5 = new JLabel("------------------------------------------------------------------------------------------");

	private JPanel sPanel7 = new JPanel(new GridLayout(1,4));
	private JLabel income = new JLabel();
	private JLabel blankLabel7 = new JLabel();
	private JLabel blankLabel8 = new JLabel();
	private JLabel incomeNum = new JLabel();

	private JPanel sPanel8 = new JPanel(new GridLayout(1,4));
	private JLabel change = new JLabel();
	private JLabel blankLabel9 = new JLabel();
	private JLabel blankLabel10 = new JLabel();
	private JLabel changeNum = new JLabel();

	private JPanel sPanel9 = new JPanel();
	private JLabel line6 = new JLabel("===================================================");

	private JPanel sPanel10 = new JPanel();
	private JButton buttonPrint = new JButton("Print");
	
	
	
	
	
	
	
	public int print(Graphics g, PageFormat pf, int page) throws // print 기능.
	PrinterException {

		if (page > 0) { 
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		frameToPrint.getContentPane().printAll(g);

		return PAGE_EXISTS;
	}
	
	public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
            	ex.printStackTrace();
            }
        }
   }

	
	public void compInit() { // UI 구성.

		// ------------------------------------- North Component.
		
		this.nPanel1.add(timeLabel);
		this.timeLabel.setText("      "+receiptDialog.selectedRowValue[2]);
		this.nPanel1.add(orderNumLabel);
		this.orderNumLabel.setText("                   "+"Order Num : "+receiptDialog.selectedRowValue[0]);

		this.nPanel2.add(line1);

		this.nPanel3.add(orderLabel);
		this.nPanel3.add(qttLabel);
		this.nPanel3.add(priceLabel);

		this.nPanel4.add(line2);

		this.northPanel.add(nPanel1);
		this.northPanel.add(nPanel2);
		this.northPanel.add(nPanel3);
		this.northPanel.add(nPanel4);
		
		this.add(northPanel, BorderLayout.NORTH);
		
		// ----------------------------------------- Center Component.
		
		this.arrayOrder = receiptDialog.selectedRowValue[3].split(":"); // 주문내용 split.

		for(int i = 0; i<arrayOrder.length;i++) {

			this.centerLabel[i] = new JLabel();
			this.centerLabel[i].setText("      "+arrayOrder[i]);
			this.centerPanel.add(centerLabel[i]);
			
		}
		
		this.add(centerPanel, BorderLayout.CENTER);
		
		// ------------------------------------------ South Component.

		this.sPanel1.add(line3);
		
		this.sPanel2.add(subTotal);
		this.subTotal.setText("      Subtotal");
		this.sPanel2.add(blankLabel1);
		this.sPanel2.add(blankLabel2);
		this.sPanel2.add(subTotalNum);
		this.subTotalNum.setText(""+((Integer.parseInt(receiptDialog.selectedRowValue[4]))*100/110));

		this.sPanel3.add(tax);
		this.tax.setText("      Tax");
		this.sPanel3.add(blankLabel3);
		this.sPanel3.add(blankLabel4);
		this.sPanel3.add(taxNum);
		this.taxNum.setText(""+((Integer.parseInt(receiptDialog.selectedRowValue[4]))*10/110));
		
		this.sPanel4.add(line4);

		this.sPanel5.add(total);
		this.total.setText("   Total");
		this.total.setFont(new Font("돋움",Font.BOLD,15));
		this.sPanel5.add(blankLabel5);
		this.sPanel5.add(blankLabel6);
		this.sPanel5.add(totalNum);
		this.totalNum.setText(receiptDialog.selectedRowValue[4]);
		this.totalNum.setFont(new Font("돋움",Font.BOLD,15));
		
		this.sPanel6.add(line5);

		this.sPanel7.add(income);
		this.income.setText("      Income");
		this.sPanel7.add(blankLabel7);
		this.sPanel7.add(blankLabel8);
		this.sPanel7.add(incomeNum);
		this.incomeNum.setText(receiptDialog.selectedRowValue[5]);

		this.sPanel8.add(change);
		this.change.setText("      Change");
		this.sPanel8.add(blankLabel9);
		this.sPanel8.add(blankLabel10);
		this.sPanel8.add(changeNum);
		this.changeNum.setText(receiptDialog.selectedRowValue[6]);
		
		this.sPanel9.add(line6);
		
		this.sPanel10.add(buttonPrint);
		this.buttonPrint.addActionListener(new ReceiptPrint(self));
		
		this.southPanel.add(sPanel1);
		this.southPanel.add(sPanel2);
		this.southPanel.add(sPanel3);
		this.southPanel.add(sPanel4);
		this.southPanel.add(sPanel5);
		this.southPanel.add(sPanel6);
		this.southPanel.add(sPanel7);
		this.southPanel.add(sPanel8);
		this.southPanel.add(sPanel9);
		this.southPanel.add(sPanel10);

		this.add(southPanel, BorderLayout.SOUTH);

	}

	public ReceiptPrint(ReceiptDialog parent) {

		this.receiptDialog = parent;
		this.setSize(400, 700);                          
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);; 
		this.compInit();      
		this.setModal(true);
		this.setResizable(false);

	}

	public ReceiptPrint(ReceiptPrint self2) {
		this.frameToPrint = self2;
	}
	
}
