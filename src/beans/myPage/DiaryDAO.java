package beans.myPage;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import common.D;

public class DiaryDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public DiaryDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("DiaryDAO생성, 데이터베이스 연결!!");
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

	public int insert(String title, String regdate, 
			String t_content, String img, String id) throws SQLException {
		
		int cnt = 0;
		
		try {
			
			pstmt = conn.prepareStatement(D.SQL_DIARY_INSERT);
			pstmt.setString(1, title);
			pstmt.setString(2, regdate);
			pstmt.setString(3, t_content);
			pstmt.setString(4, img);
			pstmt.setString(5, id);
			
			cnt = pstmt.executeUpdate();
						
		}finally {
			close();
		}
		
		return cnt;
	}
	
	public int insert(DiaryDTO dto) throws SQLException {
		
		String title = dto.getTitle();
		String regdate = dto.getRegdate();
		String t_content = dto.getContent();
		String img = dto.getImg();
		String id = dto.getId();
		
		int cnt = this.insert(title, regdate, t_content, img, id); 
		
		return cnt;
		
	}
	
	public DiaryDTO [] createArray(ResultSet rs) throws SQLException {
		DiaryDTO [] arr = null;
		
		ArrayList<DiaryDTO> list = new ArrayList<DiaryDTO>();
		
		while(rs.next()) {
			int no = rs.getInt("diary_no");
			String title = rs.getString("title");
			
			Date d = rs.getDate("regdate");
			Time t = rs.getTime("regdate");
		
			String regdate = "";
			if(d != null) {
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + "T"
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			
			
			String t_content = rs.getString("t_content");
			if(t_content == null) t_content = "";
			String img = rs.getString("img");
			String id = rs.getString("user_id");
	
			DiaryDTO dto = new DiaryDTO(no, title, regdate, t_content, img, id);
			list.add(dto);
		}
		
		arr = new DiaryDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);  // 리스트 -> 배열
		
		return arr;
	}
	
	public DiaryDTO [] select() throws SQLException {
		DiaryDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIARY_SELECT);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		 
		return arr;
	}
	
	public DiaryDTO [] selectById(String id) throws SQLException {
		
		DiaryDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIARY_SELECT_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}
	
	public DiaryDTO [] selectByNo(int no) throws SQLException {
		
		DiaryDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIARY_SELECT_NO);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	}
	
	public int update(int no, String title, String t_content, String img) throws SQLException{
		
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIARY_UPDATE);
			pstmt.setString(1, title);
			pstmt.setString(2, t_content);
			pstmt.setString(3, img);
			pstmt.setInt(4, no);
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}		
		
		return cnt;
	} 
	
	public int deleteByNo(int no) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DIARY_DELETE_BY_NO);
			pstmt.setInt(1, no);
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	} 
	
	
	
}
