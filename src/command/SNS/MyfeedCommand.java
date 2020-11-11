package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.CommentDTO;
import beans.SNS.WriteDAO;
import beans.SNS.WriteDTO;
import command.main.Command;

public class MyfeedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		WriteDTO [] arr = null;
		
		CommentDTO[] comArr = null;
		
		try {
			String user_id = request.getParameter("user_id"); //--> 로그인 세션에서 아이디 받아오기
			System.out.println(user_id);
			arr = dao.selectByUid(user_id);  // 트랜잭션 수행
			
			dao = new WriteDAO();
			comArr = dao.selectCommentByPid(user_id);
			  // 트랜잭션 수행
			
			// "list" 란  name 으로 request 에 arr 을 저장
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("list", arr);
			request.setAttribute("comList", comArr);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
