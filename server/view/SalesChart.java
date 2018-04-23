package server.view;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import server.vo.SalesVO;

public class SalesChart extends JPanel {
	private static final long serialVersionUID = 1L;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@70.12.115.72:1521:orcl";
	String user = "pc";
	String passw = "pc";
	Connection con; // Connection은 멤버로 올림

	// 생성자는 전체적인 LayOut을 설정하는 역할
	public SalesChart(String applicationTitle, String chartTitle) throws ClassNotFoundException, SQLException {
		Class.forName(driver); // 1. 드라이버 로딩
		con = DriverManager.getConnection(url, user, passw);

		// based on the dataset we create the chart
		JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, "Date", "Sales", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		// Adding chart into a chart panel
		ChartPanel chartPanel = new ChartPanel(pieChart);
		// settind default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));

		add(chartPanel);
	}

	private CategoryDataset createDataset() throws SQLException { 
		ArrayList<SalesVO> list = selectSal();

		// row keys...
		final String Sales = "Sales";
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SalesVO vo : list) {
			dataset.addValue(vo.getSales_daily(), Sales, vo.getSales_date());
		}
		return dataset;

	}

	public ArrayList<SalesVO> selectSal() throws SQLException {
		// 3. SQL 문장
		String sql = "SELECT to_char(sales_date,'MM-DD'), sales_daily FROM sales";

		// 4. 전송 객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);

		// 5. 전송
		ResultSet rs = ps.executeQuery();
		ArrayList<SalesVO> list = new ArrayList<SalesVO>();
		while (rs.next()) {
			SalesVO vo = new SalesVO();
			vo.setSales_date((rs.getString(1)));
			vo.setSales_daily(rs.getInt(2));
			list.add(vo);
		}

		// 6. 닫기
		rs.close();
		ps.close();
		return list;

	}
}
