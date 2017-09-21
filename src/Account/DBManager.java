package Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//�ؾ��� ��
//���� ����� ���� �޾ƿ� -> ����� ���� �޾Ƽ� ��� �����ؾ��մϴ�
//��񿡼� �⵵�� ������ �������°� �ϳ�
//���� �������°� �ϳ�
//�Ϻ� �������°� �ϳ�
//��� �� ������ : ��¥, �Ѿ�

public class DBManager {
	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","java7","java7");
		
		return con;
	}
	public int dataSize() throws Exception { //�̰� �� �� ���� ��
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
	public void insertAllPrice(int i) throws Exception { //���� �Ѹ��� ����
		Connection con = this.getConnection();
		String sql = "insert into month (day,price) values (sysdate, ?) ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, i);
		int result = pstat.executeUpdate();
		con.commit();
	}

	public int monthTotalPrice(int i) throws Exception {  //9���� �ִ� ������ �̾ƿ��°�.
		Connection con = this.getConnection();
		int result = 0;
		String str = "2017-09-"+i;
		String sql = "select price from month WHERE trunc(day) = to_date(?,'yyyy-mm-dd')";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, str);
		ResultSet rs = pstat.executeQuery();
		if(rs.next()){
		 result = rs.getInt("price"); //���⿡ �÷� �̸��� �־���
		}
		rs.close();
		return result;
	}
		
	public int monthPrice(int i) throws Exception { //1������ 8�������� �Ѿ��� �̾ƿ��°� 
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