package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.WriteDAO;
import beans.SNS.WriteDTO;
import command.main.Command;

public class ModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		int cnt = 0;
		
		try {
			int post_id = Integer.parseInt(request.getParameter("post_id")); //--> post_id 받아오기
			String user_id = request.getParameter("user_id");
			String content = request.getParameter("content"); 
			cnt = dao.update(content, post_id);  // 트랜잭션 수행
			
			request.setAttribute("modify", cnt);
			request.setAttribute("user_id", user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
