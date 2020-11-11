package beans.myPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.userinfo.PetDTO;
import common.D;

public class MyPetDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public MyPetDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("MyPetDAO생성, 데이터베이스 연결!!");
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
	
	public PetDTO [] createArray(ResultSet rs) throws SQLException {
		PetDTO [] arr = null;
		ArrayList<PetDTO> list = new ArrayList<PetDTO>();
		
		while(rs.next()) {
			int petId = rs.getInt("pet_id");
			String petName = rs.getString("name");
			String dogBreed = rs.getString("kind");
			int petAge = rs.getInt("age");
			String img = rs.getString("img");
			String id = rs.getString("user_id");
			PetDTO dto = new PetDTO(petId, petName, dogBreed, petAge, img, id);
			list.add(dto);
		}
		
		arr = new PetDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);
		
		return arr;
	}
	
	public PetDTO [] selectById(String id) throws SQLException {
		
		PetDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_PET_SELECT_BY_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public int update(String id, String petName, String dogBreed, int petAge, String img) throws SQLException{
		
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_PET_UPDATE);
			pstmt.setString(1, petName);
			pstmt.setInt(2, petAge);
			pstmt.setString(3, dogBreed);
			pstmt.setString(4, img);
			pstmt.setString(5, id);
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}		
		
		return cnt;
		
	}
	
}
