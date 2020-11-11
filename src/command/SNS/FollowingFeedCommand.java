package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.CommentDTO;
import beans.SNS.WriteDAO;
import beans.SNS.WriteDTO;
import command.main.Command;

public class FollowingFeedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		WriteDTO [] arr = null;
		CommentDTO [] coments = null;
		try {
			String user_id = request.getParameter("user_id"); //--> 로그인 세션에서 아이디 받아오기
			System.out.println(user_id);
			arr = dao.select(user_id);  // 트랜잭션 수행
			
			dao = new WriteDAO();
			
			coments = dao.selectCommentByPid(user_id);
			// "list" 란  name 으로 request 에 arr 을 저장
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("followingFeed", arr);
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]+">>>>>>>>>>");
			}
			request.setAttribute("comList", coments);
			request.setAttribute("user_id", user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
