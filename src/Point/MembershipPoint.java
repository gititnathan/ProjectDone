package Point;
import java.sql.Timestamp;

import java.sql.Timestamp;

public class MembershipPoint {
	private String name;
	private String contact;
	private int point;

	public MembershipPoint(String name, String contact, int point) {
		this.name = name;
		this.contact = contact;
		this.point = point;

	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	



}