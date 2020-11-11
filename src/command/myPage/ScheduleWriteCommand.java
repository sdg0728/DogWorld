package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.ScheduleDAO;
import command.main.Command;

public class ScheduleWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		ScheduleDAO dao = new ScheduleDAO();
		
		String content = request.getParameter("content");
		String start_date = request.getParameter("start");
		String end_date = request.getParameter("end");
		String id = request.getParameter("id");
		
		if(start_date.indexOf("T") >-1) {
			start_date = start_date.split("T")[0]+" "+start_date.split("T")[1];
			end_date = end_date.split("T")[0]+" "+end_date.split("T")[1];
		}

		if(content!=null && start_date != null && end_date != null && id != null
				&& content.trim().length()>0 && start_date.trim().length() >0
				&& end_date.trim().length() >0 && id.trim().length() >0) {
			try {
				
				// 첨푸 파일 정보도 같이 INSERT
				cnt = dao.insert(content, start_date, end_date, id);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("wirte", cnt);

	}

}
