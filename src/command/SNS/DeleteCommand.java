package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.WriteDAO;
import beans.SNS.WriteDTO;
import command.main.Command;

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		int cnt = 0;
		
		try {
			int post_id = Integer.parseInt(request.getParameter("post_id")); //--> post_id 받아오기
			cnt = dao.deleteByPid(post_id);  // 트랜잭션 수행
			
			// "list" 란  name 으로 request 에 arr 을 저장
			// request 가 컨트롤러에 전달될것이다.
			request.setAttribute("delete", cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
