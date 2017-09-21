package ReceiptFile;


public class Receipt {

	public int tableNum;
	
	public String orderComp;
	
	public int totalPrice;
	
	public int income;
	
	public int change;

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public String getOrderComp() {
		return orderComp;
	}

	public void setOrderComp(String orderComp) {
		this.orderComp = orderComp;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public Receipt(int tableNum, String orderComp, int totalPrice, int income, int change) {
	
		this.tableNum = tableNum;
		this.orderComp = orderComp;
		this.totalPrice = totalPrice;
		this.income = income;
		this.change = change;
		
	}

	
	
}
