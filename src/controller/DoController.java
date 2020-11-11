package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.main.Command;
import command.purchase.CardRegisterCommand;
import command.purchase.CartCommand;
import command.purchase.CartDeleteCommand;
import command.purchase.CartGoCommand;
import command.purchase.ReviewDeleteCommand;
import command.purchase.DetailCommand;
import command.purchase.GoodsDeleteCommand;
import command.purchase.GoodsDetailCommand;
import command.purchase.GoodsInsertCommand;
import command.purchase.GoodsListCommand;
import command.purchase.GoodsOrdersCommand;
import command.purchase.GoodsUpdateCommand;
import command.purchase.ListCommand;
import command.purchase.PurchaseCommand;
import command.purchase.UpdateCheckCommand;
import command.purchase.UpdateCommand;
import command.purchase.WriteCommand;
import command.userinfo.LoginCommand;
import command.myPage.DiaryDeleteCommand;
import command.myPage.DiaryUpdateCommand;
import command.myPage.DiaryViewCommand;
import command.myPage.DiaryWriteCommand;
import command.myPage.DietDeleteCommand;
import command.myPage.DietReadCommand;
import command.myPage.DietWriteCommand;
import command.myPage.ScheduleDeleteCommand;
import command.myPage.ScheduleUpdateCommand;
import command.myPage.ScheduleWriteCommand;
import command.myPage.TodayUpdateCommand;
import command.userinfo.IdSearchCommand;
import command.userinfo.InsertCommand;
import command.userinfo.PasswordDeleteCommand;
import command.userinfo.PasswordSelectCommand;
import command.userinfo.PasswordUpdateCommand;
import command.userinfo.PwSearchCommand;
import command.userinfo.UserInfoUpdateCommand;
import command.userinfo.UserInfoViewCommand;
import command.SNS.CommentCommand;
import command.SNS.FollowerCommand;
import command.SNS.FollowingAddCommand;
import command.SNS.FollowingFeedCommand;
import command.SNS.ModifyCommand;
import command.SNS.MyfeedCommand;
import command.SNS.SearchCommand;
import command.SNS.UnfollowingCommand;
import command.SNS.UploadCommand;
import command.SNS.CommentDeleteCommand;
import command.SNS.DeleteCommand;

/**
 * Servlet implementation class DoController
 */
@WebServlet("*.do")
public class DoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// 컨트롤러는 아래 두개를 갖고 있어야 한다.
		String viewPage = null; // 어떤 페이지를 보여줄지
		Command command = null; // 어떠한 로직을 수행할지

		// URL로 부터 URI, ContextPath, Command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println(com);
		
		switch (com) {
		
		//마이페이지 do command -------------------------------
		
		case "/userinfo/JSP/sign.do":
			viewPage = "sign.jsp";
			break;
		case "/userinfo/JSP/signOk.do":
			command = new InsertCommand();
			command.execute(request, response);
			viewPage = "signOk.jsp";
			break;
		case "/userinfo/JSP/id_search.do":
			viewPage = "id_search.jsp";
			break;
		case "/userinfo/JSP/id_searchOk.do":
			command = new IdSearchCommand();
			command.execute(request, response);
			viewPage = "id_searchOk.jsp";
			break;
		case "/userinfo/JSP/pw_search.do":
			viewPage = "pw_search.jsp";
			break;
		case "/userinfo/JSP/pw_searchOk.do":
			command = new PwSearchCommand();
			command.execute(request, response);
			viewPage = "pw_searchOk.jsp";
			break;
		case "/userinfo/JSP/myaccount.do":
			command = new UserInfoViewCommand();
			command.execute(request, response);
			viewPage = "myaccount.jsp";
			break;
		case "/userinfo/JSP/user_updateOk.do":
			command = new UserInfoUpdateCommand();
			command.execute(request, response);
			viewPage = "user_updateOk.jsp";
			break;
		case "/userinfo/JSP/change_password.do":
			viewPage = "change_password.jsp";
			break;
		case "/userinfo/JSP/password_updateOk.do":
			command = new PasswordUpdateCommand();
			command.execute(request, response);
			viewPage = "password_updateOk.jsp";
			break;
		case "/userinfo/JSP/membership_withdrawal.do":
			viewPage = "membership_withdrawal.jsp";
			break;
		case "/userinfo/JSP/user_deleteOk.do":
			command = new PasswordSelectCommand();
			command.execute(request, response);
			command = new PasswordDeleteCommand();
			command.execute(request, response);
			viewPage = "user_deleteOk.jsp";
			break;
		case "/myPage/JSP/myPet.do":
			System.out.println("pageChange: "+ request.getParameter("pageChange"));
			if(request.getParameter("pageChange") == null)
				viewPage = "myPet.jsp?pageChange=weather.jsp";
			else if(request.getParameter("pageChange").equals("weather.do"))
				viewPage = "myPet.jsp?pageChange=weather.jsp";
			else if(request.getParameter("pageChange").equals("diet.do")) {
				viewPage = "myPet.jsp?pageChange=diet.jsp";
			}
			else if(request.getParameter("pageChange").equals("info.do")) {
				viewPage = "myPet.jsp?pageChange=info.jsp";
			}
			else {
				viewPage = "myPet.jsp";
			}
			break;
		case "/myPage/JSP/diet_writeOk.do":
			command = new DietReadCommand();
			command.execute(request, response);
			command = new DietWriteCommand();
			command.execute(request, response);
			viewPage = "diet_writeOk.jsp";
			break;
		case "/myPage/JSP/diet_deleteOk.do":
			command = new DietDeleteCommand();
			command.execute(request, response);
			viewPage = "diet_deleteOk.jsp";
			break;
		case "/myPage/JSP/weather.do":
			viewPage = "weather.jsp";
			break;
		case "/myPage/JSP/diet.do":
			viewPage = "diet.jsp";
			break;
		case "/myPage/JSP/info.do":
			viewPage = "info.jsp";
			break;
		case "/myPage/JSP/today_updateOk.do":
			command = new TodayUpdateCommand();
			command.execute(request, response);
			viewPage = "today_updateOk.jsp";
			break;
		case "/myPage/JSP/diary.do":
			viewPage = "diary.jsp";
			break;
		case "/myPage/JSP/diary_write.do":
			viewPage = "diary_write.jsp";
			break;
		case "/myPage/JSP/diary_writeOk.do":
			command = new DiaryWriteCommand();
			command.execute(request, response);
			viewPage = "diary_writeOk.jsp";
			break;
		case "/myPage/JSP/diary_update.do":
			command = new DiaryViewCommand();
			command.execute(request, response);
			viewPage = "diary_update.jsp";
			break;
		case "/myPage/JSP/diary_updateOk.do":
			command = new DiaryUpdateCommand();
			command.execute(request, response);
			viewPage = "diary_updateOk.jsp";
			break;
		case "/myPage/JSP/diary_deleteOk.do":
			command = new DiaryDeleteCommand();
			command.execute(request, response);
			viewPage = "diary_deleteOk.jsp";
			break;
		case "/myPage/JSP/schedule.do":
			viewPage = "schedule.jsp";
			break;
		case "/myPage/JSP/schedule_writeOk.do":
			command = new ScheduleWriteCommand();
			command.execute(request, response);
			viewPage = "schedule_writeOk.jsp";
			break;
		case "/myPage/JSP/schedule_updateOk.do":
			command = new ScheduleUpdateCommand();
			command.execute(request, response);
			viewPage = "schedule_updateOk.jsp";
			break;
		case "/myPage/JSP/schedule_deleteOk.do":
			command = new ScheduleDeleteCommand();
			command.execute(request, response);
			viewPage = "schedule_deleteOk.jsp";
			break;
		
		//마이페이지 do command -------------------------------
		
			
		//SNS do command -------------------------------
			
		case "/SNS/JSP/upload.do":
			viewPage = "upload.jsp";
			break;
		case "/SNS/JSP/uploadOk.do":
			command = new UploadCommand();
			command.execute(request, response);
			viewPage = "uploadOk.jsp";
			break;

		case "/SNS/JSP/myfeed.do":
			command = new MyfeedCommand();
			command.execute(request, response);
			viewPage = "myfeed.jsp";
			break;
			
		case "/SNS/JSP/deleteOk.do":
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage = "deleteOk.jsp";
			break;
			
		case "/SNS/JSP/modifyOk.do":
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage = "modifyOk.jsp";
			break;
			
		case "/SNS/JSP/following.do":
			command = new FollowingFeedCommand();
			command.execute(request, response);
			viewPage = "following.jsp";
			break;
					
		case "/SNS/JSP/commentOk.do":
			command = new CommentCommand();
			command.execute(request, response);
			viewPage = "commentOk.jsp";
			break;
			
		case "/SNS/JSP/feedcommentOk.do":
			command = new CommentCommand();
			command.execute(request, response);
			viewPage = "feedcommentOk.jsp";
			break;
			
		case "/SNS/JSP/commentdeleteOk.do":
			command = new CommentDeleteCommand();
			command.execute(request, response);
			viewPage = "commentdeleteOk.jsp";
			break;
			
		case "/SNS/JSP/followerOk.do":
			command = new FollowerCommand();
			command.execute(request, response);
			viewPage = "followerOk.jsp";
			break;	
			
		case "/SNS/JSP/searchOk.do":
			command = new SearchCommand();
			command.execute(request, response);
			viewPage = "searchOk.jsp";
			break;	
		
		case "/SNS/JSP/search.do":
			viewPage = "search.jsp";
			break;
			
			
		case "/SNS/JSP/followingAddOk.do":
			command = new FollowingAddCommand();
			command.execute(request, response);
			viewPage = "followingAddOk.jsp";
			break;	
		
		case "/SNS/JSP/followingdeleteOk.do":
			command = new UnfollowingCommand();
			command.execute(request, response);
			viewPage = "followingdeleteOk.jsp";
			break;
			
		//SNS do command -------------------------------
		
			
		//상품 do command -------------------------------
		
		case "/index.do":
			viewPage = "index.jsp";
			break;
			
		case "/purchase/JSP/goods_list.do":
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "goods_list.jsp";
			break;
			
		case "/userinfo/JSP/login.do":
			viewPage = "login.jsp";
			break;
			
		case "/userinfo/JSP/logoutOk.do":
			viewPage = "logoutOk.jsp";
			break;
			
		case "/userinfo/JSP/loginOk.do":
			command = new LoginCommand();
			command.execute(request, response);
			viewPage = "loginOk.jsp";
			break;
			
		case "/purchase/JSP/goods_detail.do":
			command = new DetailCommand();
			command.execute(request, response);
			viewPage = "goods_detail.jsp";
			break;
			
		case "/purchase/JSP/writeOk.do":
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "writeOk.jsp";
			break;
		
		case "/purchase/JSP/reviewDeleteOk.do":
			command = new ReviewDeleteCommand();
			command.execute(request, response);
			viewPage = "reviewDeleteOk.jsp";
			break;
			
		case "/purchase/JSP/updateOk.do":
			command = new UpdateCommand();
			command.execute(request, response);
			viewPage = "updateOk.jsp";
			break;
		
		case "/purchase/JSP/cartGo.do":
			command = new CartGoCommand();
			command.execute(request, response);
			viewPage = "cartGo.jsp";
			break;
			
		case "/purchase/JSP/basket.do":
			command = new CartCommand();
			command.execute(request, response);
			viewPage = "basket.jsp";
			break;
		
		case "/purchase/JSP/purchaseGo.do":
			command = new CartGoCommand();
			command.execute(request, response);
			viewPage = "purchaseGo.jsp";
			break;
			
		case "/purchase/JSP/purchase.do":
			command = new CartCommand();
			command.execute(request, response);
			viewPage = "purchase.jsp";
			break;
			
		case "/purchase/JSP/cartDeleteOk.do":
			command = new CartDeleteCommand();
			command.execute(request, response);
			viewPage = "cartDeleteOk.jsp";
			break;
			
		case "/purchase/JSP/card_registerOk.do":
			command = new CardRegisterCommand();
			command.execute(request, response);
			viewPage = "card_registerOk.jsp";
			break;
			
		case "/purchase/JSP/purchaseOk.do":
			command = new PurchaseCommand();
			command.execute(request, response);
			viewPage = "purchaseOk.jsp";
			break;
			
		case "/purchase/JSP/orders.do":
			viewPage = "orders.jsp";
			break;
			
		case "/purchase/JSP/admin.do":
			command = new GoodsListCommand();
			command.execute(request, response);
			viewPage = "admin.jsp";
			break;
			
		case "/purchase/JSP/adminGoodsDetail.do":
			command = new GoodsDetailCommand();
			command.execute(request, response);
			viewPage = "adminGoodsDetail.jsp";
			break;
			
		case "/purchase/JSP/goodsDeleteOk.do":
			command = new GoodsDeleteCommand();
			command.execute(request, response);
			viewPage = "goodsDeleteOk.jsp";
			break;
			
		case "/purchase/JSP/GoodsUpdateOk.do":
			command = new GoodsUpdateCommand();
			command.execute(request, response);
			viewPage = "GoodsUpdateOk.jsp";
			break;
			
		case "/purchase/JSP/GoodsAdd.do":
			viewPage = "GoodsAdd.jsp";
			break;
			
		case "/purchase/JSP/goodsAddOk.do":
			command = new GoodsInsertCommand();
			command.execute(request, response);
			viewPage = "goodsAddOk.jsp";
			break;
			
		case "/purchase/JSP/adminGoodsOrders.do":
			command = new GoodsOrdersCommand();
			command.execute(request, response);
			viewPage = "adminGoodsOrders.jsp";
			break;
			
		case "/purchase/JSP/updateChkOk.do":
			command = new UpdateCheckCommand();
			command.execute(request, response);
			viewPage = "updateChkOk.jsp";
			break;
			
		//상품 do command -------------------------------
		}

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}
