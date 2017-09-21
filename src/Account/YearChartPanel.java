package Account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class YearChartPanel extends JPanel{
	private Account.DBManager dbm = new DBManager();
	private DefaultCategoryDataset yeargetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "2015��";
		String type2 = "2016��";
		String type3 = "2017��"; 

		int datasize = 0; //totalprice���̺��� �� ���� ���� ����
		int price = 0; //�Ϸ� �Ѿ׵��� ���ؼ� �� �� �Ѿ��� ���� ����
		int yearPrice = 0;
		try {
			datasize = dbm.dataSize();

			//9�� �����͸� �ҷ��ͼ� ���ϱ� = 9�� ������
			for(int i = 1; i <= datasize; i++) {
				int data = dbm.monthTotalPrice(i); 
				price += data;
			}
			//2017�� 1������ 8�������� �����͸� ���ϱ� = 2017�� ������
			for(int i = 1; i < 9; i++) {
				int data = dbm.monthPrice(i);
				yearPrice += data;
			}
			yearPrice += price; //1~8�������� �Ѿװ� 9���� �Ѿ��� ���ϴ°�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int value = yearPrice;
		dataset.addValue(64000000, series, type1);
		dataset.addValue(78000000, series, type2);
		dataset.addValue(yearPrice, series, type3);

		return dataset;
	}
	private JFreeChart yearChart = ChartFactory.createLineChart(
			"",
			"YEAR",
			"Profit",
			yeargetDataset(),
			PlotOrientation.VERTICAL,
			false, //����ǥ�� ���� : ��Ʈ�� �̰� �������� �˷��ִ°� ���α׷����� �� ������ �ѱݾ��� ���Ѵ� �̷���
			true,
			false
			);
	public YearChartPanel() {
		yearChart.setBackgroundPaint(Color.WHITE);
		// ����
		yearChart.getTitle().setFont(new Font("���� ���", Font.BOLD, 15));

		CategoryPlot plot = yearChart.getCategoryPlot();

		Font font = plot.getDomainAxis().getLabelFont();
		// X�� ��
		plot.getDomainAxis().setLabelFont(new Font("���� ���", font.getStyle(), font.getSize()));
		// X�� ������
		plot.getDomainAxis().setTickLabelFont(new Font("���� ���", font.getStyle(), 10));

		font = plot.getRangeAxis().getLabelFont();
		// Y�� ��
		plot.getRangeAxis().setLabelFont(new Font("���� ���", font.getStyle(), font.getSize()));
		// Y�� ����
		plot.getRangeAxis().setTickLabelFont(new Font("���� ���", font.getStyle(), 10));
		// y�� ��������

		plot.getRangeAxis().setRange(0.0,100000000.0);
		ChartPanel yearCP = new ChartPanel(yearChart);
		yearCP.setPreferredSize(new Dimension(920,470));
		this.add(yearCP);
	}

}




