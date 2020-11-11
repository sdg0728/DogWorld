package command.userinfo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class PasswordSelectCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		SignDAO dao = new SignDAO();
		UserDTO [] arr = null;
		
		String id = request.getParameter("id");
		
		try {
			arr = dao.selectById(id);
			request.setAttribute("data", arr);
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
