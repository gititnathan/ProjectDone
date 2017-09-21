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
		String type1 = "2015년";
		String type2 = "2016년";
		String type3 = "2017년"; 

		int datasize = 0; //totalprice테이블의 행 수를 가질 변수
		int price = 0; //하루 총액들을 더해서 그 달 총액을 가질 변수
		int yearPrice = 0;
		try {
			datasize = dbm.dataSize();

			//9월 데이터를 불러와서 더하기 = 9월 데이터
			for(int i = 1; i <= datasize; i++) {
				int data = dbm.monthTotalPrice(i); 
				price += data;
			}
			//2017년 1월부터 8월까지의 데이터를 더하기 = 2017년 데이터
			for(int i = 1; i < 9; i++) {
				int data = dbm.monthPrice(i);
				yearPrice += data;
			}
			yearPrice += price; //1~8월까지의 총액과 9월의 총액을 더하는것
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
			false, //법례표시 법례 : 차트에 이게 무엇인지 알려주는거 라인그래프는 이 라인은 총금액을 뜻한다 이런거
			true,
			false
			);
	public YearChartPanel() {
		yearChart.setBackgroundPaint(Color.WHITE);
		// 제목
		yearChart.getTitle().setFont(new Font("맑은 고딕", Font.BOLD, 15));

		CategoryPlot plot = yearChart.getCategoryPlot();

		Font font = plot.getDomainAxis().getLabelFont();
		// X축 라벨
		plot.getDomainAxis().setLabelFont(new Font("맑은 고딕", font.getStyle(), font.getSize()));
		// X축 도메인
		plot.getDomainAxis().setTickLabelFont(new Font("맑은 고딕", font.getStyle(), 10));

		font = plot.getRangeAxis().getLabelFont();
		// Y축 라벨
		plot.getRangeAxis().setLabelFont(new Font("맑은 고딕", font.getStyle(), font.getSize()));
		// Y축 범위
		plot.getRangeAxis().setTickLabelFont(new Font("맑은 고딕", font.getStyle(), 10));
		// y축 범위지정

		plot.getRangeAxis().setRange(0.0,100000000.0);
		ChartPanel yearCP = new ChartPanel(yearChart);
		yearCP.setPreferredSize(new Dimension(920,470));
		this.add(yearCP);
	}

}




