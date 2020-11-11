package beans.userinfo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import common.D;

public class PetFileDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public PetFileDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("PetFileDAO생성, 데이터베이스 연결!!");
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
	}
	
	public PetDTO [] createArray(ResultSet rs) throws SQLException {
		PetDTO [] arr = null;
		ArrayList<PetDTO> list = new ArrayList<PetDTO>();
		
		while(rs.next()) {
			int petId = rs.getInt("petId");
			String petName = rs.getString("petName");
			String dogBreed = rs.getString("dogBreed");
			int petAge = rs.getInt("petAge");
			String img = rs.getString("img");
			
			String id = rs.getString("user_id");
	
			PetDTO dto = new PetDTO(petId, petName, dogBreed, petAge, img, id);
			list.add(dto);
		}
		
		arr = new PetDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);
		
		return arr;
	}
	
	public void deleteFiles(PetDTO [] arr, HttpServletRequest request) {
		
		if(arr == null || arr.length == 0 || request == null || arr[0].getImg()=="" || arr[0].getImg()==null) return;
		
		
		
		// 물리적인 경로 
		ServletContext context = request.getServletContext();	// 물리적으로 저장된 파일들이 삭제 대상
		String saveDirectory = context.getRealPath("upload/user");
		
		for (PetDTO dto : arr) {
				
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
		
		PetDTO [] arr = null;
		
		try {
			// 1. 물리적인 파일 삭제
			pstmt = conn.prepareStatement(D.SQL_PET_SELECT_BY_NO);
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
