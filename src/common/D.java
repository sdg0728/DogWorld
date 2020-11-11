package common;

public class D {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USERID = "dogworld";
	public static final String USERPW = "dogworld123";
	
	//마이페이지 쿼리문 시작--------------------------------------------------------------------------------
	
	//login
	public static final String SQL_EXISTS_USER = 
	         "SELECT USER_INFO.* FROM USER_INFO WHERE EXISTS "
	         + "(SELECT USER_ID FROM USER_INFO WHERE USER_ID = ?) "
	         + "AND USER_ID = ?";
	
	// user
	public static final String SQL_USERINFO_INSERT = 
			"INSERT INTO user_info"
			+ "(user_id, password, name, address, email, phone) "
			+ "VALUES"
			+ "(?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_USERINFO_SELECT = 
			"SELECT * FROM user_info ORDER BY user_id DESC";
	
	public static final String SQL_USERINFO_SELECT_BY_ID = 
			"SELECT * FROM user_info WHERE user_id = ?";
	
	public static final String SQL_USERINFO_SELECT_BY_EMAIL = 
			"SELECT * FROM user_info WHERE email = ?";
	
	public static final String SQL_USERINFO_SELECT_SEARCH_PW = 
			"SELECT * FROM user_info WHERE user_id = ? AND email = ? AND phone = ?";
	
	public static final String SQL_PET_INSERT = 
			"INSERT INTO pet"
			+ "(pet_id, name, kind, age, img, user_id) "
			+ "VALUES"
			+ "(PET_SEQ.nextval, ?, ?, ?, ?, ?)";
	
	public static final String SQL_USERINFO_UPDATE = 
			"UPDATE user_info SET name = ?, address = ?, phone = ? WHERE user_id = ?";
	
	public static final String SQL_USERINFO_UPDATE_PASSWORD = 
			"UPDATE user_info SET password = ? WHERE user_id = ?";
	
	public static final String SQL_PET_UPDATE = 
			"UPDATE pet SET name = ?, age = ?, kind = ?, img = ? WHERE user_id = ?";
	
	public static final String SQL_USERINFO_PASSWORD_CHECK =
			"SELECT * FROM user_info WHERE user_id = ?";
	
	public static final String SQL_USERINFO_DELETE =
			"DELETE FROM user_info WHERE user_id = ?";
	
	// 지울 것
	public static final String SQL_PET_DELETE =
			"DELETE FROM pet WHERE user_id = ?";
	
	
	// myPet
	public static final String SQL_PET_SELECT_BY_ID = 
			"SELECT * FROM pet WHERE user_id = ?";
	
	public static final String SQL_PET_SELECT_BY_NO = 
			"SELECT * FROM pet WHERE pet_id = ?";
	
	public static final String SQL_TODAY_SCEDULE_SELECT_BY_ID = 
			"SELECT * FROM SCHEDULE WHERE user_id = ? AND TO_CHAR(TRUNC(SYSDATE), 'YYYYMMDD') >= TO_CHAR(START_DATE,'YYYYMMDD') AND TO_CHAR(TRUNC(SYSDATE), 'YYYYMMDD HH24MISS') < TO_CHAR(END_DATE,'YYYYMMDD HH24MISS')";
	
	public static final String SQL_TODAY_UPDATE_NO = 
			"UPDATE schedule SET s_check = ? WHERE schedule_no= ?";	
	
	
	// diet
	public static final String SQL_DIET_INSERT = 
			"INSERT INTO diet"
			+ "(weight, regdate, pet_id) "
			+ "VALUES"
			+ "(?, SYSDATE, ?)";
	
	public static final String SQL_DIET_SELECT_BY_PETID = 
			"SELECT * FROM diet WHERE pet_id = ? ORDER BY REGDATE";
	
	public static final String SQL_DIET_UPDATE_NO = 
			"UPDATE diet SET weight = ?, regdate = SYSDATE WHERE pet_id= ? AND regdate = TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')";
	
	public static final String SQL_DIET_DELETE_BY_NO =
			"DELETE FROM DIET WHERE (DIET.WEIGHT, DIET.REGDATE, DIET.PET_ID ) = (SELECT WEIGHT, REGDATE, PET_ID FROM(SELECT * FROM DIET WHERE pet_id= ? ORDER BY regdate DESC) WHERE ROWNUM = 1)";
	// diary
	public static final String SQL_DIARY_INSERT = 
			"INSERT INTO diary"
			+ "(diary_no, title, regdate, t_content, img, user_id) "
			+ "VALUES"
			+ "(DIARY_SEQ.nextval, ?, ?, ?, ?, ?)";
	
	public static final String SQL_DIARY_SELECT = 
			"SELECT * FROM diary ORDER BY diary_no DESC";
	
	public static final String SQL_DIARY_SELECT_ID = 
			"SELECT * FROM diary WHERE user_id = ?";
	
	public static final String SQL_DIARY_SELECT_NO = 
			"SELECT * FROM diary WHERE diary_no = ?";
	
	public static final String SQL_DIARY_UPDATE = 
			"UPDATE diary SET title = ?, t_content = ?, img = ? WHERE diary_no= ?";
	
	public static final String SQL_DIARY_DELETE_BY_NO =
			"DELETE FROM diary WHERE diary_no = ?";
	
	public static final String SQL_SCEDULE_INSERT = 
			"INSERT INTO schedule"
			+ "(schedule_no, t_content, start_date, end_date, s_check, user_id) "
			+ "VALUES"
			+ "(schedule_SEQ.nextval, ?, ?, ?, 0, ?)";
	
	// schedule
	public static final String SQL_SCEDULE_SELECT = 
			"SELECT * FROM schedule ORDER BY schedule_no DESC";
	
	public static final String SQL_SCEDULE_SELECT_ID = 
			"SELECT * FROM schedule WHERE user_id = ?";
	
	public static final String SQL_SCEDULE_SELECT_NO = 
			"SELECT * FROM schedule WHERE schedule_no = ?";
	
	public static final String SQL_SCEDULE_UPDATE_NO = 
			"UPDATE schedule SET t_content = ?, start_date = ?, end_date = ? WHERE schedule_no= ?";
	
	public static final String SQL_SCEDULE_DELETE_BY_NO =
			"DELETE FROM schedule WHERE schedule_no = ?";
	
	//마이페이지 쿼리문 끝---------------------------------------------------------------------------------
	
	
	
	//SNS 쿼리문 시작---------------------------------------------------------------------------------
	
	// 게시글 관련 쿼리문
	public static final String SQL_WRITE_INSERT = 
			"INSERT INTO post"
			+ "(post_id, t_content, img, postdate, user_id) "
			+ "VALUES"
			+ "(post_seq.nextval, ?, ?, SYSDATE, ?)";
	
	public static final String SQL_WRITE_SELECT =  //팔로잉 페이지 
			"SELECT * FROM post " + 
			"where user_id =? or user_id in " + 
			"(select friends.following from friends " + 
			"where friends.follower=?)order by postdate desc";
	
	public static final String SQL_SEARCH_SELECT_UID =  //아이디검색  
			"SELECT * FROM user_info WHERE user_id = ?";
	
	public static final String SQL_SEARCH_SELECT_PETKIND = //펫종류 검색 
			"SELECT * FROM PET WHERE kind = ?";
	
	public static final String SQL_WRITE_SELECT_BY_UID =
			"SELECT * FROM post WHERE user_id = ? ORDER BY post_id DESC";
	
	public static final String SQL_WRITE_SELECT_BY_COMMENT =
			"SELECT * FROM p_comment WHERE comment_id = ? ";
			
	
	public static final String SQL_WRITE_DELETE_BY_UID =
			"DELETE FROM post WHERE post_id = ?";
	
	//댓글지우기
	public static final String SQL_WRITE_DELETE_BY_COMMENTUID =
			"DELETE FROM p_comment WHERE comment_id = ?";

	public static final String SQL_WRITE_UPDATE = // 수정 필요 
			"UPDATE post SET t_content = ? WHERE post_id = ?";
	
	public static final String SQL_COMMEND_INSERT = //댓글넣기
			"INSERT INTO p_comment "
			+"(comment_id, t_content, regdate, post_id, user_id)"
			+"VALUES"
			+"(comment_seq.nextval,?,SYSDATE,?,?)";
	
	public static final String SQL_COMMENT_SELECT_BY_POSTID =
			"select p_comment.* from p_comment, " + 
			"(select post_id from post where user_id = ? or user_id in (select following from friends where follower = ?)) I " + 
			"where I.post_id = p_comment.post_id";
	
	public static final String SQL_FRIENDS_SELECT_BY_FOLLOWER = 
			"select * from friends where follower=?"; //팔로워 구하는 거  
	
	public static final String SQL_FRIENDS_SELECT_BY_FOLLOWING = 
			"select * from friends where following=?"; //팔로잉 구하는 거  
	
	//좋아요 
	public static final String SQL_POST_LIKE_INSERT =
			"insert into post_like (post_id, user_id) values (?,?)";
															//게시물 //유저아이디 	
	//좋아요 취소 
	public static final String SQL_POST_LIKE_DELETE = 
			"DELETE FROM post_like WHERE (post_id, user_id) = (SELECT post_id, user_id FROM post_like WHERE post_id=? AND user_id=?)";
	
	//팔로잉 추가 
	public static final String SQL_FOLLOWING_ADD = 
			"insert into friends (follower, following) " + 
			"select ?, ? from dual " + 
			"where not EXISTS " + 
			"(select * from friends where follower = ? and following = ?)";
												 //팔로잉하려는ID //유저본인
	
	public static final String SQL_FOLLOWING_DELETE = 
			"DELETE FROM friends WHERE (follower, following) = (SELECT follower, following FROM friends WHERE follower=? AND following=?)";
	
	
	public static final String SQL_POST_LIKE_SELECT =
			"select * from  post_like where user_id=?";
	
	//SNS 쿼리문 끝---------------------------------------------------------------------------------
	
	
	
	//구매 쿼리문 시작--------------------------------------------------------------------------------
	
	public static final String SQL_SELECT_GOODS_ALL = 
			"SELECT * FROM goods where category = ? ORDER BY goods_id";
	
	public static final String SQL_SELECT_GOODS = 
			"SELECT * FROM GOODS WHERE GOODS_ID = ?";
	
	public static final String SQL_SELECT_REVIEW = 
			"SELECT * FROM REVIEW WHERE GOODS_ID = ?";
	
	public static final String SQL_INSERT_REVIEW =
			"INSERT INTO REVIEW"
			+ " (USER_ID, GOODS_ID, t_content, IMAGE, REGDATE, REVIEW_ID)"
			+ " VALUES"
			+ " (?, ?, ?, ?, SYSDATE, REVIEW_SEQ.nextval)";
	
	public static final String SQL_DELETE_REVIEW = 
			"DELETE FROM REVIEW WHERE REVIEW_ID = ?";
	
	public static final String SQL_UPDATE_REVIEW = 
			"UPDATE REVIEW SET " + 
			"t_content = ?, " + 
			"IMAGE = ?, " + 
			"REGDATE = SYSDATE " +
			"WHERE REVIEW_ID = ?";
	
	public static final String SQL_UPDATE_CONTENT_REVIEW = 
			"UPDATE REVIEW SET " + 
			"t_content = ?, " + 
			"REGDATE = SYSDATE " +
			"WHERE REVIEW_ID = ?";
	
	public static final String SQL_INSERT_CART = 
			"INSERT INTO PURCHASE "
			+ "(USER_ID, GOODS_ID, P_CHECK, COUNT, purchase_id) "
			+ "VALUES "
			+ "(?, ?, 0, ?, purchase_seq.nextval)";
	
	public static final String SQL_SELECT_CART = 
			"SELECT * FROM PURCHASE "
			+ "WHERE USER_ID = ? AND P_CHECK = 0";
	
	public static final String SQL_DELETE_CART =
			"DELETE FROM PURCHASE WHERE PURCHASE_ID = ?";
	
	public static final String SQL_DELETE_ADMIN_PURCHASE =
			"DELETE FROM ADMIN_PURCHASE WHERE PURCHASE_ID = ?";
	
	public static final String SQL_SELECT_CARD = 
			"SELECT * FROM CARD WHERE USER_ID = ?";
	
	public static final String SQL_INSERT_CARD = 
			"INSERT INTO CARD "
			+ "(CARD_NUM, CVC, CARD_LIM_YEAR, CARD_LIM_MONTH, PASSWORD, USER_ID, BANK) "
			+ " VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_UPDATE_CARD = 
			"UPDATE CARD SET "
			+ "CARD_NUM = ?, "
			+ "CVC = ?, "
			+ "CARD_LIM_YEAR = ?, "
			+ "CARD_LIM_MONTH = ?, "
			+ "PASSWORD = ?, "
			+ "BANK = ? "
			+ "WHERE USER_ID = ?";
	
	public static final String SQL_SELECT_PURCHASE_ID = 
			"SELECT PURCHASE_ID FROM PURCHASE WHERE USER_ID = ? AND P_CHECK = 0";
	
	public static final String SQL_UPDATE_PURCHASE = 
			"UPDATE PURCHASE SET "
			+ "NAME = ?, "
			+ "DELIVER = ?, "
			+ "PHONE = ?, "
			+ "BUYDATE = SYSDATE, "
			+ "P_CHECK = 1 "
			+ "WHERE PURCHASE_ID = ?";
	
	public static final String SQL_INSERT_ADMIN_PURCHASE = 
			"INSERT INTO ADMIN_PURCHASE " + 
			"(USER_ID, GOODS_ID, NAME, DELIVER, PHONE, BUYDATE, P_CHECK, COUNT, PURCHASE_ID) " + 
			"SELECT PURCHASE.* FROM PURCHASE WHERE PURCHASE_ID = ?";
	
	public static final String SQL_SELECT_ORDERS = 
			"SELECT G.GOODS_ID, P.PURCHASE_ID, G.NAME, G.PRICE, G.IMG, G.DETAIL_IMG, P.COUNT, P.P_CHECK, P.BUYDATE " + 
			"FROM GOODS G, PURCHASE P WHERE G.GOODS_ID = P.GOODS_ID AND USER_ID = ? AND P.P_CHECK != 0";
	
	public static final String SQL_DELETE_GOODS = 
			"DELETE FROM GOODS WHERE GOODS_ID = ?";
	
	public static final String SQL_UPDATE_GOODS = 
			"UPDATE GOODS SET "
			+ "NAME = ?, "
			+ "PRICE = ?, "
			+ "IMG = ?, "
			+ "DETAIL_IMG = ?, "
			+ "COUNT = ? "
			+ "WHERE GOODS_ID = ?";
	
	public static final String SQL_INSERT_GOODS = 
			"INSERT INTO GOODS "
			+ "(GOODS_ID, NAME, PRICE, IMG, DETAIL_IMG, CATEGORY, COUNT) "
			+ "VALUES "
			+"(GOODS_SEQ.nextval, ?, ?, ?, ?, ? ,?)";
	
	public static final String SQL_SELECT_ADMIN_ORDERS = 
			"select goods.name as g_name, goods.price, admin_purchase.* from goods, admin_purchase " + 
			"where goods.goods_id = admin_purchase.goods_id order by admin_purchase.p_check asc";
	
	public static final String SQL_UPDATE_P_CHECK = 
			"UPDATE PURCHASE T SET T.P_CHECK = ? WHERE T.PURCHASE_ID = ?";
	
	public static final String SQL_UPDATE_ADMIN_P_CHECK = 
			"UPDATE ADMIN_PURCHASE T SET T.P_CHECK = ? WHERE T.PURCHASE_ID = ?";
	
	public static final String SQL_DECREASE_COUNT = 
			"UPDATE GOODS SET " + 
			"COUNT = (SELECT COUNT FROM GOODS WHERE GOODS_ID = 1) - ? " + 
			"WHERE GOODS_ID = ?";
	
	public static final String SQL_INCREASE_COUNT = 
			"UPDATE GOODS SET " + 
			"COUNT = (SELECT COUNT FROM GOODS WHERE GOODS_ID = ?) + ? " + 
			"WHERE GOODS_ID = ?";
	
	//구매 쿼리문 끝 ---------------------------------------------------------------------------------
}
