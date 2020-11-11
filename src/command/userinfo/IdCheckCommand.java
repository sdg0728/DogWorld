package command.userinfo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class IdCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		 SignDAO dao = new SignDAO();
		 UserDTO [] arr = null;
		 
		 System.out.println("여기 오냐???");
		 
		 try {
			
			arr = dao.select();
			request.setAttribute("data", arr);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
