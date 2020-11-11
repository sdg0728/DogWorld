package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DiaryDAO;
import beans.myPage.DiaryDTO;
import command.main.Command;

public class DiaryReadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		DiaryDAO dao = new DiaryDAO();
		DiaryDTO [] arr = null;
		
		String id = request.getParameter("id");
				
		try {
			arr = dao.selectById(id);
			request.setAttribute("data", arr);
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
