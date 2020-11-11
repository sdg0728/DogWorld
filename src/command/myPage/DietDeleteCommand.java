package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DietDAO;
import command.main.Command;

public class DietDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		DietDAO dao = new DietDAO();
		
		int petId = Integer.parseInt(request.getParameter("petId2"));
		
		try {
			cnt = dao.deleteByNo(petId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", cnt);

	}

}
