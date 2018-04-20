package server.vo;

import java.io.Serializable;

public class Order implements Serializable{
	private int use_no;
	private int pro_no;
	
	
	public int getUse_no() {
		return use_no;
	}
	public void setUse_no(int use_no) {
		this.use_no = use_no;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
}
