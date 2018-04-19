package server.vo;

//PC 테이블에 대한 정보를 담는 객체
public class PcInfo {
	String pc_ip;
	int pc_no;
	int pc_flag;
	
	public String getPc_ip() {
		return pc_ip;
	}
	public void setPc_ip(String pc_ip) {
		this.pc_ip = pc_ip;
	}
	public int getPc_no() {
		return pc_no;
	}
	public void setPc_no(int pc_no) {
		this.pc_no = pc_no;
	}
	public int getPc_flag() {
		return pc_flag;
	}
	public void setPc_flag(int pc_flag) {
		this.pc_flag = pc_flag;
	}

}
