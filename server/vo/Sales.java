package server.vo;

import java.util.Date;

public class Sales {
	private Date sales_date;
	private int sales_daily;
	private int sales_total;
	
	public Date getSales_date() {
		return sales_date;
	}
	public void setSales_date(Date sales_date) {
		this.sales_date = sales_date;
	}
	public int getSales_daily() {
		return sales_daily;
	}
	public void setSales_daily(int sales_daily) {
		this.sales_daily = sales_daily;
	}
	public int getSales_total() {
		return sales_total;
	}
	public void setSales_total(int sales_total) {
		this.sales_total = sales_total;
	}
	
}
