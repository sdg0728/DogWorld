package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.SNS.WriteDAO;
import command.main.Command;


public class UnfollowingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO(); 
		int cnt = 0 ; 
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
			try {
			
			String following = request.getParameter("following"); //유저본인
			String follower = request.getParameter("follower"); 
			cnt = dao.followingdeleteByid(following, follower);  // 트랜잭션 수행
			  // 트랜잭션 수행
			
			// "list" 란  name 으로 request 에 arr 을 저장
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("followdelete", cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
