package server.model;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.vo.PcInfo;

//PcInfo 객체에 정보를 담거나 받아와서 PC 테이블을 조작하는 모델
public class PcInfoModel {
	private Connection con;
	
	//DB에 연동시킴
	public PcInfoModel() throws SQLException {
		con = DBCon.getConnection();
	}
	
	//PC의 IP를 받아와서 해당 NO를 반환
	public int selectPcNo(InetAddress inetAddress){
		int pc_no = 0;
		String ip = inetAddress.toString();
		String sql = "SELECT pc_no FROM pc WHERE pc_ip=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ip);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				pc_no = rs.getInt("PC_NO");
			}else{
				System.out.println("입력이 잘못된 IP 입니다.");
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("SELECT 오류");
			e.printStackTrace();
		}
		
		return pc_no;
	}
	
	//PcInfo 객체를 받아와서 PC_NO에 해당하는 FLAG 1로 변환
	public void updatePcFlag(PcInfo pcinfo){
		String sql = "UPDATE pc SET pc_flag=1 WHERE pc_no=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pcinfo.getPc_no());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
