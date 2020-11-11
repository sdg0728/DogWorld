package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.ScheduleDAO;
import beans.userinfo.SignDAO;
import command.main.Command;

public class ScheduleUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		ScheduleDAO dao = new ScheduleDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		String start_date = request.getParameter("start");
		String end_date = request.getParameter("end");
				
		if(start_date.indexOf("T") >-1) {
			start_date = start_date.split("T")[0]+" "+start_date.split("T")[1];
			end_date = end_date.split("T")[0]+" "+end_date.split("T")[1];
		}

		if(content!=null && start_date != null && end_date != null &&  content.trim().length()>0 && start_date.trim().length() >0
				&& end_date.trim().length() >0) {
			try {
				cnt = dao.update(no, content, start_date, end_date);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("no", no);
		request.setAttribute("change", cnt);

	}

}
