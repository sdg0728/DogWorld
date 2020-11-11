package command.userinfo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.UserDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO();
		UserDTO dto = null;
		
		try {
			String user_id = request.getParameter("id");
			String password = request.getParameter("pw");
			
			dto = dao.login(user_id);
			
			System.out.println(dto);
			if(dto != null) {
				if(!dto.getId().trim().equals(user_id) || !dto.getPw().trim().equals(password)) {
					dto = null;
				}
			}
			System.out.println(dto);
			request.setAttribute("DTO", dto);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
