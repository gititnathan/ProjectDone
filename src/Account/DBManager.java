package Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//해야할 일
//매일 정산된 값을 받아요 -> 정산된 값을 받아서 디비에 저장해야합니다
//디비에서 년도로 내용을 꺼내오는거 하나
//월별 꺼내오는거 하나
//일별 꺼내오는거 하나
//디비에 들어갈 정보들 : 날짜, 총액

public class DBManager {
	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","java7","java7");
		
		return con;
	}
	public int dataSize() throws Exception { //이게 행 수 세는 거
		Connection con = this.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select count(*) from month";
		int rowCount = 0;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			rowCount = rs.getInt(1);
		}
		int result = rowCount;
		rs.close();
		return result;
	} 
	public void insertAllPrice(int i) throws Exception { //오늘 총매출 저장
		Connection con = this.getConnection();
		String sql = "insert into month (day,price) values (sysdate, ?) ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, i);
		int result = pstat.executeUpdate();
		con.commit();
	}

	public int monthTotalPrice(int i) throws Exception {  //9월에 있는 정보를 뽑아오는거.
		Connection con = this.getConnection();
		int result = 0;
		String str = "2017-09-"+i;
		String sql = "select price from month WHERE trunc(day) = to_date(?,'yyyy-mm-dd')";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, str);
		ResultSet rs = pstat.executeQuery();
		if(rs.next()){
		 result = rs.getInt("price"); //여기에 컬럼 이름을 넣어줌
		}
		rs.close();
		return result;
	}
		
	public int monthPrice(int i) throws Exception { //1월부터 8월까지의 총액을 뽑아오는것 
		Connection con = this.getConnection();
		int result = 0;
		String str = "2017-"+i+"-01";
		//Date d = Date.valueOf(str);
		String sql = "select amount from monthlysale where trunc(month) = to_date(?, 'yyyy-mm-dd') ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, str);
		ResultSet rs = pstat.executeQuery();
		if(rs.next()) {
			result = rs.getInt("amount");
		}
		rs.close();
		return result;
		
	}
}