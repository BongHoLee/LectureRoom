package server.vo;


public class SCustomer {
	private String c_id;						//고객 ID
	private String c_pw;					//고객 PW
	private String c_tel;					//고객 tel
	
	public SCustomer() {}
	public SCustomer(String c_id, String c_pw) {
		super();
		this.c_id = c_id;
		this.c_pw = c_pw;
	}
	
	public SCustomer(String c_id, String c_pw, String c_tel){
		super();
		this.c_id = c_id;
		this.c_pw = c_pw;
		this.c_tel = c_tel;
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
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	

}

