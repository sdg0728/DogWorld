package beans.myPage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import common.D;

public class DietDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public DietDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("DietDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		} // end try
	}// 생성자
	
	// DB 자원 반납 메소드, 만들어놓으면 편함..
	public void close() throws SQLException{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	public int insert(int weight, int petId) throws SQLException {
		
		int cnt = 0;
		
		try {
			
			pstmt = conn.prepareStatement(D.SQL_DIET_INSERT);
			pstmt.setInt(1, weight);
			pstmt.setInt(2, petId);
			
			cnt = pstmt.executeUpdate();
						
		}finally {
			close();
		}
		
		return cnt;
	}
	
	public int insert(DietDTO dto) throws SQLException {
		
		int weight = dto.getWeight();
		int petId = dto.getPetId();
		
		int cnt = this.insert(weight, petId); 
		
		return cnt;
		
	}
	
	public DietDTO [] createArray(ResultSet rs) throws SQLException {
		DietDTO [] arr = null;
		ArrayList<DietDTO> list = new ArrayList<DietDTO>();
		
		while(rs.next()) {
			int weight = rs.getInt("weight");
			int petId = rs.getInt("pet_id");
			
			Date d = rs.getDate("regdate");
			Time t = rs.getTime("regdate");
			String regDate = "";
			if(d != null) {
				regDate = new SimpleDateFormat("yyyy-MM-dd").format(d)+" "
						+new SimpleDateFormat("HH:mm:ss").format(t);
			}
			
			DietDTO dto = new DietDTO(weight, petId);
			dto.setRegdate(regDate);
			list.add(dto);
		}
		
		arr = new DietDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);
		
		return arr;
	}
	
	public DietDTO [] selectById(int petId) throws SQLException {
		
		DietDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIET_SELECT_BY_PETID);
			pstmt.setInt(1, petId);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public int update(int weight, int petId, String regdate) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIET_UPDATE_NO);
			pstmt.setInt(1, weight);
			pstmt.setInt(2, petId);
					
			pstmt.setString(3, regdate);
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}		
		
		return cnt;
	}
	
	public int deleteByNo(int petId) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIET_DELETE_BY_NO);
			pstmt.setInt(1, petId);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
}
