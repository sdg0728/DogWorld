package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.ScheduleDAO;
import beans.myPage.ScheduleDTO;
import command.main.Command;

public class TodayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleDTO [] arr = null;
		
		String id = request.getParameter("id");
			
		try {
			arr = dao.selectToday(id);
			request.setAttribute("list", arr);
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
