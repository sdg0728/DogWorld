package beans.purchase;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import common.D;

public class GoodsDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public GoodsDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
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
	
	public GoodsDTO[] selectGoods() throws SQLException{
		GoodsDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM GOODS ORDER BY GOODS_ID ASC");
			rs = pstmt.executeQuery();
			
			arr = createGoodsArray(rs);
		} finally {
			close();
		}
		
		return arr;
	}
	
	//DTO 상품 리스트 SELECT
	public GoodsDTO[] selectFile(int category) throws SQLException {
		GoodsDTO[] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_SELECT_GOODS_ALL);
			pstmt.setInt(1, category);
			
			rs = pstmt.executeQuery();

			arr = createGoodsArray(rs);
		} finally {
			close();
		}

		return arr;
	}
	
	// ResultSet --> 상품DTO 배열로 리턴
		public GoodsDTO[] createGoodsArray(ResultSet rs) throws SQLException {
			GoodsDTO[] arr = null;
			List<GoodsDTO> list = new ArrayList<GoodsDTO>();
			
			while (rs.next()) {
				int goods_id = rs.getInt("goods_id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String detail_img = rs.getString("detail_img");
				String img = rs.getString("img");
				int category = rs.getInt("category");
				int count = rs.getInt("count");

				GoodsDTO dto = new GoodsDTO(goods_id, name, price, img, detail_img, category, count);
				list.add(dto);
			}
			
			arr = new GoodsDTO[list.size()];
			list.toArray(arr);

			return arr;
		}
		
		//장바구니 DTO리스트
		public PurchaseDTO[] createPurchaseArray(ResultSet rs) throws SQLException {
			PurchaseDTO[] arr = null;
			List<PurchaseDTO> list = new ArrayList<PurchaseDTO>();
			
			while (rs.next()) {
				String user_id = rs.getString("user_id");
				int goods_id = rs.getInt("goods_id");
				String name = rs.getString("name");
				String deliver = rs.getString("deliver");
				String phone = rs.getString("phone");
				
				Date d = rs.getDate("buydate");
				String buydate = "";
				if(d != null) {
					buydate = new SimpleDateFormat("yyyy-MM-dd").format(d);
				}
				
				int p_check = rs.getInt("p_check");
				int count = rs.getInt("count");
				int purchase_id = rs.getInt("purchase_id");
				
				PurchaseDTO dto = new PurchaseDTO(user_id, goods_id, name, deliver, phone, buydate, p_check, count, purchase_id);
				list.add(dto);
			}
			
			arr = new PurchaseDTO[list.size()];
			list.toArray(arr);

			return arr;
		}
		
		//상품 상세정보 DAO
		public GoodsDTO selectGoods(int goods_id) throws SQLException{
			GoodsDTO dto = null;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_GOODS);
				pstmt.setInt(1, goods_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int goodsId = rs.getInt("goods_id");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					String detail_img = rs.getString("detail_img");
					String img = rs.getString("img");
					int category = rs.getInt("category");
					int count = rs.getInt("count");

					dto = new GoodsDTO(goodsId, name, price, img, detail_img, category, count);
				}
				
			} finally {
				close();
			}
			return dto;
		}
		
		//상품 리뷰 가져오기
		public ReviewDTO[] selectReview(int goods_id) throws SQLException{
			ReviewDTO[] arr = null;
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_REVIEW);
				pstmt.setInt(1, goods_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String user_id = rs.getString("user_id");
					int goodsId = rs.getInt("goods_id");
					String t_content = rs.getString("t_content");
					String image = rs.getString("image");
					
					Date d = rs.getDate("regdate");
					String regdate = "";
					if(d != null) {
						regdate = new SimpleDateFormat("yyyy-MM-dd").format(d);
					}
					int review_id = rs.getInt("review_id");
					
					list.add(new ReviewDTO(user_id, goodsId, t_content, image, regdate, review_id));
				}
			} finally {
				close();
			}
			
			arr = new ReviewDTO[list.size()];
			list.toArray(arr);
			return arr;
		}
		
		//리뷰 추가하기
		public int insertReview(String user_id, int goods_id, String t_content, String image) throws SQLException{
			int cnt = 0;
			
			try {
//				pstmt = conn.prepareStatement(D.SQL_INSERT_REVIEW_CHECK);
//				pstmt.setInt(1, goods_id);
//				pstmt.setString(2, user_id);
//				pstmt.setInt(3, goods_id);
//				
//				rs = pstmt.executeQuery();
//				
//				while(rs.next()) {
//					String chk = rs.getString("t_content");
//					if(chk != null) {
//						return 100;
//					}
//				}
//				
//				pstmt.close();
//				rs.close();
				
				pstmt = conn.prepareStatement(D.SQL_INSERT_REVIEW);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, goods_id);
				pstmt.setString(3, t_content);
				pstmt.setString(4, image);
				
				cnt = pstmt.executeUpdate();

			} finally {
				close();
			}
			
			return cnt;
		}
		
		//파일 지우기
		public void deleteFiles(String image, String path, HttpServletRequest request) {
			if (image == null || request == null)
				return;

			// 물리적인 경로
			ServletContext context = request.getServletContext();
			String saveDirectory = context.getRealPath("upload/"+path);

			String[] temp = image.split("/");
			String img = temp[temp.length-1];
			
			File f = new File(saveDirectory, img);
			System.out.println("삭제시도 --> " + f.getAbsolutePath());

			if (f.exists()) {
				if (f.delete()) {
					System.out.println("삭제성공!");
				} else {
					System.out.println("삭제실패!");
				}
			}
			
		}
		
		//이미지 경로 가져오기
		public ResultSet selectImg(int review_id, String path, HttpServletRequest request) throws SQLException{
			ResultSet rs = null;
			
			try {
				pstmt = conn.prepareStatement("SELECT IMAGE FROM REVIEW WHERE REVIEW_ID = ?");
				pstmt.setInt(1, review_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String img = rs.getString("IMAGE");
					
					deleteFiles(img, path, request);
				}
				
			} finally {
				pstmt.close();
				rs.close();
			}
			
			return rs;
		}
		
		//리뷰 삭제하기
		public int deleteReview(int review_id, HttpServletRequest request) throws SQLException{
			int cnt = 0;
			
			try {
				rs = selectImg(review_id,"purchase",request);
				
				pstmt = conn.prepareStatement(D.SQL_DELETE_REVIEW);
				
				pstmt.setInt(1, review_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//리뷰 수정하기(이미지 포함)
		public int updateReview(String t_content, String image, int review_id, HttpServletRequest request) throws SQLException {
			int cnt = 0;
			
			try {
				rs = selectImg(review_id, "purchase", request);
				
				pstmt = conn.prepareStatement(D.SQL_UPDATE_REVIEW);
				
				pstmt.setString(1, t_content);
				pstmt.setString(2, image);
				pstmt.setInt(3, review_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//리뷰 수정하기(이미지 제외)
		public int updateReview(String t_content, int review_id) throws SQLException {
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_CONTENT_REVIEW);
				
				pstmt.setString(1, t_content);
				pstmt.setInt(2, review_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//장바구니에 추가
		public int insertCart(String user_id, int goods_id, int count) throws SQLException {
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_INSERT_CART);
				
				pstmt.setString(1, user_id);
				pstmt.setInt(2, goods_id);
				pstmt.setInt(3, count);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//장바구니 가져오기
		public PurchaseDTO[] selectCart(String user_id) throws SQLException {
			PurchaseDTO[] arr = null;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_CART);
				pstmt.setString(1, user_id);
				
				rs = pstmt.executeQuery();
				
				arr = createPurchaseArray(rs);
			} finally {
				close();
			}
			
			return arr;
		}
		
		public GoodsDTO[] selectGoodsByCart(int[] goods_ids) throws SQLException {
			GoodsDTO[] arr = null;
			List<GoodsDTO> list = new ArrayList<GoodsDTO>();
			
			try {
				for(int i = 0; i < goods_ids.length; i++) {
					pstmt = conn.prepareStatement(D.SQL_SELECT_GOODS);
					pstmt.setInt(1, goods_ids[i]);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int goods_id = rs.getInt("goods_id");
						String name = rs.getString("name");
						int price = rs.getInt("price");
						String img = rs.getString("img");
						String detail_img = rs.getString("detail_img");
						int category = rs.getInt("category");
						int count = rs.getInt("count");
						
						GoodsDTO dto = new GoodsDTO(goods_id, name, price, img, detail_img, category, count);
						list.add(dto);
					}
					
					pstmt.close();
					rs.close();
				}
				
				arr = new GoodsDTO[list.size()];
				list.toArray(arr);
			} finally {
				close();
			}
			
			return arr;
		}
		
		//장바구니 지우기
		public int deleteCart(int purchase_id) throws SQLException {
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_DELETE_CART);
				pstmt.setInt(1, purchase_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//저장된 카드 가져오기
		public CardDTO selectCard(String user_id) throws SQLException {
			CardDTO dto = null;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_CARD);
				pstmt.setString(1, user_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String card_num = rs.getString("card_num");
					int cvc = rs.getInt("cvc");
					int card_lim_year = rs.getInt("card_lim_year");
					int card_lim_month = rs.getInt("card_lim_month");
					int password = rs.getInt("password");
					String userId = rs.getString("user_id"); 
					int bank = rs.getInt("bank");
					
					dto = new CardDTO(card_num, cvc, card_lim_year, card_lim_month, password, userId, bank);
				}
			} finally {
				close();
			}
			
			return dto;
		}
		
		public int checkCard(String user_id) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement("select count(*) count from card where user_id = ?");
				pstmt.setString(1, user_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cnt = rs.getInt("count");
				}
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//카드 등록하기
		public int insertCard(String card_num, int cvc, int card_lim_year, int card_lim_month, 
				int password, String user_id, int bank) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_INSERT_CARD);
				pstmt.setString(1, card_num);
				pstmt.setInt(2, cvc);
				pstmt.setInt(3, card_lim_year);
				pstmt.setInt(4, card_lim_month);
				pstmt.setInt(5, password);
				pstmt.setString(6, user_id);
				pstmt.setInt(7, bank);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//카드 정보 수정
		public int updateCard(String card_num, int cvc, int card_lim_year, int card_lim_month, 
				int password, String user_id, int bank) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_CARD);
				pstmt.setString(1, card_num);
				pstmt.setInt(2, cvc);
				pstmt.setInt(3, card_lim_year);
				pstmt.setInt(4, card_lim_month);
				pstmt.setInt(5, password);
				pstmt.setInt(6, bank);
				pstmt.setString(7, user_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//purchase_id 가져오기
		public Integer[] selectPurchaseId(String user_id) throws SQLException {
			Integer[] idArr = null;
			List<Integer> list = new ArrayList<Integer>();
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_PURCHASE_ID);
				pstmt.setString(1, user_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int purchase_id = rs.getInt("purchase_id");
					
					list.add(purchase_id);
				}
				idArr = new Integer[list.size()];
				list.toArray(idArr);
				
				
			} finally {
				close();
			}
			
			return idArr;
		}
		
		//purcase_id count 가져오기
		public int[] selectCount(int purchase_id) throws SQLException {
			int[] cc = new int[2];
			
			try {
				pstmt = conn.prepareStatement("SELECT COUNT, GOODS_ID FROM PURCHASE WHERE PURCHASE_ID = ?");
				pstmt.setInt(1, purchase_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cc[0] = rs.getInt("count");
					cc[1] = rs.getInt("goods_id");
				}
				
			} finally {
				close();
			}
			
			return cc;
		}
		
		//구매하기
		public int updatePurchase(String name, String deliver, String phone, int purchase_id, int goods_id, int count) throws SQLException {
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_PURCHASE);
				pstmt.setString(1, name);
				pstmt.setString(2, deliver);
				pstmt.setString(3, phone);
				pstmt.setInt(4, purchase_id);
				
				cnt = pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(D.SQL_INSERT_ADMIN_PURCHASE);
				pstmt.setInt(1, purchase_id);
				
				cnt += pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(D.SQL_DECREASE_COUNT);
				pstmt.setInt(1, count);
				pstmt.setInt(2, goods_id);
				
				pstmt.executeUpdate();
				
				
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//구매목록
		public OrdersDTO[] selectOrders(String user_id) throws SQLException {
			OrdersDTO[] arr = null;
			List<OrdersDTO> list = new ArrayList<OrdersDTO>();
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_ORDERS);
				pstmt.setString(1, user_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int goods_id = rs.getInt("goods_id");
					int purchase_id = rs.getInt("purchase_id");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					String img = rs.getString("img");
					String detail_img = rs.getString("detail_img");
					int count = rs.getInt("count");
					int p_check = rs.getInt("p_check");
					String buydate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("buydate")); 
					
					OrdersDTO dto = new OrdersDTO(goods_id, purchase_id, name, price, img, detail_img, count, buydate, p_check);
					list.add(dto);
				}
				
				arr = new OrdersDTO[list.size()];
				list.toArray(arr);
			} finally {
				close();
			}
			
			return arr;
		}
		
		//구매 취소
		public int deletePurchase(int purchase_id, int goods_id, int count) throws SQLException {
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_DELETE_CART);
				pstmt.setInt(1, purchase_id);
				
				cnt = pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(D.SQL_DELETE_ADMIN_PURCHASE);
				pstmt.setInt(1, purchase_id);
				
				cnt += pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(D.SQL_INCREASE_COUNT);
				pstmt.setInt(1, goods_id);
				pstmt.setInt(2, count);
				pstmt.setInt(3, goods_id);
				
				pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}

		//상품 삭제
		public int deleteGoods(int goods_id, HttpServletRequest request) throws SQLException {
			int cnt = 0;
			
			try {
				rs = selectImg(goods_id, request);
				
				pstmt = conn.prepareStatement(D.SQL_DELETE_GOODS);
				pstmt.setInt(1, goods_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//이미지 경로 가져오기
				public ResultSet selectImg(int goods_id, HttpServletRequest request) throws SQLException{
					ResultSet rs = null;
					
					try {
						pstmt = conn.prepareStatement("SELECT IMG, DETAIL_IMG FROM GOODS WHERE GOODS_ID = ?");
						pstmt.setInt(1, goods_id);
						
						rs = pstmt.executeQuery();
						
						while(rs.next()) {
							String img = rs.getString("IMG");
							String detail_img = rs.getString("DETAIL_IMG");
							
							deleteFiles(img, "goods", request);
							deleteFiles(detail_img, "goods", request);
						}
						
					} finally {
						pstmt.close();
						rs.close();
					}
					
					return rs;
				}
		
		public int updateGoods(String name, int price, String img, String detail_img, int count, int goods_id,  HttpServletRequest request) throws SQLException{
			int cnt = 0;
			
			try {
				//rs = selectImg(goods_id, request);
				
				pstmt = conn.prepareStatement(D.SQL_UPDATE_GOODS);
				pstmt.setString(1, name);
				pstmt.setInt(2, price);
				pstmt.setString(3, img);
				pstmt.setString(4, detail_img);
				pstmt.setInt(5, count);
				pstmt.setInt(6, goods_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//상품수정
		public int updateGoods(String name, int price, String img, String detail_img, int count, int goods_id) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_GOODS);
				pstmt.setString(1, name);
				pstmt.setInt(2, price);
				pstmt.setString(3, img);
				pstmt.setString(4, detail_img);
				pstmt.setInt(5, count);
				pstmt.setInt(6, goods_id);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//상품등록
		public int insertGoods(String name, int price, String img, String detail_img, int category, int count) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_INSERT_GOODS);
				pstmt.setString(1, name);
				pstmt.setInt(2, price);
				pstmt.setString(3, img);
				pstmt.setString(4, detail_img);
				pstmt.setInt(5, category);
				pstmt.setInt(6, count);
				
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		//구매 상품 전체 리스트
		public AdminPurchaseDTO[] selectAllGoods() throws SQLException{
			AdminPurchaseDTO[] arr = null;
			List<AdminPurchaseDTO> list = new ArrayList<AdminPurchaseDTO>();
			
			try {
				pstmt = conn.prepareStatement(D.SQL_SELECT_ADMIN_ORDERS);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String goods_name = rs.getString("g_name");
					int price = rs.getInt("price");
					String user_id = rs.getString("user_id");
					int goods_id = rs.getInt("goods_id");
					String name = rs.getString("name");
					String deliver = rs.getString("deliver");
					String phone = rs.getString("phone");
					
					Date d = rs.getDate("buydate");
					String buydate = "";
					if(d != null) {
						buydate = new SimpleDateFormat("yyyy-MM-dd").format(d);
					}
					
					int p_check = rs.getInt("p_check");
					int count = rs.getInt("count");
					int purchase_id = rs.getInt("purchase_id");
					
					AdminPurchaseDTO dto = new AdminPurchaseDTO(goods_name, price, user_id, goods_id, name, deliver, phone, buydate, p_check, count, purchase_id);
					System.out.println(dto);
					list.add(dto);
				}
				
				arr = new AdminPurchaseDTO[list.size()];
				list.toArray(arr);
				
			} finally {
				close();
			}
			
			return arr;
		}
		
		//배송상태 변경
		public int updateCheck(int purchase_id, int p_check) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_P_CHECK);
				
				pstmt.setInt(1, p_check);
				pstmt.setInt(2, purchase_id);
				System.out.println(pstmt.getWarnings());
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
		
		public int updateAdminCheck(int purchase_id, int p_check) throws SQLException{
			int cnt = 0;
			
			try {
				pstmt = conn.prepareStatement(D.SQL_UPDATE_ADMIN_P_CHECK);
				
				pstmt.setInt(1, p_check);
				pstmt.setInt(2, purchase_id);
				System.out.println(pstmt.getWarnings());
				cnt = pstmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}
}
