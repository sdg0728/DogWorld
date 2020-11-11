package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.purchase.AjaxCardRegisterCommand;
import command.purchase.AjaxGoodsUpdateCommand;
import command.purchase.AjaxOrderDeleteCommand;
import command.purchase.AjaxOrdersCommand;
import command.purchase.CardCommand;
import command.purchase.CardRegisterCommand;
import command.purchase.CartCommand;
import command.purchase.GoodsUpdateCommand;
import command.purchase.OrderDeleteCommand;
import command.purchase.OrdersCommand;
import command.purchase.ajaxCardCommand;
import command.purchase.ajaxCartCommand;
import command.myPage.BreedInfoAjaxCommand;
import command.myPage.BreedInfoCommand;
import command.myPage.BreedInfoKindSelectCommand;
import command.myPage.DiaryAjaxCommand;
import command.myPage.DiaryReadCommand;
import command.myPage.DietAjaxCommand;
import command.myPage.DietReadCommand;
import command.myPage.PetInfoAjaxCommand;
import command.myPage.PetInfoReadCommand;
import command.myPage.ScheduleAjaxCommand;
import command.myPage.ScheduleReadCommand;
import command.myPage.TodayAjaxCommand;
import command.myPage.TodayCommand;
import command.myPage.WeatherAjaxCommand;
import command.myPage.WeatherAjaxSendCommand;
import command.myPage.WeatherUserInfoCommand;
import command.userinfo.AjaxCommand;
import command.userinfo.IdCheckCommand;
import command.SNS.AjaxFollowerListCommand;
import command.SNS.AjaxFollowingListCommand;
import command.SNS.AjaxLikelistCommand;
import command.SNS.AjaxPetkindListCommand;
import command.SNS.AjaxPostlikelistCommand;
import command.SNS.AjaxUserIdListCommand;
import command.SNS.FollowerCommand;
import command.SNS.FollowingCommand;
import command.SNS.LikeCommand;
import command.SNS.LikelistCommand;
import command.SNS.SearchCommand;
import command.SNS.UnlikeCommand;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ajaxAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ajaxAction(request, response);
	}

	private void ajaxAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//URL로 부터 URI, ContextPath, Command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println("ajax: "+com);
		
		switch(com) {
		//마이페이지 ajax command -------------------------------
		
		case "/userinfo/JSP/signId.ajax": // 글 전체 목록
			// 글 목록 읽기
			new IdCheckCommand().execute(request, response);
			
			new AjaxCommand().execute(request, response);
			
			break;
		case "/userinfo/JSP/signEmail.ajax": // 글 전체 목록
			// 글 목록 읽기
			new IdCheckCommand().execute(request, response);
			new AjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/petInfo.ajax":
			new PetInfoReadCommand().execute(request, response);
			new PetInfoAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/today.ajax":
			new TodayCommand().execute(request, response);
			new TodayAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/diet.ajax":
			new PetInfoReadCommand().execute(request, response);
			new PetInfoAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/getWeight.ajax":
			new DietReadCommand().execute(request, response);
			new DietAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/weather.ajax":
			new WeatherUserInfoCommand().execute(request, response);
			new WeatherAjaxCommand().execute(request, response);
			new WeatherAjaxSendCommand().execute(request, response);
			break;
		case "/myPage/JSP/info.ajax":
			new BreedInfoKindSelectCommand().execute(request, response);
			new BreedInfoCommand().execute(request, response);
			new BreedInfoAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/diary.ajax":
			new DiaryReadCommand().execute(request, response);
			new DiaryAjaxCommand().execute(request, response);
			break;
		case "/myPage/JSP/schedule.ajax":
			new ScheduleReadCommand().execute(request, response);
			new ScheduleAjaxCommand().execute(request, response);
			break;
		
		//마이페이지 ajax command -------------------------------
		
		
		//SNS ajax command -------------------------------
		
		case "/SNS/JSP/follower.ajax": // 글 전체 목록
			// 글 목록 읽기
			new FollowerCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxFollowerListCommand().execute(request, response);
			break;
		
		case "/SNS/JSP/following.ajax": // 글 전체 목록
			// 글 목록 읽기
			new FollowingCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxFollowingListCommand().execute(request, response);
			break;
			
			
			
		case "/SNS/JSP/userId.ajax":
			new SearchCommand().execute(request, response);
			if(request.getAttribute("list") != null) {
				new AjaxUserIdListCommand().execute(request, response);
			}else if(request.getAttribute("list2") != null) {
				new AjaxPetkindListCommand().execute(request, response);
			}
			break;
			

		case "/SNS/JSP/like.ajax": // 글 전체 목록
			// 글 목록 읽기
			new LikeCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxLikelistCommand().execute(request, response);
			break;
			
		case "/SNS/JSP/unlike.ajax": // 글 전체 목록
			// 글 목록 읽기
			new UnlikeCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxLikelistCommand().execute(request, response);
			break;
			
		case "/SNS/JSP/followingList.ajax": // 글 전체 목록
			// 글 목록 읽기
			new FollowerCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxFollowerListCommand().execute(request, response);
			break;
		
		case "/SNS/JSP/PostlikeList.ajax": // 글 전체 목록
			// 글 목록 읽기
			new LikelistCommand().execute(request, response);
			// 읽어온 데이터를 다음 커맨드에 넘겨줌.
			new AjaxPostlikelistCommand().execute(request, response);
			break;
		
		//SNS ajax command -------------------------------
		
		
		//상품 ajax command -------------------------------
		
			case "/purchase/JSP/basket.ajax":
				new CartCommand().execute(request, response);
				new ajaxCartCommand().execute(request, response);
				break;
				
			case "/purchase/JSP/card.ajax":
				new CardCommand().execute(request, response);
				new ajaxCardCommand().execute(request, response);
				break;
				
			case "/purchase/JSP/cardReg.ajax":
				new CardRegisterCommand().execute(request, response);
				new AjaxCardRegisterCommand().execute(request, response);
				break;
				
			case "/purchase/JSP/orders.ajax":
				new OrdersCommand().execute(request, response);
				new AjaxOrdersCommand().execute(request, response);
				break;
				
			case "/purchase/JSP/ordersDelete.ajax":
				new OrderDeleteCommand().execute(request, response);
				new AjaxOrderDeleteCommand().execute(request, response);
				break;
				
			case "/purchase/JSP/updateGoods.ajax":
				new GoodsUpdateCommand().execute(request, response);
				new AjaxGoodsUpdateCommand().execute(request, response);
				break;
				
			//상품 ajax command -------------------------------
		}
	}

}
