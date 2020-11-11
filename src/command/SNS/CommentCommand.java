
package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.WriteDAO;
import command.main.Command;


public class CommentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		int cnt = 0 ; 
		try {
			
			String user_id = request.getParameter("user_id");
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			String content = request.getParameter("content");
			cnt = dao.commentinsert(content, post_id, user_id);  // 트랜잭션 수행
			  // 트랜잭션 수행
			
			// "list" 란  name 으로 request 에 arr 을 저장
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("p_list", cnt);
			request.setAttribute("user_id", user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
