package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DietDAO;
import beans.myPage.DietDTO;
import command.main.Command;

public class DietReadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		DietDAO dao = new DietDAO();
		
		DietDTO [] arr = null;
		
		int petId = Integer.parseInt(request.getParameter("petId"));
		
		try {
			arr = dao.selectById(petId);
			request.setAttribute("data", arr);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
