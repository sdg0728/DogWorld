package command.userinfo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class PasswordDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		SignDAO dao = new SignDAO();
		UserDTO [] arr = (UserDTO [])request.getAttribute("data");
		
		int count = (arr== null) ? 0 : arr.length;
		
		String password = request.getParameter("psw");
		
		String id = request.getParameter("id");
		
		try {
			if(!password.equals(arr[0].getPw())) {
				cnt = 0;
			}else {
				cnt = dao.delete(id);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", cnt);
		
	}

}
