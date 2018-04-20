package server.vo;

// 판매하는 상품의 정보를 담는 객체
public class Product {
	private int pro_no;				// 메뉴 번호
	private String pro_name;		// 메뉴 이름
	private int pro_stock;			// 메뉴 재고
	private int pro_price;			// 메뉴 가격
	
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_stock() {
		return pro_stock;
	}
	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	
	
}
