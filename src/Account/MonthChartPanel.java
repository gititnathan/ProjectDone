package Account;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MonthChartPanel extends JPanel{
	private Account.DBManager dbm = new DBManager();

	private DefaultCategoryDataset monthgetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "Jan";
		String type2 = "Feb";
		String type3 = "Mar";
		String type4 = "Apr";
		String type5 = "May";
		String type6 = "June";
		String type7 = "Jul";
		String type8 = "Aug";
		String type9 = "Sept";

		int jmp = 0;
		int fmp = 0;
		int mmp = 0;
		int amp = 0;
		int maymp = 0;
		int junemp = 0;
		int julmp = 0;
		int augmp = 0;

		int datasize = 0; //totalprice테이블의 행 수를 가질 변수
		int price = 0; //하루 총액들을 더해서 그 달 총액을 가질 변수
		try {
			jmp = dbm.monthPrice(1);
			fmp = dbm.monthPrice(2);
			mmp = dbm.monthPrice(3);
			amp = dbm.monthPrice(4);
			maymp = dbm.monthPrice(5);
			junemp = dbm.monthPrice(6);
			julmp = dbm.monthPrice(7);
			augmp = dbm.monthPrice(8);

			datasize = dbm.dataSize();

			//9월 데이터를 불러와서 더하기 = 9월 데이터
			for(int i = 1; i <= datasize; i++) {
				int data = dbm.monthTotalPrice(i); 
				price += data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		dataset.addValue(jmp, series, type1);
		dataset.addValue(fmp, series, type2);
		dataset.addValue(mmp, series, type3);
		dataset.addValue(amp, series, type4);
		dataset.addValue(maymp, series, type5);
		dataset.addValue(junemp, series, type6);
		dataset.addValue(julmp, series, type7);
		dataset.addValue(augmp, series, type8);
		dataset.addValue(price, series, type9);

		return dataset;
	}
	private JFreeChart monthChart = ChartFactory.createLineChart(
			"",
			"Month",
			"Profit",
			monthgetDataset(),
			PlotOrientation.VERTICAL,
			false,
			true,
			false
			);
	public MonthChartPanel() {
		CategoryPlot plot = monthChart.getCategoryPlot();
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		plot.getRangeAxis().setRange(0.0,500000.0);
		//plot.getRangeAxis().setRange(0.0,100000000.0);
		ChartPanel monthCP = new ChartPanel(monthChart);		
		monthCP.setPreferredSize(new Dimension(920,470));
		this.add(monthCP);
	}

}
