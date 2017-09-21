package Point;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PointDBManager {


	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "java7";
		String pw = "java7";

		Connection con = 
				DriverManager.getConnection(url,id,pw);
		return con;

	}
	public boolean isContactExist (String contact) throws Exception{ // ����ó�� �����ϴ��� ��ȸ�ϴ� ���

		Connection con = this.getConnection();
		String sql = "select * from membership where contact = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, contact);
		ResultSet rs = pstat.executeQuery();

		boolean result = rs.next();
		con.close();
		return result;
	}
	
	
	public int insertMember(String name, String contact, int zero) throws Exception{// ��� �ֱ� ���� �̸�, ����ó�� �޴� ��.
		Connection con = this.getConnection();
		String sql = "insert into membership values(?,?,?)";//�����ۼ� //���������̺���seqĮ��������������߰�
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, name);
		pstat.setString(2, contact);
		pstat.setInt(3, zero);
		int result = pstat.executeUpdate();

		con.commit();
		con.close();

		return result;
	}

	public int updatePoint (int point, String contact) throws Exception { 
		// updatePoint �޽��� point�� contact�� �޾ƿ��� int���� ���������� ����� �ϴ� class�� �Ѱ��ش�.
		// �׶� �Ѱ��ִ� int���� result�� ��´�.
		Connection con = this.getConnection();

		String sql = "update membership set point=? where contact like ?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setInt(1, point);
		pstat.setString(2, contact);
		int result = pstat.executeUpdate();

		con.commit();
		con.close();
		return result;
	}


	public ArrayList<MembershipPoint> selectPoint(String contact) throws Exception{

		Connection con = this.getConnection();
		String sql = "select * from membership where contact = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, contact);
		ResultSet rs = pstat.executeQuery();

		ArrayList<MembershipPoint> result = new ArrayList<MembershipPoint>();	//ResultSet�� �ִ� �ڷ����� �������� �������� ��̸���Ʈ �����, �װ͵��� Ǯ������ �ϴ� ���׸��� ��������� ��Ʃ��Ʈ�� Ŭ������ ���� �ȿ� id����� �� �����
		while(rs.next()){
			String name = rs.getString("name");
			int point = rs.getInt("point");

			result.add(new MembershipPoint(name,contact, point));
		}
		return result;
	}

	public int updateContact (String newContact, String contact) throws Exception { 
		// updatePoint �޽��� point�� contact�� �޾ƿ��� int���� ���������� ����� �ϴ� class�� �Ѱ��ش�.
		// �׶� �Ѱ��ִ� int���� result�� ��´�.
		Connection con = this.getConnection();

		String sql = "update membership set contact=? where contact like ?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setString(1, newContact);
		pstat.setString(2, contact);
		int result = pstat.executeUpdate();

		con.commit();
		con.close();
		return result;
	}


}
