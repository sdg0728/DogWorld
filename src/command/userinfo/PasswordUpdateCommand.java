package command.userinfo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import command.main.Command;

public class PasswordUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		SignDAO dao = new SignDAO();
		
		String id = request.getParameter("id");
		String password = request.getParameter("psw");
		
		if(id !=null && password != null &&  id.trim().length()>0 && password.trim().length() >0) {
			try {
				cnt = dao.updatePassword(id, password);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("id", id);
		request.setAttribute("change", cnt);

	}

}
