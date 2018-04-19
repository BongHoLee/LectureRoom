package server.vo;

public class UsePc {
	String c_id;
	String clientIp;
	String m_id;
	int pc_no;
	int usetime;
	int usecharge;
	int usetotal;
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getPc_no() {
		return pc_no;
	}
	public void setPc_no(int pc_no) {
		this.pc_no = pc_no;
	}
	public int getUsetime() {
		return usetime;
	}
	public void setUsetime(int usetime) {
		this.usetime = usetime;
	}
	public int getUsecharge() {
		return usecharge;
	}
	public void setUsecharge(int usecharge) {
		this.usecharge = usecharge;
	}
	public int getUsetotal() {
		return usetotal;
	}
	public void setUsetotal(int usetotal) {
		this.usetotal = usetotal;
	}
	

}
