package beans.userinfo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.D;

public class SignDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public SignDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("SignDAO생성, 데이터베이스 연결!!");
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
	} // end close();
	
	public int signInsert(String id, String pw, String name, 
			String address, String email, String phone, String petName,
			String dogBreed, int petAge, String img) throws SQLException {
		
		int cnt = 0;
		
		try {
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.SQL_USERINFO_INSERT);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, address);
			pstmt.setString(5, email);
			pstmt.setString(6, phone);
			
			cnt = pstmt.executeUpdate();
						
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_PET_INSERT);
			pstmt.setString(1, petName);
			pstmt.setString(2, dogBreed);
			pstmt.setInt(3, petAge);
			pstmt.setString(4, img);
			pstmt.setString(5, id);
			
			cnt += pstmt.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			conn.rollback();  // 예외 발생하면 rollback
			throw e;
		}
		finally {
			close();
		}
		
		return cnt;
	}
	
	public int signInsert(UserDTO uDto, PetDTO pDto) throws SQLException {
		
		String id = uDto.getId();
		String pw = uDto.getPw();
		String name = uDto.getName();
		String address = uDto.getAddress();
		String email = uDto.getEmail();
		String phone = uDto.getPhone();
		String petName = pDto.getPetName();
		String dogBreed = pDto.getDogBreed();
		int petAge = pDto.getPetAge();
		String img = pDto.getImg();
		
		int cnt = this.signInsert(id, pw, name, address, email, phone, petName, dogBreed, petAge, img);
		
		return cnt;
		
	}
	
	public UserDTO [] userArray(ResultSet rs) throws SQLException {
		UserDTO [] arr = null;
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		
		while(rs.next()) {
			String id = rs.getString("user_id");
			String pw = rs.getString("password");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			
			UserDTO dto = new UserDTO(id, pw, name, address, email, phone);
			list.add(dto);
		}
		
		arr = new UserDTO[list.size()];
		list.toArray(arr);
		
		return arr;
	}
	
	public UserDTO [] select() throws SQLException {
		UserDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_USERINFO_SELECT);
			rs = pstmt.executeQuery();
			arr = userArray(rs);
			
		} finally {
			close();
		}
		
		return arr;
	}
	
	public UserDTO [] selectById(String userId) throws SQLException {
		
		UserDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_USERINFO_SELECT_BY_ID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			arr = userArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}	
	
	public UserDTO [] selectByEmail(String email) throws SQLException {
		
		UserDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_USERINFO_SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			arr = userArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}
	
	public UserDTO [] selectSearchPw(String id, String email, String phone) throws SQLException {
		
		UserDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_USERINFO_SELECT_SEARCH_PW);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
			arr = userArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}
	
	
	
	public PetDTO [] petArray(ResultSet rs) throws SQLException {
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
	
	public PetDTO [] selectPetById(String id) throws SQLException {
		
		PetDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_PET_SELECT_BY_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			arr = petArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public int update(String id, String name, String address, String phone, String petName,
			String dogBreed, int petAge, String img) throws SQLException{
		
		int cnt = 0;
		
		try {
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.SQL_USERINFO_UPDATE);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_PET_UPDATE);
			pstmt.setString(1, petName);
			pstmt.setInt(2, petAge);
			pstmt.setString(3, dogBreed);
			pstmt.setString(4, img);
			pstmt.setString(5, id);
			
			cnt += pstmt.executeUpdate();
			
			conn.commit();
			
		} catch(SQLException e) {
			conn.rollback();  // 예외 발생하면 rollback
			throw e;		  // 예외를 다시 throw
		} 
		finally {
			close();
		}
		
		return cnt;
	}
	
	public int updatePassword(String id, String password) throws SQLException{
		
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_USERINFO_UPDATE_PASSWORD);
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int delete(String id) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_PET_DELETE);
			pstmt.setString(1, id);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_USERINFO_DELETE);
			pstmt.setString(1, id);
			cnt += pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
		
}
