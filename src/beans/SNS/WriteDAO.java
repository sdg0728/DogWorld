
package beans.SNS;

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
import java.util.List;

import beans.userinfo.PetDTO;
import beans.userinfo.UserDTO;
import common.D;

public class WriteDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection도 생성된다.
	public WriteDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end 생성자
	
	
	// DB 자원 반납 메소드, 만들어 놓으면 편함.
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	} // end close()
	/*
   post_id number NOT NULL,
   t_content clob,
   img varchar2(100),
   postdate timestamp DEFAULT sysdate,
   PRIMARY KEY (post_id)
	 */
	
	public int insert(String t_content, String img, String user_id) throws SQLException {
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_INSERT);
			pstmt.setString(1, t_content);
			pstmt.setString(2, img);
			pstmt.setString(3, user_id); //aaa -> user_id
			
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		return cnt;
	} // end insert();

	
	// ResultSet --> DTO 배열로 리턴
	public WriteDTO [] createArray(ResultSet rs) throws SQLException {
		WriteDTO [] arr = null;  // DTO 배열로 리턴
		
		ArrayList<WriteDTO> list = new ArrayList<WriteDTO>();
		
		while(rs.next()) {
			int post_id = rs.getInt("post_id");
			String t_content = rs.getString("t_content");
			String img = rs.getString("img");
			String user_id = rs.getString("user_id");
			if(t_content == null) t_content = "";
			
			Date d = rs.getDate("postdate");
			
			String postdate = "";
			if(d != null) {
				postdate = new SimpleDateFormat("yyyy-MM-dd").format(d);
			}
			
			WriteDTO dto = new WriteDTO(post_id, user_id, t_content, img);
			dto.setPostdate(postdate);
			list.add(dto);
		} // end while
		
		arr = new WriteDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큼의 배열 생성 
		list.toArray(arr);  // 리스트 -> 배열
			
		return arr;
	} // end createArray()
	
	// 전체 SELECT  --> 친구 or 인기
	public WriteDTO [] select(String user_id) throws SQLException {
		WriteDTO [] arr = null;
		
		try {
			//쿼리문 수정
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			
			
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		 
		return arr;
	} // end select();
	
	
	// 특정 uid 의 글만 SELECT --> 마이 피드
	public WriteDTO[] selectByUid(String user_id) throws SQLException {
		WriteDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
		} finally {
			close();
		} // end try
		
		return arr;
	} // end selectByUid()
	
	//댓글 가져오기
	public WriteDTO[] selectByPostid(int post_id) throws SQLException {
		WriteDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_COMMENT);
			pstmt.setInt(1, post_id);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
		} finally {
			close();
		} // end try
		
		return arr;
	} // end selectByUid()
	
	
	// 특정 uid 의 글 수정 (제목, 내용)
	public int update( String t_content, int post_id) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_UPDATE);
			pstmt.setString(1, t_content);
			pstmt.setInt(2, post_id);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try		
		
		return cnt;
	} // end update()
	
	//댓글 Db insert
	public int commentinsert(String t_content, int post_id, String user_id) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_COMMEND_INSERT);
			pstmt.setString(1, t_content);
			pstmt.setInt(2, post_id);
			pstmt.setString(3, user_id);

			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try		
		
		return cnt;
	} // end update()
	
	
	// 특정 uid 글 삭제하기
	public int deleteByPid(int post_id) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_UID);
			pstmt.setInt(1, post_id);
			
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	} // end deleteByUid()
	
	
	//댓글삭제 
	public int deleteByCid(int comment_id) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_COMMENTUID);
			pstmt.setInt(1, comment_id);
			
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	} // end deleteByCid()
	
	
	//특정 글 댓글 가져오기
	public CommentDTO[] selectCommentByPid(String user_id) throws SQLException{
		CommentDTO[] arr = null;
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		
		try {
		pstmt = conn.prepareStatement(D.SQL_COMMENT_SELECT_BY_POSTID);	
		pstmt.setString(1, user_id);
		pstmt.setString(2, user_id);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int post_id = rs.getInt("post_id"); 	
			String t_content = rs.getString("t_content");
			String regdate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("regdate"));   
			String userId = rs.getString("user_id");
			int comment_id = rs.getInt("comment_id");   
			
			CommentDTO dto = new CommentDTO(post_id, userId, t_content,regdate,comment_id);
			System.out.println(dto);
			list.add(dto);
		}
		arr = new CommentDTO[list.size()];
		list.toArray(arr);
		
		} finally {
			close();
		}
		
		return arr;
	}
	
	
	//팔로워 구하는 거 
	public FriendsDTO[] selectByfid(String follower) throws SQLException {
		FriendsDTO[] arr = null;
		
		List<FriendsDTO> f_list = new ArrayList<FriendsDTO>();
		
		try {
			pstmt = conn.prepareStatement(D.SQL_FRIENDS_SELECT_BY_FOLLOWER);
			pstmt.setString(1, follower);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String follower1 = rs.getString("follower");
				String following = rs.getString("following");
				FriendsDTO dto = new FriendsDTO(follower1,following);
				System.out.println(dto);
				f_list.add(dto);
			}
			arr = new FriendsDTO[f_list.size()]; //팔로워들이 담다 
			f_list.toArray(arr);
			
			} finally {
				close();
			}
		return arr;
	} // end selectByUid()
	
	//팔로잉 구하는 거 
	public FriendsDTO[] selectByfwid(String following) throws SQLException {
		FriendsDTO[] arr = null;
		
		List<FriendsDTO> fw_list = new ArrayList<FriendsDTO>();
		
		try {
			pstmt = conn.prepareStatement(D.SQL_FRIENDS_SELECT_BY_FOLLOWING);
			pstmt.setString(1, following);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String follower = rs.getString("follower");
				String following1 = rs.getString("following");
				FriendsDTO dto = new FriendsDTO(follower,following1);
				System.out.println(dto);
				fw_list.add(dto);
			}
			arr = new FriendsDTO[fw_list.size()]; //팔로워들이 담다 
			fw_list.toArray(arr);
			
			} finally {
				close();
			}
		return arr;
	} // end selectByUid()
	
	
	//아이디 검색
	public UserDTO[] selectBySearchUid(String user_id) throws SQLException {
		UserDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SEARCH_SELECT_UID);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			arr = userArray(rs);
		} finally {
			close();
		} // end try
		
		return arr;
	} // end selectByUid()
	
	
	//젯종류 검색
	public PetDTO[] selectBySearchKind(String kind) throws SQLException {
		PetDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SEARCH_SELECT_PETKIND);
			pstmt.setString(1, kind);
			rs = pstmt.executeQuery();
			
			arr = petArray(rs);
		} finally {
			close();
		} // end try
		
		return arr;
	} // end selectByUid()
	
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
	
	
	public int followingaddByid(String follower, String following) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_FOLLOWING_ADD);
			pstmt.setString(1, follower);
			pstmt.setString(2, following);
			pstmt.setString(3, follower);
			pstmt.setString(4, following);
			
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	} // end deleteByCid()
	
	//언팔
	public int followingdeleteByid(String follower, String following) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_FOLLOWING_DELETE);
			pstmt.setString(1, follower);
			pstmt.setString(2, following);
		
			
			cnt = pstmt.executeUpdate();			
		} finally {
			close();
		}
		
		return cnt;
	} 
	
	
	//좋아요 
	public int likeinsert( int post_id, String user_id) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_POST_LIKE_INSERT);
			pstmt.setInt(1, post_id);
			pstmt.setString(2, user_id);

			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try		
		
		return cnt;
	} // end update()
	
	
	public int likedelete( int post_id, String user_id) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_POST_LIKE_DELETE);
			pstmt.setInt(1, post_id);
			pstmt.setString(2, user_id);

			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try		
		
		return cnt;
	} // end update()
	
	
	public LikeDTO[] likeselect(String user_id) throws SQLException {
		LikeDTO[] arr = null;
		
		List<LikeDTO> like_list = new ArrayList<LikeDTO>();
		
		try {
			pstmt = conn.prepareStatement(D.SQL_POST_LIKE_SELECT);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int post_id1 = rs.getInt("post_id");
				String user_id1 = rs.getString("user_id");
				LikeDTO dto = new LikeDTO(post_id1,user_id1);
				System.out.println(dto);
				like_list.add(dto);
			}
			arr = new LikeDTO[like_list.size()]; //팔로워들이 담다 
			like_list.toArray(arr);
			
			} finally {
				close();
			}
		return arr;
	} // end selectByUid()
	
}





















