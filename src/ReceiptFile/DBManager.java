package ReceiptFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {
	
	private PreparedStatement pstat;
	private Statement statement;

	private ResultSetMetaData resultSetMetaData = null;
	private ResultSet resultSet = null; 
	
	private Object [] tempObject;

	
	
	private Connection getConnection() throws Exception{ // DB 연결.

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = 
				DriverManager.
				getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","java7","java7");

		return con;

	}

	public int insertOrder(Receipt tmp) throws Exception{ // 결제 내용 저장.

		Connection con = this.getConnection();

		String sql = "insert into receipt values(receipt_seq.nextval, ?,sysdate,?,?,?,?)";
		pstat = con.prepareStatement(sql);
		pstat.setInt(1, tmp.getTableNum());
		pstat.setString(2, tmp.getOrderComp());
		pstat.setInt(3, tmp.getTotalPrice());
		pstat.setInt(4, tmp.getIncome());
		pstat.setInt(5, tmp.getChange());

		int result=pstat.executeUpdate();

		con.commit();
		con.close();
		
		return result;

	}

	public ArrayList<Object[]> searchByKeyword(int searchMode, String keyWord) throws Exception{ // 검색어 조회.

		Connection con = this.getConnection();
		statement = con.createStatement();
		
		switch(searchMode) {

		case 0:
			resultSet = statement.executeQuery("select * from receipt where orderid = " + keyWord + "");
			break;

		case 1:
			resultSet = statement.executeQuery("select * from receipt where tablenum = " + keyWord + "");
			break;

		case 2:
			resultSet = statement.executeQuery("select * from receipt where ordercomp like '%" + keyWord + "%'");
			break;

		}

		this.resultSetMetaData = resultSet.getMetaData();
		
		ArrayList<Object[]> result = new ArrayList();

		while (resultSet.next())
		{
			tempObject = new Object[resultSetMetaData.getColumnCount()];
			for(int i=0; i < resultSetMetaData.getColumnCount(); i++)
			{
				tempObject[i] = resultSet.getString(i+1);
			}

			result.add(tempObject);

		}
		
		con.commit();
		con.close();

		return result;
	}

	public ArrayList<Object[]> searchByDate(String[] tmp1, String[] tmp2) throws Exception{ // 기간별 조회.
		
		String date1 = tmp1[0]+"-"+tmp1[1]+"-"+tmp1[2];
		String date2 = tmp2[0]+"-"+tmp2[1]+"-"+tmp2[2];

		Connection con = this.getConnection();

		String sql = "select * from receipt where regdate >= to_date(?,'yyyy-mm-dd') and regdate <= to_date(?,'yyyy-mm-dd') + 0.99999";
		pstat = con.prepareStatement(sql);
		pstat.setString(1, date1);
		pstat.setString(2, date2);
		
		this.resultSet = pstat.executeQuery();
		this.resultSetMetaData = resultSet.getMetaData();
		
		ArrayList<Object[]> result = new ArrayList();
		
		while (resultSet.next())
		{
			tempObject = new Object[resultSetMetaData.getColumnCount()];
			for(int i=0; i < resultSetMetaData.getColumnCount(); i++)
			{
				tempObject[i] = resultSet.getString(i+1);
			}

			result.add(tempObject);

		}
		
		con.commit();
		con.close();

		return result;

	}
	
}

