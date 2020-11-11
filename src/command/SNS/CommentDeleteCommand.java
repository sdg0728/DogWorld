package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.WriteDAO;
import command.main.Command;
import beans.SNS.CommentDTO;

public class CommentDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		int cnt = 0;
		
		try {
			int comment_id = Integer.parseInt(request.getParameter("comment_id")); 
			cnt = dao.deleteByCid(comment_id);  // 트랜잭션 수행
			
		
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("CMTdelete", cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
