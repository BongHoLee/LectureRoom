package client.vo;

public class Customer {
	private String c_id;						//고객 ID
	private String c_pw;					//고객 PW
	
	public Customer() {}
	public Customer(String c_id, String c_pw) {
		super();
		this.c_id = c_id;
		this.c_pw = c_pw;
	}
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_pw() {
		return c_pw;
	}
	public void setC_pw(String c_pw) {
		this.c_pw = c_pw;
	}
	

}
