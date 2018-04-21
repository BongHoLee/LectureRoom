package server.vo;

//ORDER_PRO의 정보를 담는 VO 객체
public class UpOrder {
	int order_no;						
	int use_no;
	int pro_no;
	int order_flag;
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
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
	public int getOrder_flag() {
		return order_flag;
	}
	public void setOrder_flag(int order_flag) {
		this.order_flag = order_flag;
	}
}
