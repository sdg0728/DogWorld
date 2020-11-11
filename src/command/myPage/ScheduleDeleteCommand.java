package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.ScheduleDAO;
import command.main.Command;

public class ScheduleDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		ScheduleDAO dao = new ScheduleDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		try {
			cnt = dao.deleteByNo(no);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", cnt);

	}

}
