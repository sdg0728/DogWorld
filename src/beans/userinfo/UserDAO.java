package beans.userinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.D;

public class UserDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public UserDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("FileDAO 생성, 데이터베이스 연결!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}
	
	public UserDTO login(String user_id) throws SQLException {
		UserDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_EXISTS_USER);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("user_id");
				String pw = rs.getString("password");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String phone = rs.getString("phone");

				dto = new UserDTO(id, pw, name, address, email, phone);
			}
		} finally {
			close();
		}
		
		return dto;
	}
}
