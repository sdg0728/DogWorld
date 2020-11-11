package beans.myPage;

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

import common.D;

public class ScheduleDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public ScheduleDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("ScheduleDAO생성, 데이터베이스 연결!!");
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
	
	public int insert(String t_content, String start_date, 
			String end_date, String id) throws SQLException {
		
		int cnt = 0;
		
		try {
			
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_INSERT);
			pstmt.setString(1, t_content);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			pstmt.setString(4, id);
			
			cnt = pstmt.executeUpdate();
						
		}finally {
			close();
		}
		
		return cnt;
	}
	
	public int insert(ScheduleDTO dto) throws SQLException {
		
		String t_content = dto.getContent();
		String start_date = dto.getStart_date();
		String end_date = dto.getEnd_date();
		String id = dto.getId();
		
		int cnt = this.insert(t_content, start_date, end_date, id); 
		
		return cnt;
		
	}
	
	public ScheduleDTO [] createArray(ResultSet rs) throws SQLException {
		ScheduleDTO [] arr = null;
		
		ArrayList<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
		
		while(rs.next()) {
			int no = rs.getInt("schedule_no");
			String t_content = rs.getString("t_content");
			
			Date s_d = rs.getDate("start_date");
			Time s_t = rs.getTime("start_date");
			
			String start_date = "";
			if(s_d != null) {
				start_date = new SimpleDateFormat("yyyy-MM-dd").format(s_d) + "T"
						+ new SimpleDateFormat("HH:mm:ss").format(s_t);
			}
			
			Date e_d = rs.getDate("end_date");
			Time e_t = rs.getTime("end_date");
			String end_date = "";
			if(e_d != null) {
				end_date = new SimpleDateFormat("yyyy-MM-dd").format(e_d) + "T"
						+ new SimpleDateFormat("HH:mm:ss").format(e_t);
			}
			
			int s_check = rs.getInt("s_check");
			
			String id = rs.getString("user_id");
	
			ScheduleDTO dto = new ScheduleDTO(no, t_content, start_date, end_date, s_check, id);
			list.add(dto);
		}
		
		arr = new ScheduleDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);
		
		return arr;
	}
	
	public ScheduleDTO [] select() throws SQLException {
		ScheduleDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_SELECT);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		 
		return arr;
	}
	
	public ScheduleDTO [] selectById(String id) throws SQLException {
		
		ScheduleDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_SELECT_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public ScheduleDTO [] selectByNo(int no) throws SQLException {
		ScheduleDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_SELECT_NO);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	public ScheduleDTO [] selectToday(String id) throws SQLException {
		ScheduleDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_TODAY_SCEDULE_SELECT_BY_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
			
		} finally {
			close();
		}
		
		return arr;
	}
	
	public int update(int no, String t_content, String start_date, 
			String end_date) throws SQLException{
		
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_UPDATE_NO);
			pstmt.setString(1, t_content);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
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
			pstmt = conn.prepareStatement(D.SQL_SCEDULE_DELETE_BY_NO);
			pstmt.setInt(1, no);
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int updateToday(int no, int s_check) throws SQLException{
		
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_TODAY_UPDATE_NO);
			pstmt.setInt(1, s_check);
			pstmt.setInt(2, no);
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}		

		return cnt;
	}

}
