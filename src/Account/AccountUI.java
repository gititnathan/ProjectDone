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
	//---입력 탭창---
	private JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelCenter = new JPanel(new GridLayout(1,2));
	private JPanel panelProfit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelAccount = new JPanel();
	
	private JButton accountOkButton = new JButton("정산완료");
	
	//----table에 값지정해줄 변수 선언--
	private int count1 = sum.sell.getQuantity1();
	private int count2 = sum.sell.getQuantity2();
	private int count3 = sum.sell.getQuantity3();
	private int count4 = sum.sell.getQuantity4();
	private int count5 = sum.sell.getQuantity5();
	private int count6 = sum.sell.getQuantity6();
	private int count7 = sum.sell.getQuantity7();
	private int count8 = sum.sell.getQuantity8();
	private int count9 = sum.sell.getQuantity9();
	//차트 데이터 값 넣어주기
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private DefaultCategoryDataset getDataset() {
		String series = "count";
		String type1 = "감베리";
		String type2 = "로제";
		String type3 = "칠리";
		String type4 = "갈비살";
		String type5 = "부채살";
		String type6 = "살치살";
		String type7 = "통마늘";
		String type8 = "치즈";
		String type9 = "치즈+";
		
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
	//테이블 값 설정해주기
	private String columnNames[] = {"메뉴","수량"};
	private Object rowData[][] = {	
			{"감베리크림파스타", count1},
			{"스파이시로제파스타", count2},
			{"칠리쉬림프토마토파스타", count3},
			{"블랙갈비살스테이크", count4},
			{"블랙부채살스테이크", count5},
			{"블랙살치살스테이크", count6},
			{"통마늘비프샐러드", count7},
			{"리코타치즈샐러드", count8},
			{"리코타치즈샐러드(with 치아바타)", count9},
	};
	private DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
	private void firstTap() {
		this.add(tPane);
		this.panelAccount.setLayout(new BorderLayout());
		//---첫번째 탭 창 구성---
		this.panelButton.add(accountOkButton);
		this.panelAccount.add(panelButton, BorderLayout.NORTH);
		
		//==테이블 추가 및 테이블 설정==
		JTable table = new JTable(dtm);
		//테이블의 각각의 컬럼들 사이즈 줌
		table.getColumn("메뉴").setPreferredWidth(170);
		table.getColumn("수량").setPreferredWidth(30);
		table.setRowHeight(20);
	
		JScrollPane scroll = new JScrollPane(table); 
		table.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		table.setPreferredScrollableViewportSize(new Dimension(200,400));
		
		//==차트 추가 및 차트 설정==
		JFreeChart barChart = ChartFactory.createBarChart(
				"",
				"현재판매메뉴",
				"수량",
				getDataset(),
				PlotOrientation.VERTICAL,
				false,
				true,
				false
				);
		 barChart.getTitle().setFont(new Font("맑은 고딕", Font.BOLD, 15));
		 
		 CategoryPlot plot = barChart.getCategoryPlot(); 
		 plot.getRangeAxis().setLabelAngle(90 * (Math.PI / 180.0));
		 plot.getRangeAxis().setRange(0.0,20.0);
		 
		 Font font = plot.getDomainAxis().getLabelFont();
		 plot.getDomainAxis().setLabelFont(new Font("맑은 고딕", font.getStyle(), font.getSize()));
		 plot.getDomainAxis().setTickLabelFont(new Font("맑은 고딕", font.getStyle(), 10));
		 
		 font = plot.getRangeAxis().getLabelFont();
		 plot.getRangeAxis().setLabelFont(new Font("맑은 고딕", font.getStyle(), font.getSize()));
		 plot.getRangeAxis().setTickLabelFont(new Font("맑은 고딕", font.getStyle(), 8));
		 
		 ChartPanel barCP = new ChartPanel(barChart);
		 barCP.setPreferredSize(new Dimension(200,400));
		 
		 //정산완료 버튼 이벤트
		 this.accountOkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//테이블의 각각의 수량을 0으로 바꿔줌
					dtm.setValueAt(0, 0, 1);
					dtm.setValueAt(0, 1, 1);
					dtm.setValueAt(0, 2, 1);
					dtm.setValueAt(0, 3, 1);
					dtm.setValueAt(0, 4, 1);
					dtm.setValueAt(0, 5, 1);
					dtm.setValueAt(0, 6, 1);
					dtm.setValueAt(0, 7, 1);
					dtm.setValueAt(0, 8, 1);
					//서버에서 받은 수량을 가지는 값을 0으로 바꿔줌 = 그래야 창을 닫고 다시 열어도 0으로 됨
					sum.sell.setQuantity1(0);
					sum.sell.setQuantity2(0);
					sum.sell.setQuantity3(0);
					sum.sell.setQuantity4(0);
					sum.sell.setQuantity5(0);
					sum.sell.setQuantity6(0);
					sum.sell.setQuantity7(0);
					sum.sell.setQuantity8(0);
					sum.sell.setQuantity9(0);
					//그래프의 모든 수량을 0으로 바꿔준다.
					dataset.removeRow(0);
				}
			});
		 
		this.panelCenter.add(barCP);
		this.panelCenter.add(scroll);
		this.panelAccount.add(panelCenter,BorderLayout.CENTER);
		tPane.addTab("매출 현황",panelAccount);
	}
	
	//---조회 탭창---
	private JPanel panelSelect = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelInquiry = new JPanel();
	private JPanel panelChart = new JPanel();
	private JRadioButton yearButton = new JRadioButton("연도별");
	private JRadioButton monthButton = new JRadioButton("월별");
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

		//연도별 버튼 클릭시 이벤트
		this.yearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelChart.add(ycp,"year");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"year");

			}
		});
		//월별 버튼 클릭시 이벤트
		this.monthButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelChart.add(mcp,"month");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"month");
			}

		});
		
		tPane.addTab("조회", panelInquiry);

	}
	public AccountUI(ServerPos.Server ser) {
		this.ser = ser;
		this.setTitle("매출현황");
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