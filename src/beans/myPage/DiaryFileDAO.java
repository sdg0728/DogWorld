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

public class DiaryFileDAO {

	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public DiaryFileDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("DiaryDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		} // end try
	}
	
	// DB 자원 반납 메소드, 만들어놓으면 편함..
	public void close() throws SQLException{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	} // end close();
	
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
	
	public void deleteFiles(DiaryDTO [] arr, HttpServletRequest request) {
		
		if(arr == null || arr.length == 0 || request == null || arr[0].getImg()=="" || arr[0].getImg()==null) return;
		
		
		
		// 물리적인 경로 
		ServletContext context = request.getServletContext();	// 물리적으로 저장된 파일들이 삭제 대상
		String saveDirectory = context.getRealPath("upload/diary");
		
		for (DiaryDTO dto : arr) {
				
			File f = new File(saveDirectory, dto.getImg());
			System.out.println("삭제시도--> " + f.getAbsolutePath());
			
			if(f.exists()) {
				if(f.delete()) {
					System.out.println("삭제 성공");
				}else {
					System.out.println("삭제 실패");
				}
			}
			
		}
		
	}
	
	public int deleteFilesByNo(int no, HttpServletRequest request) throws SQLException {
		int cnt = 0;
		
		DiaryDTO [] arr = null;
		
		try {
			// 1. 물리적인 파일 삭제
			pstmt = conn.prepareStatement(D.SQL_DIARY_SELECT_NO);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
			
			deleteFiles(arr, request);	// 파일 삭제
			
		}finally {
			close();
		}
		
		return cnt;
		
	}	
		
}
