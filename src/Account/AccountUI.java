package Account;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class AccountUI extends JDialog{
	private JTabbedPane tPane = new JTabbedPane();
	
	private ServerPos.Server ser;
	private Sum sum = new Sum();
	//---�Է� ��â---
	private JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelCenter = new JPanel(new GridLayout(1,2));
	private JPanel panelProfit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelAccount = new JPanel();
	
	private JButton accountOkButton = new JButton("����Ϸ�");
	
	//----table�� ���������� ���� ����--
	private int count1 = sum.sell.getQuantity1();
	private int count2 = sum.sell.getQuantity2();
	private int count3 = sum.sell.getQuantity3();
	private int count4 = sum.sell.getQuantity4();
	private int count5 = sum.sell.getQuantity5();
	private int count6 = sum.sell.getQuantity6();
	private int count7 = sum.sell.getQuantity7();
	private int count8 = sum.sell.getQuantity8();
	private int count9 = sum.sell.getQuantity9();
	//��Ʈ ������ �� �־��ֱ�
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private DefaultCategoryDataset getDataset() {
		String series = "count";
		String type1 = "������";
		String type2 = "����";
		String type3 = "ĥ��";
		String type4 = "�����";
		String type5 = "��ä��";
		String type6 = "��ġ��";
		String type7 = "�븶��";
		String type8 = "ġ��";
		String type9 = "ġ��+";
		
		dataset.addValue(count1, series, type1);
		dataset.addValue(count2, series, type2);
		dataset.addValue(count3, series, type3);
		dataset.addValue(count4, series, type4);
		dataset.addValue(count5, series, type5);
		dataset.addValue(count6, series, type6);
		dataset.addValue(count7, series, type7);
		dataset.addValue(count8, series, type8);
		dataset.addValue(count9, series, type9);
		return dataset;
	}
	//���̺� �� �������ֱ�
	private String columnNames[] = {"�޴�","����"};
	private Object rowData[][] = {	
			{"������ũ���Ľ�Ÿ", count1},
			{"�����̽÷����Ľ�Ÿ", count2},
			{"ĥ���������丶���Ľ�Ÿ", count3},
			{"������콺����ũ", count4},
			{"����ä�콺����ũ", count5},
			{"����ġ�콺����ũ", count6},
			{"�븶�ú���������", count7},
			{"����Ÿġ�������", count8},
			{"����Ÿġ�������(with ġ�ƹ�Ÿ)", count9},
	};
	private DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
	private void firstTap() {
		this.add(tPane);
		this.panelAccount.setLayout(new BorderLayout());
		//---ù��° �� â ����---
		this.panelButton.add(accountOkButton);
		this.panelAccount.add(panelButton, BorderLayout.NORTH);
		
		//==���̺� �߰� �� ���̺� ����==
		JTable table = new JTable(dtm);
		//���̺��� ������ �÷��� ������ ��
		table.getColumn("�޴�").setPreferredWidth(170);
		table.getColumn("����").setPreferredWidth(30);
		table.setRowHeight(20);
	
		JScrollPane scroll = new JScrollPane(table); 
		table.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		table.setPreferredScrollableViewportSize(new Dimension(200,400));
		
		//==��Ʈ �߰� �� ��Ʈ ����==
		JFreeChart barChart = ChartFactory.createBarChart(
				"",
				"�����ǸŸ޴�",
				"����",
				getDataset(),
				PlotOrientation.VERTICAL,
				false,
				true,
				false
				);
		 barChart.getTitle().setFont(new Font("���� ���", Font.BOLD, 15));
		 
		 CategoryPlot plot = barChart.getCategoryPlot(); 
		 plot.getRangeAxis().setLabelAngle(90 * (Math.PI / 180.0));
		 plot.getRangeAxis().setRange(0.0,20.0);
		 
		 Font font = plot.getDomainAxis().getLabelFont();
		 plot.getDomainAxis().setLabelFont(new Font("���� ���", font.getStyle(), font.getSize()));
		 plot.getDomainAxis().setTickLabelFont(new Font("���� ���", font.getStyle(), 10));
		 
		 font = plot.getRangeAxis().getLabelFont();
		 plot.getRangeAxis().setLabelFont(new Font("���� ���", font.getStyle(), font.getSize()));
		 plot.getRangeAxis().setTickLabelFont(new Font("���� ���", font.getStyle(), 8));
		 
		 ChartPanel barCP = new ChartPanel(barChart);
		 barCP.setPreferredSize(new Dimension(200,400));
		 
		 //����Ϸ� ��ư �̺�Ʈ
		 this.accountOkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//���̺��� ������ ������ 0���� �ٲ���
					dtm.setValueAt(0, 0, 1);
					dtm.setValueAt(0, 1, 1);
					dtm.setValueAt(0, 2, 1);
					dtm.setValueAt(0, 3, 1);
					dtm.setValueAt(0, 4, 1);
					dtm.setValueAt(0, 5, 1);
					dtm.setValueAt(0, 6, 1);
					dtm.setValueAt(0, 7, 1);
					dtm.setValueAt(0, 8, 1);
					//�������� ���� ������ ������ ���� 0���� �ٲ��� = �׷��� â�� �ݰ� �ٽ� ��� 0���� ��
					sum.sell.setQuantity1(0);
					sum.sell.setQuantity2(0);
					sum.sell.setQuantity3(0);
					sum.sell.setQuantity4(0);
					sum.sell.setQuantity5(0);
					sum.sell.setQuantity6(0);
					sum.sell.setQuantity7(0);
					sum.sell.setQuantity8(0);
					sum.sell.setQuantity9(0);
					//�׷����� ��� ������ 0���� �ٲ��ش�.
					dataset.removeRow(0);
				}
			});
		 
		this.panelCenter.add(barCP);
		this.panelCenter.add(scroll);
		this.panelAccount.add(panelCenter,BorderLayout.CENTER);
		tPane.addTab("���� ��Ȳ",panelAccount);
	}
	
	//---��ȸ ��â---
	private JPanel panelSelect = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelInquiry = new JPanel();
	private JPanel panelChart = new JPanel();
	private JRadioButton yearButton = new JRadioButton("������");
	private JRadioButton monthButton = new JRadioButton("����");
	private ButtonGroup bg = new ButtonGroup();
	private YearChartPanel ycp = new YearChartPanel();
	private MonthChartPanel mcp = new MonthChartPanel();
	private void secondTap() {
		bg.add(yearButton);
		bg.add(monthButton);
		this.panelSelect.add(yearButton);
		this.panelSelect.add(monthButton);
		
		this.panelChart.setLayout(new CardLayout());
		this.panelChart.add(ycp,"year");
		
		this.panelInquiry.setLayout(new BorderLayout());
		this.panelInquiry.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));		
		this.panelInquiry.add(panelSelect,BorderLayout.NORTH);
		this.panelInquiry.add(panelChart,BorderLayout.CENTER);

		//������ ��ư Ŭ���� �̺�Ʈ
		this.yearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelChart.add(ycp,"year");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"year");

			}
		});
		//���� ��ư Ŭ���� �̺�Ʈ
		this.monthButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelChart.add(mcp,"month");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"month");
			}

		});
		
		tPane.addTab("��ȸ", panelInquiry);

	}
	public AccountUI(ServerPos.Server ser) {
		this.ser = ser;
		this.setTitle("������Ȳ");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(ser);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getDataset();
		this.firstTap();
		this.secondTap();
		this.setResizable(false);
		this.setVisible(true);
	}
}