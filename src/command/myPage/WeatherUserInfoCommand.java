package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class WeatherUserInfoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		SignDAO dao = new SignDAO();
		UserDTO [] arr = null;

		String id = request.getParameter("id");
		
		System.out.println("id:????" +id);
		try {
			arr = dao.selectById(id);
			
			String juso = arr[0].getAddress();
			
			request.setAttribute("juso", juso);
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
