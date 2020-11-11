package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.ScheduleDAO;
import command.main.Command;

public class TodayUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		ScheduleDAO dao = new ScheduleDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		int s_check = Integer.parseInt(request.getParameter("s_check"));
		
		try {
			cnt = dao.updateToday(no, s_check);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("no", no);
		request.setAttribute("change", cnt);

	}

}
